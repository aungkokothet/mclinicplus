package com.lucentinsight.mclinicplus;


import java.io.IOException;
import java.util.Date;

import java.util.Map;

//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lucentinsight.mclinicplus.R;
import com.lucentinsight.mclinicplus.data.Clinic;

import android.support.v7.app.ActionBarActivity;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends ActionBarActivity 
//implements
//GooglePlayServicesClient.ConnectionCallbacks,
//GooglePlayServicesClient.OnConnectionFailedListener 
{

	 LocationClient   mLocationClient ;
	 Location location=null;
     String phone;
     Date start;
     Date end;
     private static Clinic clinic;
	private static final Map<String, Clinic> clinicsMap=Application.getClinicList();
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 start = new Date();
//	    mLocationClient = new LocationClient(this, this, this);		 
//			 mLocationClient.connect();
		
		setContentView(R.layout.activity_map);
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ma, menu);
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
		if (id == R.id.Lists) {
			ListActivity.start(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	 public static void start(Context context,Clinic clinic) {
//	        Intent intent = new Intent(context, MapActivity.class);
		 MapActivity.clinic=clinic;
	        context.startActivity(Application.getMapIntent());
	        
	    }
	 
	 private void centerMapOnMyLocation() {
		
			 GoogleMap map = ((MapFragment) getFragmentManager()
	                .findFragmentById(R.id.fragment1)).getMap();
//		    map.setMyLocationEnabled(true);
		   
		    Marker marker=null;
		    for (Clinic clinic : clinicsMap.values()) {
		    	marker =map.addMarker((new MarkerOptions()
		        .position(new LatLng(clinic.getLat(), clinic.getLang()))
		        .title(clinic.getName())));
				marker.showInfoWindow();
				if(this.clinic == null){
				this.clinic=clinic;
				}
			}
		    Log.d("Location Updates",map.toString());
		    
		    OnMarkerClickListener onMarkerCLickListener= new OnMarkerClickListener() {
				
				@Override
				public boolean onMarkerClick(Marker arg0) {
					clinic=clinicsMap.get(arg0.getTitle());
			    	 TextView  textView1 = (TextView)findViewById(R.id.textView1);
			    	 TextView  textView2 = (TextView)findViewById(R.id.textView2);
			    	 textView1.setText(arg0.getTitle());
			    	 textView2.setText(clinic.getAddress());
			    	 phone=clinicsMap.get(arg0.getTitle()).getContactNo();
					return false;
				}
			};
			map.setOnMarkerClickListener(onMarkerCLickListener);
//		
//		 LatLng myLocation=null;
//		    if (location != null) {
//		    	myLocation = new LatLng(location.getLatitude(),
//		                location.getLongitude());
		    	
		    	 map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(clinic.getLat(),clinic.getLang()),
				            15));
		    	 TextView  textView1 = (TextView)findViewById(R.id.textView1);
		    	 TextView  textView2 = (TextView)findViewById(R.id.textView2);
		    	 textView1.setText(clinic.getName());
		    	 textView2.setText(clinic.getAddress());
//		    }
		    
		}
	 
	 
//	 private boolean servicesConnected() {
//	        // Check that Google Play services is available
//	        int resultCode =
//	                GooglePlayServicesUtil.
//	                        isGooglePlayServicesAvailable(this);
//	        // If Google Play services is available
//	        if (ConnectionResult.SUCCESS == resultCode) {
//	            // In debug mode, log the status
//	            Log.d("Location Updates",
//	                    "Google Play services is available.");
//	            // Continue
//	            return true;
//	        // Google Play services was not available for some reason.
//	        // resultCode holds the error code.
//	        } else {
//	            // Get the error dialog from Google Play services
//	            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
//	                    resultCode,
//	                    this,
//	                    CONNECTION_FAILURE_RESOLUTION_REQUEST);
//
//	            // If Google Play services can provide an error dialog
//	            if (errorDialog != null) {
//	                // Create a new DialogFragment for the error dialog
////	                ErrorDialogFragment errorFragment =
////	                        new ErrorDialogFragment();
//	                // Set the dialog in the DialogFragment
////	                errorFragment.setDialog(errorDialog);
//	                // Show the error dialog in the DialogFragment
////	                errorFragment.show(getSupportFragmentManager(),
////	                        "Location Updates");
//	            }
//	        }
//	        return false;
//	 }

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		centerMapOnMyLocation();
		end = new Date();
		System.out.println("time taken:"+(end.getTime()-start.getTime()));
	}

//	@Override
//	public void onConnectionFailed(ConnectionResult arg0) {
//		// TODO Auto-generated method stub
//		Log.d("Location Updates","Disconnected. Please re-connect.");
//		Toast.makeText(this, "Disconnected. Please re-connect.",
//                Toast.LENGTH_SHORT).show();
//	}

//	@Override
//	public void onConnected(Bundle arg0) {
//		// TODO Auto-generated method stub
//		 Log.d("Location Updates","Connected");
////		 Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
////		 location = mLocationClient.getLastLocation();
//	
//	}

//	@Override
//	public void onDisconnected() {
//		// TODO Auto-generated method stub
//		
//	}
	
	  public void call(View view) {   
          Application.call(phone, this);
 }
 
 public void makeAppointment(View view) {   
	 Application.makeAppointment(this);
}
 public void clinicDetails(View view) {   
	 ClinicActivity.start(MapActivity.this,clinic);
  //   finish();
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

}
