package com.lucentinsight.mclinicplus;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.widget.Toast;

import com.lucentinsight.mclinicplus.dao.ClinicDAO;
import com.lucentinsight.mclinicplus.dao.impl.ClinicJSONDAO;
import com.lucentinsight.mclinicplus.data.Clinic;

public class Application {

	private static volatile Application instance = null;
	private static Map<String, Clinic> clinicList=null;
	private static String sID = null;
	private static final String INSTALLATION = "INSTALLATION";
	private static Intent mapIntent;
	private static Properties prop;
	private static int callCount;
	private static int appointmentCount;
	private static int offlineCallCount;
	private static int offlineAppointmentCount;
	private static Typeface typeFace;
	private static Context context;
	
	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		Application.context = context;
	}

	public static Typeface getTypeFace() {
		
		return typeFace;
	}

	public static void setTypeFace(Typeface typeFace) {
		Application.typeFace = typeFace;
	}

	//typeFace=Typeface.createFromAsset(getAssets(),"ZAWGYIONE2008.TTF");
	public static int getCallCount() {
		return callCount;
	}

	public static void setCallCount(int callCount) {
		Application.callCount = callCount;
	}

	public static int getAppointmentCount() {
		return appointmentCount;
	}

	public static void setAppointmentCount(int appointmentCount) {
		Application.appointmentCount = appointmentCount;
	}

	public static int getOfflineCallCount() {
		return offlineCallCount;
	}

	public static void setOfflineCallCount(int offlineCallCount) {
		Application.offlineCallCount = offlineCallCount;
	}

	public static int getOfflineAppointmentCount() {
		return offlineAppointmentCount;
	}

	public static void setOfflineAppointmentCount(int offlineAppointmentCount) {
		Application.offlineAppointmentCount = offlineAppointmentCount;
	}

	private Application() {
		 prop = new Properties();
	}

	public void createMapIntent(Context context){
		mapIntent = new Intent(context, MapActivity.class);
	}
	
	public static Intent getMapIntent(){
		return mapIntent;
	}
	 public static Application getInstance() {
	        if (instance == null) {
	            synchronized (Application.class) {
	                instance = new Application();
	            }
	        }
	        return instance;
	    }

	public static Map<String, Clinic> getClinicList()  {
//		if(clinicList==null){
//			  File data = new File(context.getFilesDir(), "clinics.json");
//				InputStream is = new FileInputStream(data);
//				
//				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//		        StringBuilder out = new StringBuilder();
//		        String line;
//		        while ((line = reader.readLine()) != null) {
//		            out.append(line);
//		        }
//		        reader.close();
//			ClinicDAO clinicdao = new ClinicJSONDAO();
//			 getInstance().setClinicList(clinicdao.getAllClinics(out.toString()));
//		}
		return clinicList;
	}

	public static Map<String, Clinic> getClinicList(Context context) throws IOException  {
			if(clinicList==null){
				  File data = new File(context.getFilesDir(), "clinics.json");
					InputStream is = new FileInputStream(data);
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			        StringBuilder out = new StringBuilder();
			        String line;
			        while ((line = reader.readLine()) != null) {
			            out.append(line);
			        }
			        reader.close();
				ClinicDAO clinicdao = new ClinicJSONDAO();
				 getInstance().setClinicList(clinicdao.getAllClinics(out.toString()));
			}
			return clinicList;
		}

	public static void setClinicList(Map<String, Clinic> clinicList) {
		Application.clinicList = clinicList;
	}

	public synchronized static String id(Context context) {
        if (sID == null) {  
            File installation = new File(context.getFilesDir(), INSTALLATION);
            File data = new File(context.getFilesDir(), "clinics.json");
           
            try {
              if (!installation.exists()){
                    writeInstallationFile(installation);
                String clinicData = "[{ \"name\":\"SSC Hospital\",\"address\":\"#7 Shwegondaing Rd,Yangon 11201, Myanmar (Burma)\",\"lat\":\"16.81082\",\"lang\":\"96.16556\",\"contactNo\":\"+95 9 432 02362\",\"doctors\":[{}]},{ \"name\":\"Bahosi Clinic\",\"address\":\"B 1/6 - 31/36 - 37/38, Bahosi Housing Complex , Wardan Street , Lanmadaw Township, Yangon\",\"lat\":\"16.7795\",\"lang\":\"96.139854\",\"contactNo\":\"+95 1 2300502\",\"doctors\":[{}]},{ \"name\":\"Asia Royal Cardiac & Medical Care Centre\",\"address\":\"No. 14, Baho Street ,Sanchaung Township ,Yangon, Myanmar.\",\"lat\":\"16.79902\",\"lang\":\"96.131154\",\"contactNo\":\"+95 1 538055\",\"doctors\":[{},{ \"name\":\"Prof. Aung Kyaw Zaw\",\"qualification\":\"M.B.,B.S (Ygn), M.Med.Sc (Int.Med),F.A.C.T.M(Aus), M.R.C.P(UK), F.R.C.P (Edin), Dr. Med.Sc (Cardiology)\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"TUE\",\"startTime\":\"10 AM\",\"endTime\":\"-\"},{ \"day\":\"FRI\",\"startTime\":\"10 AM\",\"endTime\":\"-\"},{ \"day\":\"SUN\",\"startTime\":\"10 AM\",\"endTime\":\"-\"}]},{ \"name\":\"Prof. Aung\",\"qualification\":\"\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"TUE\",\"startTime\":\"11:30 AM\",\"endTime\":\"1 PM\"},{ \"day\":\"WED\",\"startTime\":\"11:30 AM\",\"endTime\":\"1 PM\"},{ \"day\":\"THU\",\"startTime\":\"11:30 AM\",\"endTime\":\"1 PM\"}]},{ \"name\":\"Prof. Khin May San\",\"qualification\":\"\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"MON\",\"startTime\":\"11 AM\",\"endTime\":\"2:30 PM\"},{ \"day\":\"TUE\",\"startTime\":\"11 AM\",\"endTime\":\"2:30 PM\"},{ \"day\":\"WED\",\"startTime\":\"11 AM\",\"endTime\":\"2:30 PM\"},{ \"day\":\"THU\",\"startTime\":\"11 AM\",\"endTime\":\"2:30 PM\"},{ \"day\":\"FRI\",\"startTime\":\"11 AM\",\"endTime\":\"2:30 PM\"},{ \"day\":\"SAT\",\"startTime\":\"8 AM\",\"endTime\":\"11 AM\"},{ \"day\":\"SUN\",\"startTime\":\"8 AM\",\"endTime\":\"11 AM\"}]},{ \"name\":\"Asso. Prof. Myo Win\",\"qualification\":\"\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"MON\",\"startTime\":\"5 PM\",\"endTime\":\"7 PM\"},{ \"day\":\"WED\",\"startTime\":\"5 PM\",\"endTime\":\"7 PM\"},{ \"day\":\"FRI\",\"startTime\":\"5 PM\",\"endTime\":\"7 PM\"}]},{ \"name\":\"Prof. San Lwin\",\"qualification\":\"\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"MON\",\"startTime\":\"3 PM\",\"endTime\":\"6 PM\"},{ \"day\":\"FRI\",\"startTime\":\"3 PM\",\"endTime\":\"6 PM\"}]},{ \"name\":\"Prof. Tin Latt\",\"qualification\":\"\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"MON\",\"startTime\":\"8 AM\",\"endTime\":\"10 AM\"},{ \"day\":\"TUE\",\"startTime\":\"8 PM\",\"endTime\":\"9:30 PM\"},{ \"day\":\"WED\",\"startTime\":\"8 AM\",\"endTime\":\"10 AM\"},{ \"day\":\"THU\",\"startTime\":\"8 PM\",\"endTime\":\"9:30 PM\"},{ \"day\":\"FRI\",\"startTime\":\"8 AM\",\"endTime\":\"10 AM\"},{ \"day\":\"SAT\",\"startTime\":\"8 PM\",\"endTime\":\"9:30 PM\"}]},{ \"name\":\"Prof. Myint Soe Win\",\"qualification\":\"\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"MON\",\"startTime\":\"6 PM\",\"endTime\":\"8 PM\"},{ \"day\":\"WED\",\"startTime\":\"7 PM\",\"endTime\":\"9 PM\"},{ \"day\":\"FRI\",\"startTime\":\"6 PM\",\"endTime\":\"8 PM\"}]},{ \"name\":\"Dr.Than Htike Aung\",\"qualification\":\"\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"MON\",\"startTime\":\"9 AM\",\"endTime\":\"12 AM\"},{ \"day\":\"TUE\",\"startTime\":\"9 AM\",\"endTime\":\"12 AM\"},{ \"day\":\"THU\",\"startTime\":\"3 PM\",\"endTime\":\"8 PM\"},{ \"day\":\"FRI\",\"startTime\":\"9 AM\",\"endTime\":\"12 AM\"}]},{ \"name\":\"Prof. Cho Lay Mar\",\"qualification\":\"\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"TUE\",\"startTime\":\"5 PM\",\"endTime\":\"8 PM\"},{ \"day\":\"THU\",\"startTime\":\"5 PM\",\"endTime\":\"8 PM\"}]},{ \"name\":\"Dr.Lwin Tin Aye\",\"qualification\":\"\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"TUE\",\"startTime\":\"4:30 PM\",\"endTime\":\"5:30 PM\"},{ \"day\":\"THU\",\"startTime\":\"4:30 PM\",\"endTime\":\"5:30 PM\"},{ \"day\":\"SAT\",\"startTime\":\"6 PM\",\"endTime\":\"7:30 PM\"}]},{ \"name\":\"Dr Ye Htun Naung\",\"qualification\":\"\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"SAT\",\"startTime\":\"10 AM\",\"endTime\":\"12 AM\"},{ \"day\":\"SUN\",\"startTime\":\"10 AM\",\"endTime\":\"12 AM\"}]},{ \"name\":\"Prof. Ni Ni Win\",\"qualification\":\"\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"TUE\",\"startTime\":\"4:30 PM\",\"endTime\":\"5:30 PM\"},{ \"day\":\"WED\",\"startTime\":\"4:30 PM\",\"endTime\":\"5:30 PM\"},{ \"day\":\"THU\",\"startTime\":\"4:30 PM\",\"endTime\":\"5:30 PM\"},{ \"day\":\"SAT\",\"startTime\":\"6 PM\",\"endTime\":\"7:30 PM\"}]},{ \"name\":\"Dr. Yin Nwe tun\",\"qualification\":\"\",\"specialization\":\"Cardiologists\",\"schedule\":[{},{ \"day\":\"MON\",\"startTime\":\"7 AM\",\"endTime\":\"8 AM\"},{ \"day\":\"TUE\",\"startTime\":\"7 AM\",\"endTime\":\"8 AM\"},{ \"day\":\"WED\",\"startTime\":\"7 AM\",\"endTime\":\"8 AM\"},{ \"day\":\"THU\",\"startTime\":\"7 AM\",\"endTime\":\"8 AM\"},{ \"day\":\"FRI\",\"startTime\":\"7 AM\",\"endTime\":\"8 AM\"},{ \"day\":\"SAT\",\"startTime\":\"7 AM\",\"endTime\":\"8 AM\"},{ \"day\":\"SUN\",\"startTime\":\"7 AM\",\"endTime\":\"8 AM\"}]},{ \"name\":\"Prof. Khing Maung Aye\",\"qualification\":\"\",\"specialization\":\"Cardiac Surgeons\",\"schedule\":[{},{ \"day\":\"MON\",\"startTime\":\"6 PM\",\"endTime\":\"7 PM\"},{ \"day\":\"WED\",\"startTime\":\"6 PM\",\"endTime\":\"7 PM\"},{ \"day\":\"FRI\",\"startTime\":\"6 PM\",\"endTime\":\"7 PM\"}]},{ \"name\":\"Prof. Khing Maung Lwin\",\"qualification\":\"\",\"specialization\":\"Cardiac Surgeons\",\"schedule\":[{},{ \"day\":\"TUE\",\"startTime\":\"9 PM\",\"endTime\":\"-\"},{ \"day\":\"SAT\",\"startTime\":\"9 PM\",\"endTime\":\"-\"}]},{ \"name\":\"Dr. Aung Thu\",\"qualification\":\"\",\"specialization\":\"Cardiac Surgeons\",\"schedule\":[{},{ \"day\":\"THU\",\"startTime\":\"7 PM\",\"endTime\":\"8 PM\"},{ \"day\":\"SAT\",\"startTime\":\"9 AM\",\"endTime\":\"11 AM\"}]},{ \"name\":\"Dr. Cho Mar Lwin\",\"qualification\":\"\",\"specialization\":\"General Physicians\",\"schedule\":[{},{ \"day\":\"TUE\",\"startTime\":\"7 PM\",\"endTime\":\"-\"}]}]}]";
                writedataFile(data,clinicData);
                }
        //        instance.setDatavarsion("1",context);
                sID = readFile(installation);
                if(sID == null){
                	  writeInstallationFile(installation);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sID;
    }
	
	  private static String readFile(File installation) throws IOException {
	        InputStream in = new FileInputStream(installation);
	        prop.load(in);
	        in.close();
	        try{
	        callCount=Integer.parseInt(prop.getProperty("callCount"));
	        appointmentCount=Integer.parseInt(prop.getProperty("appointmentCount"));
	        offlineCallCount=Integer.parseInt(prop.getProperty("offlineCallCount"));
	        offlineAppointmentCount=Integer.parseInt(prop.getProperty("offlineAppointmentCount"));
	        }catch(NumberFormatException e){
//	        	11-14 14:13:17.158: E/AndroidRuntime(22046): Caused by: java.lang.NumberFormatException: Invalid int: "null"
 	
	        }
	        return prop.getProperty("id");
	    }

	    private static void writeInstallationFile(File installation) throws IOException {
	        FileOutputStream out = new FileOutputStream(installation);
	        String id = UUID.randomUUID().toString();
	        prop.setProperty("id", id);
	        prop.setProperty("dataVersion", "1");
	        prop.setProperty("callCount", "0");
	        prop.setProperty("appointmentCount", "0");
	        prop.setProperty("offlineCallCount", "0");
	        prop.setProperty("offlineAppointmentCount", "0");
	        prop.store(out, null);//callCount=1&appointmentCount=1&offlineCallCount=1&offlineAppointmentCount=2
	        //out.write(id.getBytes());
	        out.close();
	    }
	    
	    public static void updateStats(Context context) throws IOException{
	    	  File installation = new File(context.getFilesDir(), INSTALLATION);
	    	  FileOutputStream out = new FileOutputStream(installation);
		        prop.setProperty("callCount", Integer.toString(callCount));
		        prop.setProperty("appointmentCount", Integer.toString(appointmentCount));
		        prop.setProperty("offlineCallCount", Integer.toString(offlineCallCount));
		        prop.setProperty("offlineAppointmentCount", Integer.toString(offlineAppointmentCount));
		        prop.store(out, null);
	    }
	    
	    private static void writedataFile(File data,String clinicData) throws IOException {
	    	System.out.println("clinicData"+clinicData);
	    //	data.delete();
	        FileOutputStream out = new FileOutputStream(data);
	       
	        out.write(clinicData.getBytes());
	        out.close();
	    }
	    
	    public static void call(String phno,Context context){
	    	if(isNetworkAvailable(context)){
			callCount=callCount+1;
			}else{
				offlineCallCount=offlineCallCount+1;
			}
	    	  Intent callIntent = new Intent(Intent.ACTION_CALL);          
	          callIntent.setData(Uri.parse("tel:"+phno));          
	          context.startActivity(callIntent);  
	    }
	    
	    public static void makeAppointment(Context context){
	    	if(isNetworkAvailable(context)){
				appointmentCount=appointmentCount+1;
			}else{
				offlineAppointmentCount=offlineAppointmentCount+1;
			}
	    	Toast.makeText(context, "Make Appointment feature will comming soon to our app. Thank you for your interest. Please use call button to directaly contact the clinic for now",
	             Toast.LENGTH_LONG).show();
	    }
	    
	    public String getDataversion(){
	    	return prop.getProperty("dataVersion");
	    }
	    
	    public void setDatavarsion(String dataVersion,Context context) throws IOException{
	    	File installation = new File(context.getFilesDir(), INSTALLATION);
	    	  FileOutputStream out = new FileOutputStream(installation);
	    	prop.setProperty("dataVersion", dataVersion);
	    	 prop.store(out, null);
	    }
	    
	    public void getOnlinedatafile(Context context) throws IOException{
	    	URL url = new URL("http://mclinic.yourmyanmarsme.com/php/getClinicList.php");
	    	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        	urlConnection.setRequestMethod("POST");
        	urlConnection.setDoOutput(true);

        	//and connect!

        	urlConnection.connect();
        	InputStream is= urlConnection.getInputStream();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        	 StringBuilder out = new StringBuilder();
 	        String line;
 	        while ((line = reader.readLine()) != null) {
 	            out.append(line);
 	        }
//        	String clinucData =reader.readLine();
//        	System.out.println("clinicdata:"+out.toString());
        	if(out.length()!=0){
        	File data = new File(context.getFilesDir(), "clinics.json");
        	 writedataFile(data,out.toString());
        	}
	    }
	    
	    public void uploadStats() throws IOException{
	    	URL url = new URL("http://mclinic.yourmyanmarsme.com/php/stats_edit.php");
	    	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        	urlConnection.setRequestMethod("POST");
        	urlConnection.setDoOutput(true);
        	urlConnection.setDoInput(true);
        	urlConnection.connect();
        	//and connect!
        	List<NameValuePair> params = new ArrayList<NameValuePair>();
        	params.add(new BasicNameValuePair("installationId", sID));
        	params.add(new BasicNameValuePair("callCount", prop.getProperty("callCount")));
        	params.add(new BasicNameValuePair("appointmentCount", prop.getProperty("appointmentCount")));
        	params.add(new BasicNameValuePair("offlineCallCount", prop.getProperty("offlineCallCount")));
        	params.add(new BasicNameValuePair("offlineAppointmentCount", prop.getProperty("offlineAppointmentCount")));
        	OutputStream os = urlConnection.getOutputStream();
        	BufferedWriter writer = new BufferedWriter(
        	        new OutputStreamWriter(os, "UTF-8"));
        	writer.write(getQuery(params));
        	writer.flush();
        	writer.close();
        	os.close();
        
        	InputStream is= urlConnection.getInputStream();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        	 StringBuilder out = new StringBuilder();
 	        String line;
 	        while ((line = reader.readLine()) != null) {
 	            out.append(line);
 	        }
        	//urlConnection.getOutputStream().write( ("installationId=" + sID).getBytes());
        	
	    }
	    
	    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
	    {
	        StringBuilder result = new StringBuilder();
	        boolean first = true;

	        for (NameValuePair pair : params)
	        {
	            if (first)
	                first = false;
	            else
	                result.append("&");

	            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
	            result.append("=");
	            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
	        }

	        return result.toString();
	    }
	    
	    public static boolean isNetworkAvailable(Context context) {
		    ConnectivityManager connectivityManager 
		          = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
		}
}
