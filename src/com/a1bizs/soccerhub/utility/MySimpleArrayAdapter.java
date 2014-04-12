package com.a1bizs.soccerhub.utility;

import java.util.List;

import com.a1bizs.soccerhub.R;
import com.a1bizs.soccerhub.model.ListItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<ListItem> {
	  private final Context context;
	  private final List<ListItem> values;

	  public MySimpleArrayAdapter(Context context, List<ListItem> values) {		  
	    super(context, R.layout.listlayout, values);
	    this.context = context;
	    this.values = values;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View listView = inflater.inflate(R.layout.listlayout, parent, false);
	    TextView team = (TextView) listView.findViewById(R.id.team);
	    TextView date = (TextView) listView.findViewById(R.id.date);
	    TextView time = (TextView) listView.findViewById(R.id.time);
	    TextView odds = (TextView) listView.findViewById(R.id.odds);
	    TextView tips = (TextView) listView.findViewById(R.id.tips);
	    ListItem item = values.get(position);
	    team.setText(item.getTeama() + " - " + item.getTeamb());
	    date.setText(item.getDate().toString());
	    time.setText(item.getTime().toString());
	    odds.setText(item.getOdds());
	    tips.setText(item.getTips());
	    return listView;
	  }
	} 