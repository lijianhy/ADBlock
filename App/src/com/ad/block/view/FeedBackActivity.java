package com.ad.block.view;

import com.ad.block.R;
import com.ad.block.R.layout;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
			//获取LayoutInflater对象，该对象能把XML文件转换为与之一直的View对象 
			LayoutInflater inflater = getLayoutInflater(); 
			//根据指定的布局文件创建一个具有层级关系的View对象 
			//第二个参数为View对象的根节点，即LinearLayout的ID 
			View layout = inflater.inflate(R.layout.view_toast, (ViewGroup) findViewById(R.id.view_toast_root));
			//查找ImageView控件 
			//注意是在layout中查找 
			TextView text = (TextView) layout.findViewById(R.id.text); 
			text.setText("意见反馈成功，谢谢"); 
			Toast toast = new Toast(getApplicationContext()); 
			//设置Toast显示位置(起点位置,水平向右位移,垂直向下位移) 
//			toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1150); 
			toast.setDuration(Toast.LENGTH_LONG); 
			//让Toast显示为我们自定义的样子 
			toast.setView(layout); 
			toast.show(); 
			break;
		}
	}
	
}
