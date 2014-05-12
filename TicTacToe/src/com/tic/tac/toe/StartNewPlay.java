package com.tic.tac.toe;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

public class StartNewPlay extends ActionBarActivity {
	
	int player, winner, score1, score2;
	private int[][] tttBoard = new int[3][3];
	private String selectSymbol1, selectSymbol2, winnerMessage;
	private static final String TAG = "Test";
	final Context con = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_new_play);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Intent intent = getIntent();
		selectSymbol1 = intent.getStringExtra(SelectSymbol.Selected_Symbol1);
		selectSymbol2 = intent.getStringExtra(SelectSymbol.Selected_Symbol2);
		player = 1;
		score1 = score2 = 0;
		clearArray();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_new_play, menu);
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
	
	public void buttonClicked(View v){
		Button button123 = (Button) v;
		button123.setClickable(false);
		button123.setBackgroundColor(Color.BLACK);
		String tagValue = (String) v.getTag();
		int tagValueInt = Integer.parseInt(tagValue);
		int i = tagValueInt % 10;
		int j = tagValueInt / 10;

		tttBoard[i][j] = player;

		if(display_winner()){
			selectDialog();
		}
		
		button123.setTextColor(Color.WHITE);
		button123.setTextSize(45);
		
		if(player == 1){
			button123.setText(selectSymbol1);
		} else{
			button123.setText(selectSymbol2);
		}
		
		player = get_player();
		
		if(check_winner() == 0 && !check_Tboardfull()){
			Toast.makeText(getApplicationContext(), "Player" + player +"'s Turn", Toast.LENGTH_SHORT).show();
		}
	}
	
	private boolean display_winner(){
		int win = check_winner();
		
		if (win == 0 && check_Tboardfull()){
			winnerMessage = "It's a tie!!!";
			return true;
		} else if(win == 1){
			winnerMessage = "Winner - Player 1";
			score1 += 1;
			return true;
		}else if(win == 2){
			winnerMessage = "Winner - Player 2";
			score2 += 1;
			return true;
		}
		return false;
	}
	
	private int get_player(){
		if(player == 1){
			player = 2;
		}else{
			player = 1;
		}
		
		return player;
	}
	
	private int check_winner() {
		winner = 0;
		
		if(tttBoard[0][0] == tttBoard[0][1] && tttBoard[0][1] == tttBoard[0][2] && tttBoard[0][0] != 0){
			winner = tttBoard[0][0];
		}else if(tttBoard[1][0] == (tttBoard[1][1]) && tttBoard[1][1] == (tttBoard[1][2]) && tttBoard[1][0] != 0){
			winner = tttBoard[1][0];
		}else if(tttBoard[2][0] == (tttBoard[2][1]) && tttBoard[2][1] == (tttBoard[2][2]) && tttBoard[2][0] != 0){
			winner = tttBoard[2][0];
		}else if(tttBoard[0][0] == (tttBoard[1][0]) && tttBoard[1][0] == (tttBoard[2][0]) && tttBoard[0][0] != 0){
			winner = tttBoard[0][0];
		}else if(tttBoard[0][1] == (tttBoard[1][1]) && tttBoard[1][1] == (tttBoard[2][1]) && tttBoard[0][1] != 0){
			winner = tttBoard[0][1];
		}else if(tttBoard[0][2] == (tttBoard[1][2]) && tttBoard[1][2] == (tttBoard[2][2]) && tttBoard[0][2] != 0){
			winner = tttBoard[0][2];
		}else if(tttBoard[0][0] == (tttBoard[1][1]) && tttBoard[1][1] == (tttBoard[2][2]) && tttBoard[0][0] != 0){
			winner = tttBoard[0][0];
		}else if(tttBoard[0][2] == (tttBoard[1][1]) && tttBoard[1][1] == (tttBoard[2][0]) && tttBoard[0][2] != 0){
			winner = tttBoard[0][2];
		}
		return winner;
	}

	public boolean check_Tboardfull(){
		
		for(int i = 0; i < tttBoard.length; i++ ){
			for (int j = 0; j < tttBoard.length; j++){
				if(tttBoard[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	public void setButtonActive(){
		Button button11 = (Button) findViewById(R.id.button11);
		Button button12 = (Button) findViewById(R.id.button12);
		Button button13 = (Button) findViewById(R.id.button13);
		Button button21 = (Button) findViewById(R.id.button21);
		Button button22 = (Button) findViewById(R.id.button22);
		Button button23 = (Button) findViewById(R.id.button23);
		Button button31 = (Button) findViewById(R.id.button31);
		Button button32 = (Button) findViewById(R.id.button32);
		Button button33 = (Button) findViewById(R.id.button33);
		
		button11.setClickable(true);
		button12.setClickable(true);
		button13.setClickable(true);
		button21.setClickable(true);
		button22.setClickable(true);
		button23.setClickable(true);
		button31.setClickable(true);
		button32.setClickable(true);
		button33.setClickable(true);
		
		button11.setText("");
		button12.setText("");
		button13.setText("");
		button21.setText("");
		button22.setText("");
		button23.setText("");
		button31.setText("");
		button32.setText("");
		button33.setText("");
	}
	
	public void clearArray(){
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				tttBoard[i][j] = 0;
			}
		}
	}
	
	public void clearScore(){
		score1 = score2 = 0;
	}
	
	public void startNewGame(View view){
		Intent i = new Intent(getApplicationContext (), StartNewPlay.class);
		i.putExtra(SelectSymbol.Selected_Symbol1, selectSymbol1);
		i.putExtra(SelectSymbol.Selected_Symbol2, selectSymbol2);
		startActivity(i);
		//setButtonActive();
		//clearArray();
	}
	
	public void selectDialog(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);
			alertDialogBuilder.setTitle(winnerMessage);
			alertDialogBuilder
				.setMessage("What would you like to do?!!")
				.setCancelable(false)
				.setPositiveButton("New Game",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						startActivity(new Intent(getApplicationContext (), SelectSymbol.class));
					}
				  })
				.setNegativeButton("Main Menu",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						startActivity(new Intent(getApplicationContext (), TicTacToeActivity.class));
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
	}
	
	public void onPause() {
	    super.onPause();
	}
	
	public void onResume() {
	    super.onResume(); 
	}
	
	public void onDestroy() {
	    super.onDestroy();
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
			View rootView = inflater.inflate(R.layout.fragment_start_new_play,
					container, false);
			return rootView;
		}
	}

}
