package com.lucentinsight.mclinicplus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.lucentinsight.mclinicplus.data.Clinic;
import com.lucentinsight.mclinicplus.data.Doctor;
import com.lucentinsight.mclinicplus.data.Schedule;

import android.support.v7.app.ActionBarActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ClinicActivity extends ActionBarActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

static Clinic clinic;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clinic);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clinic, menu);
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
		}if (id == R.id.action_bar_title) {
			this.finish();
			return true;
		}
		 onBackPressed();
			if(id == R.id.Mapview){
				MapActivity.start(this,clinic);
				return true;
			}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			if(position==0){
				return PlaceholderFragment.newInstance(position + 1,"All");
			}
			return PlaceholderFragment.newInstance(position + 1,clinic.getSpecialization().toArray()[position-1].toString());
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			
			return clinic.getSpecialization().size()+1;
		
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		
		
		private  String special;

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber,String sp) {
			PlaceholderFragment fragment = new PlaceholderFragment(sp);
			Bundle args = new Bundle();
			
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
//			PlaceholderFragment.sectionNumber=sectionNumber;
			return fragment;
		}

		public PlaceholderFragment(String sp) {
			special=sp;

		}

		public PlaceholderFragment() {
//			special="All";

		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_clinic,
					container, false);
//			
			
			ListView list=(ListView)rootView.findViewById(R.id.listView2);
			TextView lablel=(TextView)rootView.findViewById(R.id.section_label);
			if(special.equals("All")){
				lablel.setText("  "+special+"     >>");
			}else{
			lablel.setTypeface(Application.getTypeFace());	
			int id=Application.getContext().getResources().getIdentifier(special.replace(" ", "_"), "string", "com.lucentinsight.mclinicplus");
			System.out.println("id:"+id);
			System.out.println("special:"+special);
			if(id!=0){
			String mystring = Application.getContext().getResources().getString(id);
			lablel.setText("  "+special+"s ("+mystring+")     >>");
			}else{
			lablel.setText("  "+special+"s     >>");
			}
			}
//			 adapter=new ArrayAdapter<String>(this,
//			            android.R.layout.simple_list_item_1,
//			            listItems);
//			 list.setAdapter(adapter);
			
			String[] from = new String[] { "doctorName", "doctorQualification","doctorSpecialization" };
		    int[] to = new int[] { R.id.doctorName, R.id.doctorQualification,R.id.doctorSpecialization };
		    List<HashMap<String, Object>> fillMaps = new ArrayList<HashMap<String, Object>>();
		   
	
		
				 Map<String, Doctor> doctors=clinic.getDoctors();  
				    for (Doctor doctor : doctors.values()) {
				    	List<Schedule> scheduleList = doctor.getSchdules();
				    	String schedulestr="";
				    	for (Iterator iterator = scheduleList.iterator(); iterator
								.hasNext();) {
							Schedule schedule = (Schedule) iterator.next();
							schedulestr=schedulestr+schedule.getDay()+": From "+schedule.getStartTime()+" to "+schedule.getEndTime()+"\n";
						}
//				    	System.out.println("sectionNumber:"+special);
//				    	System.out.println("tab sp:"+clinic.getSpecialization().toArray()[0]);
				    	  HashMap<String, Object> map = new HashMap<String, Object>();
				    	  if(special.equals("All")){
				    	 map.put("doctorName", doctor.getName()); // This will be shown in R.id.title
				 	    map.put("doctorQualification", doctor.getQualification()); // And this in R.id.description
				 	   map.put("doctorSpecialization", doctor.getSpecialization()+"\n"+schedulestr);
//				 	   System.out.println("schedules"+doctor.getSpecialization()+"\n"+schedulestr);
				 	    fillMaps.add(map);
				    	}else if(doctor.getSpecialization().equals(special)){
				    		 map.put("doctorName", doctor.getName()); // This will be shown in R.id.title
						 	    map.put("doctorQualification", doctor.getQualification()); // And this in R.id.description
						 	   map.put("doctorSpecialization", doctor.getSpecialization()+"\n"+schedulestr);
						 	  fillMaps.add(map);
				    	}
				    }
				
			
		
		   	    

		    SimpleAdapter adapter = new ClinicListAdaptor(rootView.getContext(), fillMaps, R.layout.doctorrow, from, to);
		    list.setAdapter(adapter);
			return rootView;
		}
	}

	public static void start(Context context) {
	
	    Intent intent = new Intent(context, ClinicActivity.class);
	    context.startActivity(intent);
	 
	}

	 public void call(View view) {   
//		 Toast.makeText(this, view.getContentDescription(),Toast.LENGTH_SHORT).show();
		
		 Application.call(clinic.getContactNo(), this);
        
}   
	 public void makeAppointment(View view) {   
		 Application.makeAppointment(this);
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
	 
	 @Override
	protected void onStart() {
			android.app.ActionBar actionBar = getActionBar();
			Typeface typeFace=Typeface.createFromAsset(this.getAssets(),"ZAWGYIONE2008.TTF");
			Application.setTypeFace(typeFace);
			Application.setContext(this);
			actionBar.setDisplayHomeAsUpEnabled(true);
			// Create the adapter that will return a fragment for each of the three
			// primary sections of the activity.
			mSectionsPagerAdapter = new SectionsPagerAdapter(
					getSupportFragmentManager());

			
			// Set up the ViewPager with the sections adapter.
			mViewPager = (ViewPager) findViewById(R.id.pager);
			mViewPager.setAdapter(mSectionsPagerAdapter);
			 TextView  textView1 = (TextView)findViewById(R.id.clinicName2);
	    	 TextView  textView2 = (TextView)findViewById(R.id.clinicaddress2);
	    
//	    			if(clinic ==null){
//	    				
//	    				  Intent intent = new Intent(this,MainActivity.class);
//	    				   
//	    				    this.startActivity(intent);
//	    			}
	    	 
	    	 if(clinic !=null){
	    	 textView1.setText(clinic.getName());
	    	 textView2.setText(clinic.getAddress());
	    	 }
		super.onStart();
	}

	public static void start(Context context, Clinic clinic2) {
		clinic=clinic2;
		start(context);
		
	}

}
