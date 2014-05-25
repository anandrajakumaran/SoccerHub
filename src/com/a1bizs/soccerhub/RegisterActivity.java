package com.a1bizs.soccerhub;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONException;
import org.json.JSONObject;


import com.a1bizs.soccerhub.conf.CONFIG;
import com.a1bizs.soccerhub.favourite.FavouriteActivity;
import com.a1bizs.soccerhub.leagueToday.TodayActivity;
import com.a1bizs.soccerhub.member.Login;
import com.a1bizs.soccerhub.member.MemberActivity;
import com.a1bizs.soccerhub.model.ListItem;
import com.a1bizs.soccerhub.model.memberDb;
import com.a1bizs.soccerhub.readDays.ReadDayActitity;
import com.a1bizs.soccerhub.utility.utilityData;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	ProgressDialog dialog;
	TextView name;
	TextView email;
	TextView pass;
	TextView status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		dialog = new ProgressDialog(this);
		name = (TextView) findViewById(R.id.name);
		name.requestFocus();
		email = (TextView) findViewById(R.id.email);
		pass = (TextView) findViewById(R.id.password);
		status = (TextView) findViewById(R.id.status_register);
		TextView login = (TextView) findViewById(R.id.login_link);
		String loginText = "Login";
		SpannableString content = new SpannableString(loginText);
		content.setSpan(new UnderlineSpan(), 0, loginText.length(), 0);
		login.setText(content);
		login.setOnClickListener(
				new View.OnClickListener() {
		       public void onClick(View v){
		             Intent intent = new Intent(getBaseContext(), Login.class);
		             startActivity(intent);
		       }
		} );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        
        case R.id.spinning:
            Intent spinningActivity = new Intent(getApplicationContext(), SpinningActivity.class);
            startActivity(spinningActivity);
            overridePendingTransition (CONFIG.ACTIVITY_NO_ANIM, CONFIG.ACTIVITY_NO_ANIM);
            return true;
        
        case R.id.home:
            Intent matchActivity = new Intent(getApplicationContext(), MatchActivity.class);
            startActivity(matchActivity);
            overridePendingTransition (CONFIG.ACTIVITY_NO_ANIM, CONFIG.ACTIVITY_NO_ANIM);
            return true;
            
        case R.id.leagueToday:
            Intent leagueActivity = new Intent(getApplicationContext(), TodayActivity.class);
            startActivity(leagueActivity);
            overridePendingTransition (CONFIG.ACTIVITY_NO_ANIM, CONFIG.ACTIVITY_NO_ANIM);
            return true;
 
        case R.id.member:
            Intent memberActivity;
            if(utilityData.isLogin(this) == true)
            	memberActivity = new Intent(getApplicationContext(), MemberActivity.class);
            else
            	memberActivity = new Intent(getApplicationContext(), Login.class);
            startActivity(memberActivity);
            overridePendingTransition (CONFIG.ACTIVITY_NO_ANIM, CONFIG.ACTIVITY_NO_ANIM);
            return true;
            
        case R.id.favourite:
            Intent favouriteActivity = new Intent(getBaseContext(), FavouriteActivity.class);
            startActivity(favouriteActivity);
            overridePendingTransition (CONFIG.ACTIVITY_NO_ANIM, CONFIG.ACTIVITY_NO_ANIM);
            return true;
            
//        case R.id.readDays:
//            Intent readDaysActivity = new Intent(getBaseContext(), ReadDayActitity.class);
//            startActivity(readDaysActivity);
//            overridePendingTransition (CONFIG.ACTIVITY_NO_ANIM, CONFIG.ACTIVITY_NO_ANIM);
//            return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }         
	
	public void register_listener(View view) {	
		email.setError(null);
		pass.setError(null);
		name.setError(null);
		
		boolean cancel = false;
		View focusView = null;
		
		String mEmail    = email.getText().toString();
		String mPassword = pass.getText().toString();
		String mName = name.getText().toString();

		if (TextUtils.isEmpty(mName)) {
			name.setError(getString(R.string.error_field_required));
			focusView = name;
			cancel = true;
		} else if (TextUtils.isEmpty(mPassword)) {
			pass.setError(getString(R.string.error_field_required));
			focusView = pass;
			cancel = true;
		} else if (mPassword.length() < 4) {
			pass.setError(getString(R.string.error_invalid_password));
			focusView = pass;
			cancel = true;
		} 
		

		if (TextUtils.isEmpty(mEmail)) {
			email.setError(getString(R.string.error_field_required));
			focusView = email;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			email.setError(getString(R.string.error_invalid_email));
			focusView = email;
			cancel = true;
		} 
		
		if (cancel) {
			focusView.requestFocus();
		} else {
		
new AsyncTask<Void, Void, Void>() {
			
			@Override
		    protected void onPreExecute() {
		      show();
		    }
			
			@Override
			protected Void doInBackground(Void... params) {
				try {
					memberDb user = new memberDb();
					user.setName(name.getText().toString());
					user.setEmail(email.getText().toString());
					user.setPassword(pass.getText().toString());
					HttpClient httpclient = new DefaultHttpClient();
				//	HttpPut httpput = new HttpPut("http://appsa1bizssg.org/register/register");
					HttpPut httpput = new HttpPut("http://appsa1bizssg.org/android_login_api/");
					ObjectMapper mapper = new ObjectMapper();
						String json = mapper.writeValueAsString(user);
						Log.d("JSSSSSSSSS", json);
						StringEntity entity = new StringEntity(json);
						entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
						httpput.setEntity(entity);
						HttpResponse response = httpclient.execute(httpput);
//						HttpEntity resEntity = response.getEntity();
//						InputStream is = resEntity.getContent();
//						int status = jsonparser(readResponse(is));
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  catch (JsonGenerationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JsonMappingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				return null;
			}
			
			private String readResponse(InputStream is) throws IOException {
		        ByteArrayOutputStream bos = new ByteArrayOutputStream();
		        byte[] data = new byte[2048];
		        int len = 0;
		        while ((len = is.read(data, 0, data.length)) >= 0) {
		            bos.write(data, 0, len);
		        }
		        return new String(bos.toByteArray(), "UTF-8");
		    }
			
			public int jsonparser(String res) {
				try {
					JSONObject jsonobj = new JSONObject(res);
					return (Integer)jsonobj.get("id");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return -1;
			}
			@Override
		    protected void onPostExecute(Void h) {
			  status.setText("Successfully Registered. An Activation email is sent to your email id");
		      hide();
		    }
		}.execute();
		}
		
	}
	
	
	void show() {
	    dialog.setMessage("Please wait");
	    dialog.show();
	}

	void hide() {
	    dialog.dismiss();
	}

}
