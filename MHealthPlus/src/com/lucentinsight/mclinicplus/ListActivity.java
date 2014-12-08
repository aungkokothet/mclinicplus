package com.lucentinsight.mclinicplus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.lucentinsight.mclinicplus.data.Clinic;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class ListActivity  extends ActionBarActivity  {

	Map<String, Clinic> clinicsMap=null;
	
	private Clinic clinic=null;
	
//	ArrayAdapter<String> adapter;
//	 ArrayList<String> listItems=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if(id == R.id.Mapview){
			MapActivity.start(this,null);
			return true;
		}
		return super.onOptionsItemSelected(item);
		
	}
	
	 public static void start(Context context) {
	        Intent intent = new Intent(context, ListActivity.class);
	        context.startActivity(intent);
	 }
	
	 public void call(View view) {   
//		 Toast.makeText(this, view.getContentDescription(),Toast.LENGTH_SHORT).show();
	
		 Application.call(view.getContentDescription().toString(), this);
        
}   
	 public void makeAppointment(View view) {   
		 Application.makeAppointment(this);
	}
	 
	 public void clinicDetails(View view) {  
		 LinearLayout v = (LinearLayout) view;
//		 v.getChildAt(0)
		 TextView tv =(TextView) v.getChildAt(0);
		 clinic =clinicsMap.get(tv.getText());
//		 Toast.makeText(this, tv.getText(),Toast.LENGTH_SHORT).show();
		 ClinicActivity.start(ListActivity.this,clinic);
		
//	     finish();
	}
	 
	 @Override
	protected void onPause() {
	try {
		Application.updateStats(this);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		super.onPause();
	}
	 
	
//		BackgroundSplashTask bt = new BackgroundSplashTask(this,new MainActivity());
//		bt.execute();
	 
	 @Override
	protected void onStart() {
		 try {
			clinicsMap=Application.getClinicList(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			ListView list=(ListView)findViewById(R.id.listView1);

			String[] from = new String[] { "clinicName", "clinicaddress","imageButton3","noofdocs" };
		    int[] to = new int[] { R.id.clinicName, R.id.clinicaddress,R.id.imageButton3,R.id.noofdocs };
		    List<HashMap<String, Object>> fillMaps = new ArrayList<HashMap<String, Object>>();
		  

					    for (Clinic clinic : clinicsMap.values()) {
		    	  HashMap<String, Object> map = new HashMap<String, Object>();
		    	 map.put("clinicName", clinic.getName()); // This will be shown in R.id.title
		 	    map.put("clinicaddress", clinic.getAddress()); // And this in R.id.description
		 	   map.put("imageButton3", clinic.getContactNo());
		 	  map.put("noofdocs", clinic.getDoctors().size()+" Doctors");
		 	    fillMaps.add(map);
		    }   

		    

		    SimpleAdapter adapter = new ClinicListAdaptor(this, fillMaps, R.layout.clinicrow, from, to);
		    list.setAdapter(adapter);
//		 System.out.println("I am here!");
		super.onStart();
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	 
}
