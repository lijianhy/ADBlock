package com.ad.block.view;

import com.ad.block.R;
import com.ad.block.R.layout;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FeedBackActivity extends BaseActivity implements OnClickListener {
	
	private EditText edtFeedback;
	private Button btnSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed_back);
		initView();
		setListener();
	}
	
	private void initView(){
		edtFeedback = (EditText) findViewById(R.id.afb_edt_feedback);
		btnSubmit = (Button) findViewById(R.id.afb_btn_submit);
	}
	
	private void setListener(){
		btnSubmit.setOnClickListener(this);
	}
	
	public void onBack(View v) {
		this.finish();
	}

	@Override
	public void onClick(View v) {
		int vid = v.getId();
		switch (vid) {
		case R.id.afb_btn_submit:
			
			break;
		}
	}
	
}
