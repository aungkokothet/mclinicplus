package com.lucentinsight.mclinicplus;



import com.google.android.gms.common.AccountPicker;
import com.lucentinsight.mclinicplus.R;

import android.support.v7.app.ActionBarActivity;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class LandingActivity extends ActionBarActivity {

	String mEmail; // Received from newChooseAccountIntent(); passed to getToken()
	static final int REQUEST_CODE_PICK_ACCOUNT = 1000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landing);
		
	}

	public void signUpOnClickListener(View view){
		 pickUserAccount();
	}
	
	public void skipListener(View view){
		startMap();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.landing, menu);
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
		return super.onOptionsItemSelected(item);
	}
	
	 public static void start(Context context) {
//		 System.out.println("startlanding");
	        Intent intent = new Intent(context, LandingActivity.class);
	        context.startActivity(intent);
	       
	    }
	 
		private void pickUserAccount() {
		    String[] accountTypes = new String[]{"com.google"};
		    Intent intent = AccountPicker.newChooseAccountIntent(null, null,
		            accountTypes, false, null, null, null, null);
		    startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);
		}
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		    if (requestCode == REQUEST_CODE_PICK_ACCOUNT) {
		        // Receiving a result from the AccountPicker
		        if (resultCode == RESULT_OK) {
		            mEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
		            // With the account name acquired, go get the auth token
//		            data.ge
		            
//		            getUsername();
		            startMap();
		            //Toast.makeText(this, mEmail, Toast.LENGTH_SHORT).show();
		        } else if (resultCode == RESULT_CANCELED) {
		            // The account picker dialog closed without selecting an account.
		            // Notify users that they must pick an account to proceed.
		            Toast.makeText(this, "Please Sign in or Skip to Proceed", Toast.LENGTH_SHORT).show();
		        }
		    }
		    // Later, more code will go here to handle the result from some exceptions...
		}
		
		private void startMap() {
	        MapActivity.start(LandingActivity.this,null);
	        finish();
	    }
}
