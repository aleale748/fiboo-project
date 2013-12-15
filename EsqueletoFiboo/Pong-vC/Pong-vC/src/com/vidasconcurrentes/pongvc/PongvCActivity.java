package com.vidasconcurrentes.pongvc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PongvCActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        
		TextView play = (TextView)findViewById(R.id.play_button);
		play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), R.string.menu_play, Toast.LENGTH_SHORT).show();
			}
		});
        
        TextView options = (TextView)findViewById(R.id.options_button);
        options.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), R.string.menu_options, Toast.LENGTH_SHORT).show();
			}
		});
        
        TextView exit = (TextView)findViewById(R.id.exit_button);
        exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), R.string.menu_exit, Toast.LENGTH_SHORT).show();
			}
		});
        
        ImageView logo = (ImageView)findViewById(R.id.imageView1);
        logo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.vidasconcurrentes.blogspot.com"));  
				startActivity(viewIntent);
			}
		});
    }
}