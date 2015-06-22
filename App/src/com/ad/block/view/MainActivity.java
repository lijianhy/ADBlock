package com.ad.block.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.ad.block.R;

public class MainActivity extends Activity {
	
	private ImageView imgMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imgMenu = (ImageView) findViewById(R.id.amain_img_menu);
		imgMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,MenuActivity.class));
			}
		});
	}

}
