package com.tic.tac.toe;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.os.Build;

public class SelectSymbol extends ActionBarActivity {
	
	public final static String Selected_Symbol1 = "com.tic.tac.toe.MESSAGE1";
	public final static String Selected_Symbol2 = "com.tic.tac.toe.MESSAGE";
	private RadioGroup symbolGroup1, symbolGroup2;
	private RadioButton selectedSymbol1, selectedSymbol2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_symbol);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_symbol, menu);
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
	
	public void startThePlay(View v){
		symbolGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
		symbolGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
		int check1 = symbolGroup1.getCheckedRadioButtonId();
		int check2 = symbolGroup2.getCheckedRadioButtonId();
		if(check1 == -1 || check2 == -1){
			Toast.makeText(getApplicationContext(), "Please select a Symbol for each Player", Toast.LENGTH_SHORT).show();
		} else{
			selectedSymbol1 = (RadioButton) findViewById(symbolGroup1.getCheckedRadioButtonId());
			selectedSymbol2 = (RadioButton) findViewById(symbolGroup2.getCheckedRadioButtonId());
			Intent startPlay = new Intent(this, StartNewPlay.class);
			startPlay.putExtra(Selected_Symbol1, selectedSymbol1.getText());
			startPlay.putExtra(Selected_Symbol2, selectedSymbol2.getText());
			startActivity(startPlay);
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_select_symbol,
					container, false);
			return rootView;
		}
	}

}
