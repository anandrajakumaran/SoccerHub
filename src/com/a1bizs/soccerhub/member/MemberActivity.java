package com.a1bizs.soccerhub.member;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Date;

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
import org.json.JSONException;

import com.a1bizs.soccerhub.R;
import com.a1bizs.soccerhub.ActiveTipsActivity;
import com.a1bizs.soccerhub.MatchActivity;
import com.a1bizs.soccerhub.SpinningActivity;
import com.a1bizs.soccerhub.conf.CONFIG;
import com.a1bizs.soccerhub.conf.PREFERENCE_CONF;
import com.a1bizs.soccerhub.favourite.FavouriteActivity;
import com.a1bizs.soccerhub.leagueToday.TodayActivity;
import com.a1bizs.soccerhub.model.Subscription;
import com.a1bizs.soccerhub.readDays.ReadDayActitity;
import com.a1bizs.soccerhub.utility.utilityData;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
@SuppressWarnings("deprecation")
public class MemberActivity extends Activity implements OnTouchListener 
{
	protected static LocalActivityManager mLocalActivityManager;
	String memberId;
	String memberName;
	TextView topMember;
	private static final String CONFIG_ENVIRONMENT = PaymentActivity.ENVIRONMENT_PRODUCTION;
	private static final String CONFIG_CLIENT_ID = "AdygMBA8j4wohkqwGcriUCWsn1f2VXNfcydrkWjBWtFJ3C84UNm0B_WdiL37";
	private static final String CONFIG_RECEIVER_EMAIL ="a1bizssg@a1bizs.com";
	private Subscription subsc;
	private double fees;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member);
