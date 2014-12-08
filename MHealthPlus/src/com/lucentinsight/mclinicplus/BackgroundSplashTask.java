package com.lucentinsight.mclinicplus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.lucentinsight.mclinicplus.dao.ClinicDAO;
import com.lucentinsight.mclinicplus.dao.impl.ClinicJSONDAO;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

public class BackgroundSplashTask extends AsyncTask{

	private Context context;
	private MainActivity activity;
	public BackgroundSplashTask (Context myContext,MainActivity activity) {
	    this.context = myContext;
	    this.activity=activity;
	}
	
	@Override
	protected Object doInBackground(Object... params) {
		
		try {
//			int a= 500;
//				Thread.sleep(a);
			Application app = Application.getInstance();
	    	System.out.println("Installation ID:"+app.id(context));
	    	BufferedReader reader;
	    	InputStream is;
	    
//			clinicdao.getAllClinics(out.toString());
	       
	        if(app.isNetworkAvailable(context)){
	        	URL url = new URL("http://mclinic.yourmyanmarsme.com/dataversion.html");
	        	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	        	urlConnection.setRequestMethod("GET");

	        	urlConnection.setDoOutput(true);

	        	//and connect!

	        	urlConnection.connect();
	        	is= urlConnection.getInputStream();
	        	reader = new BufferedReader(new InputStreamReader(is));
	        	String dataversion =reader.readLine();
	        	System.out.println(dataversion);
	        	String odataVersion=app.getDataversion();
	        //	System.out.println(odataVersion);
	        	if(!dataversion.equals(odataVersion)){
	        		app.getOnlinedatafile(context);
	        		app.setDatavarsion(dataversion, context);
	        	}
	        	app.uploadStats();
	        }else{
	        	System.out.println("Internet not avaliable");
	        }
	        if(isMapAvailable()){
	        	System.out.println("Map avaliable");
	        }else{
	        	System.out.println("Map not avaliable");
	        }
	        File data = new File(context.getFilesDir(), "clinics.json");
			is = new FileInputStream(data);
			
			reader = new BufferedReader(new InputStreamReader(is));
	        StringBuilder out = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            out.append(line);
	        }
	        reader.close();
//	        System.out.println("json:"+out.toString());
	    	ClinicDAO clinicdao = new ClinicJSONDAO();
	    	app.setClinicList(clinicdao.getAllClinics(out.toString()));
	    	app.createMapIntent(context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//			catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return null;
	} 
	
	@Override
	  protected void onPostExecute(Object result) {
		
		startLanding();
//		System.out.println("after startlanding");
	}
	
	private void startLanding() {
//	        LandingActivity.start(context);
//		MapActivity.start(context);
		ListActivity.start(context);
//	        System.out.println("in startlanding");
	        activity.finish();
    }
	
	
	
	private boolean isMapAvailable(){
		return  new File(context.getExternalCacheDir(), "cache_vts_com.lucentinsight.mclinicplus.0").exists();
	}
}
