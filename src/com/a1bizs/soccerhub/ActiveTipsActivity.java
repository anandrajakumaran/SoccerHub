package com.a1bizs.soccerhub;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.a1bizs.soccerhub.conf.CONFIG;
import com.a1bizs.soccerhub.favourite.FavouriteActivity;
import com.a1bizs.soccerhub.leagueToday.TodayActivity;
import com.a1bizs.soccerhub.member.LoginActivity;
import com.a1bizs.soccerhub.member.MemberActivity;
import com.a1bizs.soccerhub.model.ListItem;
import com.a1bizs.soccerhub.readDays.ReadDayActitity;
import com.a1bizs.soccerhub.utility.MySimpleArrayAdapter;
import com.a1bizs.soccerhub.utility.utilityData;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class ActiveTipsActivity extends Activity {
	private ListView listview;
	ProgressDialog dialog;
	private MySimpleArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_active_tips);
		dialog = new ProgressDialog(this);
		listview = (ListView) findViewById(R.id.listview);
		getFixtures();
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
	
	private void getFixtures() {
		new AsyncTask<Void, Void, ArrayList<ListItem>>() {
			
			@Override
		    protected void onPreExecute() {
		      show();
		    }
			
			@Override
			protected ArrayList<ListItem> doInBackground(Void... params) {
		ArrayList<ListItem> templist = null;
		try {
			URL findUrl = new URL("http://appsa1bizssg.org/subsc/tips?id=1");
			HttpURLConnection con = (HttpURLConnection) findUrl.openConnection();
			con.addRequestProperty("Cache-Control", "no-cache");
			int sc = con.getResponseCode();
			if (sc == 200) {
		        InputStream is = con.getInputStream();
		        ObjectMapper mapper = new ObjectMapper();
		        templist = (ArrayList<ListItem>) mapper.readValue(is, new TypeReference<ArrayList<ListItem>>() { });
		        Log.d("TTTTTTTTTEEEEEEEEEEEE", templist.toString());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return templist;
			}
			@Override
		    protected void onPostExecute(ArrayList<ListItem> li) {
			  doneLoading(li);	
		      hide();
		    }
		}.execute();
		
}
	
	void show() {
	    dialog.setMessage("Please wait");
	    dialog.show();
	}

	void hide() {
	    dialog.dismiss();
	}
	
	private void doneLoading(ArrayList<ListItem> lItem) {
		if ( lItem != null) {
		    adapter = new MySimpleArrayAdapter(this, lItem);
	        listview.setAdapter(adapter);
		} else
			Toast.makeText(getApplicationContext(), "No Active Tips", 
   				   Toast.LENGTH_LONG).show();
	}

}