Intent intent = new Intent(this, PayPalService.class);
        
        intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT, CONFIG_ENVIRONMENT);
        intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, CONFIG_CLIENT_ID);
        intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL, CONFIG_RECEIVER_EMAIL);
         
        startService(intent);
		setupMemberInfo();
	}

	public void setupMemberInfo()
	{
		try
		{
			SharedPreferences sharedPreferences = PreferenceManager
					  .getDefaultSharedPreferences(this);
			boolean isLogin = sharedPreferences.getBoolean(PREFERENCE_CONF.IS_LOGIN, false);
			if(isLogin == false)
				return;
			
			memberId   = sharedPreferences.getString(PREFERENCE_CONF.MEM_ID, "");
			memberName = sharedPreferences.getString(PREFERENCE_CONF.MEM_NAME, "");
			
			((TextView)findViewById(R.id.textHead)).setText(memberName);

			TextView infoMember = (TextView) findViewById(R.id.memberInfo);
			infoMember.setText(memberName);
			
			TextView logout = (TextView) findViewById(R.id.memberLogout);
			logout.setText("( Log out )");
			logout.setClickable(true);
			logout.setOnTouchListener(this);
		}
		catch(Exception e)
		{
			String msg = e.getMessage();
			Log.d("member", msg);
		}
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) 
	{
		if(event.getAction() != MotionEvent.ACTION_DOWN)
			return false;
		
		// TODO Auto-generated method stub
		if(v.getId() == R.id.memberLogout)
		{
			Toast.makeText(getBaseContext(), "Log out", Toast.LENGTH_SHORT).show();
			
			removePreference(PREFERENCE_CONF.MEM_ID);
			removePreference(PREFERENCE_CONF.MEM_NAME);
			removePreference(PREFERENCE_CONF.IS_LOGIN);
			removePreference(PREFERENCE_CONF.IS_ADMIN);
			
            Intent loginActivity = new Intent(getBaseContext(), LoginActivity.class);
            try
    		{
    			replaceContentView("loginActivity", loginActivity);
    		}
    		catch(Exception e)
    		{
    			String msg = e.getMessage();
    			Log.d("Change Activity", msg);
    		}
          
		}
		return false;
	}    
	
	public void replaceContentView(String id, Intent newIntent) 
	{
		try
		{
			/*
			newIntent.addFlags(Intent. FLAG_ACTIVITY_NEW_TASK);
			View view =  getLocalActivityManager()
					    .startActivity(id,newIntent) 
					    .getDecorView(); 
			this.setContentView(view);
			*/
			newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(newIntent);
			overridePendingTransition (CONFIG.ACTIVITY_NO_ANIM, CONFIG.ACTIVITY_NO_ANIM);
		}
		catch(Exception e)
		{
			String msg = e.getMessage();
			Log.d("Change Activity", msg);
		}
	}
	
	public void removePreference(String key)
	{
		SharedPreferences sharedPreferences = PreferenceManager
											  .getDefaultSharedPreferences(this);
		sharedPreferences.edit().remove(key).commit();
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
            	memberActivity = new Intent(getApplicationContext(), LoginActivity.class);
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
	
	public void activetips_listener(View view) {
		Intent intent = new Intent(getBaseContext(), ActiveTipsActivity.class);
		replaceContentView("ActiveTipsActivity", intent);
	}
	
	public void onBuy_listener(View pressed) {
		
		int id = pressed.getId();
		subsc = new Subscription(1);
		Date curr = new Date(System.currentTimeMillis());
		switch (id) {
		case R.id.today:
			subsc.setStart(curr);
			subsc.setEnd(curr);
			fees = 1.50;
			break;
			
		case R.id.month:
		    Date end = new Date(curr.getTime());
		    end.setMonth(end.getMonth() + 1);
			subsc.setStart(curr);
			subsc.setEnd(end);
			fees = 10.25;
			break;
			
		case R.id.year:
			Date endy = new Date(curr.getTime());
		    endy.setYear(endy.getYear() + 1);
			subsc.setStart(curr);
			subsc.setEnd(endy);
			fees = 50.50;
			break;
			
		}
		PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(fees), "USD", "Tips Subscription");
        
        Intent intent = new Intent(this, PaymentActivity.class);
         
        intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT, CONFIG_ENVIRONMENT);
        intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, CONFIG_CLIENT_ID);
        intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL, CONFIG_RECEIVER_EMAIL);
         
        // It's important to repeat the clientId here so that the SDK has it if Android restarts your 
        // app midway through the payment UI flow.
        intent.putExtra(PaymentActivity.EXTRA_PAYER_ID, "1");
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
         
        startActivityForResult(intent, 0);
	}
	
	@Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    // TODO: send 'confirm' to your server for verification.
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.
                    createSubscription();
                    Toast.makeText(getApplicationContext(), "Success", 
          				   Toast.LENGTH_LONG).show();
                     Log.i("paymentExample", confirm.toJSONObject().toString(4));
 
                } catch (JSONException e) {
                	Toast.makeText(getApplicationContext(), "an extremely unlikely failure occurred: ", 
          				   Toast.LENGTH_LONG).show();
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
        	Toast.makeText(getApplicationContext(), "The user canceled.", 
  				   Toast.LENGTH_LONG).show();
            Log.i("paymentExample", "The user canceled.");
        }
        else if (resultCode == PaymentActivity.RESULT_PAYMENT_INVALID) {
        	Toast.makeText(getApplicationContext(), "An invalid payment was submitted. Please see the docs.", 
  				   Toast.LENGTH_LONG).show();
            Log.i("paymentExample", "An invalid payment was submitted. Please see the docs.");
        }
    }
     
    private void createSubscription() {
             new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPut httpput = new HttpPut("http://appsa1bizssg.org/subsc/createSubsc");
				ObjectMapper mapper = new ObjectMapper();
					String json = mapper.writeValueAsString(subsc);
					Log.d("JSSSSSSSSS", json);
					StringEntity entity = new StringEntity(json);
					entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
					httpput.setEntity(entity);
					HttpResponse res = httpclient.execute(httpput);
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
		}.execute();
		
	}
    

	@Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
	
	
	/*	
	@Override
	public void onBackPressed() 
    {
		Log.e("back", "Favourite pressed accepted");
    	 	 	//super.onBackPressed(); 
		finish();
   // 	moveTaskToBack(true);
  //  	finish();
	    Log.e("back", "pressed accepted");
	    Intent intent = new Intent(this, MatchActivity.class);
	    replaceContentView("matchActivity", intent);
	//    startActivity(intent);
	 }
	*/ 
}
