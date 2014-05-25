package com.a1bizs.soccerhub.leagueToday;

import java.util.ArrayList;

import com.a1bizs.soccerhub.R;
import com.a1bizs.soccerhub.model.countryDb;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class TodayList extends ArrayAdapter<countryDb>
{
	private final Activity context;
	private final ArrayList<countryDb> countries;
	private final Integer imageId;
	public TodayList(Activity context, ArrayList<countryDb> countries, Integer imageId)
	{
		super(context, R.layout.country_single, countries);
		this.context = context;
		this.countries = countries;
		this.imageId = imageId;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) 
	{
//		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//		View rowView        = inflater.inflate(R.layout.country_single, null, true);
//		TextView txtTitle   = (TextView) rowView.findViewById(R.id.txt);
//		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
//		txtTitle.setText(countries.get(position).getName());
//		imageView.setImageResource(imageId);
//		rowView.setId(countries.get(position).getID());
//		return rowView;
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View rowView        = inflater.inflate(R.layout.country_single, parent, false);
		TextView txtTitle   = (TextView) rowView.findViewById(R.id.txt);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(countries.get(position).getName());
		rowView.setId(countries.get(position).getID());
		String country = countries.get(position).getName();
		
		if(country.equalsIgnoreCase("Serbia"))
		{
			imageView.setImageResource(R.drawable.serbia);

		}else 	if(country.equalsIgnoreCase("USA"))
		{
			imageView.setImageResource(R.drawable.usa);

		}else 	if(country.equalsIgnoreCase("Sweden"))
		{
			imageView.setImageResource(R.drawable.sweden);

		}else 	if(country.equalsIgnoreCase("Argentina"))
		{
			imageView.setImageResource(R.drawable.argentina);

		}else 	if(country.equalsIgnoreCase("Portugal"))
		{
			imageView.setImageResource(R.drawable.portugal);

		}else 	if(country.equalsIgnoreCase("Venezuela"))
		{
			imageView.setImageResource(R.drawable.venezuela);

		}else 	if(country.equalsIgnoreCase("Norway"))
		{
			imageView.setImageResource(R.drawable.norway);

		}else 	if(country.equalsIgnoreCase("Scotland"))
		{
			imageView.setImageResource(R.drawable.scotland);

		}else 	if(country.equalsIgnoreCase("Denmark"))
		{
			imageView.setImageResource(R.drawable.denmark);

		}else 	if(country.equalsIgnoreCase("Poland"))
		{
			imageView.setImageResource(R.drawable.poland);

		}else 	if(country.equalsIgnoreCase("Slovenia"))
		{
			imageView.setImageResource(R.drawable.slovenia);

		}else 	if(country.equalsIgnoreCase("Czech Republic"))
		{
			imageView.setImageResource(R.drawable.czech_republic);

		}else 	if(country.equalsIgnoreCase("Germany"))
		{
			imageView.setImageResource(R.drawable.germany);

		}else 	if(country.equalsIgnoreCase("Uruguay"))
		{
			imageView.setImageResource(R.drawable.uruguay);

		}else 	if(country.equalsIgnoreCase("China"))
		{
			imageView.setImageResource(R.drawable.china);

		}else 	if(country.equalsIgnoreCase("Italy"))
		{
			imageView.setImageResource(R.drawable.italy);

		}else 	if(country.equalsIgnoreCase("Belarus"))
		{
			imageView.setImageResource(R.drawable.belarus);

		}else 	if(country.equalsIgnoreCase("Brazil"))
		{
			imageView.setImageResource(R.drawable.brazil);

		}else 	if(country.equalsIgnoreCase("Korea Republic"))
		{
			imageView.setImageResource(R.drawable.korea_republic);

		}else 	if(country.equalsIgnoreCase("Iceland"))
		{
			imageView.setImageResource(R.drawable.iceland);

		}else 	if(country.equalsIgnoreCase("Finland"))
		{
			imageView.setImageResource(R.drawable.finland);

		}else 	if(country.equalsIgnoreCase("Japan"))
		{
			imageView.setImageResource(R.drawable.japan);

		}else 	if(country.equalsIgnoreCase("Ireland"))
		{
			imageView.setImageResource(R.drawable.ireland);

		}else 	if(country.equalsIgnoreCase("Switzerland"))
		{
			imageView.setImageResource(R.drawable.switzerland);

		}else 	if(country.equalsIgnoreCase("Ukraine"))
		{
			imageView.setImageResource(R.drawable.ukraine);

		}else 	if(country.equalsIgnoreCase("Hungary"))
		{
			imageView.setImageResource(R.drawable.hungary);

		}else 	if(country.equalsIgnoreCase("Paraguay"))
		{
			imageView.setImageResource(R.drawable.paraguay);

		}else 	if(country.equalsIgnoreCase("Lithuania"))
		{
			imageView.setImageResource(R.drawable.lithuania);

		}else 	if(country.equalsIgnoreCase("Austria"))
		{
			imageView.setImageResource(R.drawable.austria);

		}else 	if(country.equalsIgnoreCase("Ecuador"))
		{
			imageView.setImageResource(R.drawable.ecuador);

		}else 	if(country.equalsIgnoreCase("Panama"))
		{
			imageView.setImageResource(R.drawable.panama);

		}else 	if(country.equalsIgnoreCase("Russia"))
		{
			imageView.setImageResource(R.drawable.russia);

		}else 	if(country.equalsIgnoreCase("England"))
		{
			imageView.setImageResource(R.drawable.england);

		}else 	if(country.equalsIgnoreCase("Bolivia"))
		{
			imageView.setImageResource(R.drawable.bolivia);

		}else 	if(country.equalsIgnoreCase("Slovakia"))
		{
			imageView.setImageResource(R.drawable.slovakia);

		}else 	if(country.equalsIgnoreCase("Croatia"))
		{
			imageView.setImageResource(R.drawable.croatia);

		}else 	if(country.equalsIgnoreCase("Turkey"))
		{
			imageView.setImageResource(R.drawable.turkey);

		}else 	if(country.equalsIgnoreCase("Bulgaria"))
		{
			imageView.setImageResource(R.drawable.bulgaria);

		}else 	if(country.equalsIgnoreCase("Greece"))
		{
			imageView.setImageResource(R.drawable.greece);

		}else 	if(country.equalsIgnoreCase("International"))
		{
			imageView.setImageResource(R.drawable.international);

		}else 	if(country.equalsIgnoreCase("Belgium"))
		{
			imageView.setImageResource(R.drawable.belgium);

		}else 	if(country.equalsIgnoreCase("FYR Macedonia"))
		{
			imageView.setImageResource(R.drawable.fyr_macedonia);

		}else 	if(country.equalsIgnoreCase("Spain"))
		{
			imageView.setImageResource(R.drawable.spain);

		}else 	if(country.equalsIgnoreCase("Chile"))
		{
			imageView.setImageResource(R.drawable.chile);

		}else 	if(country.equalsIgnoreCase("Mexico"))
		{
			imageView.setImageResource(R.drawable.mexico);

		}else 	if(country.equalsIgnoreCase("Romania"))
		{
			imageView.setImageResource(R.drawable.romania);

		}else 	if(country.equalsIgnoreCase("Israel"))
		{
			imageView.setImageResource(R.drawable.israel);

		}else 	if(country.equalsIgnoreCase("Costa Rica"))
		{
			imageView.setImageResource(R.drawable.costa_rica);

		}else 	if(country.equalsIgnoreCase("Estomia"))
		{
			imageView.setImageResource(R.drawable.estonia);

		}else 	if(country.equalsIgnoreCase("France"))
		{
			imageView.setImageResource(R.drawable.france);

		}else 	if(country.equalsIgnoreCase("Latvia"))
		{
			imageView.setImageResource(R.drawable.latvia);

		}else 	if(country.equalsIgnoreCase("Colombia"))
		{
			imageView.setImageResource(R.drawable.colombia);

		}else{
			
			imageView.setImageResource(R.drawable.not_available);

		}

		return rowView;
	}
}