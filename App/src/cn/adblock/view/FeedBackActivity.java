package cn.adblock.view;

import cn.adblock.R;
import cn.adblock.R.layout;
import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FeedBackActivity extends BaseActivity implements OnClickListener, OnFocusChangeListener {
	
	private EditText edtFeedback;
	private Button btnSubmit;
	private int flag = 0;

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
		edtFeedback.setOnFocusChangeListener(this);
	}
	
	public void onBack(View v) {
		this.finish();
	}

	@Override
	public void onClick(View v) {
		int vid = v.getId();
		switch (vid) {
		case R.id.afb_btn_submit:
			if (flag == 0 && !edtFeedback.getText().toString().trim().equals("")) {
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
				toast.setDuration(Toast.LENGTH_SHORT); 
				//让Toast显示为我们自定义的样子 
				toast.setView(layout); 
				toast.show(); 
				flag = 1;
				break;
			}
			if (flag == 1) {
				//获取LayoutInflater对象，该对象能把XML文件转换为与之一直的View对象 
				LayoutInflater inflater = getLayoutInflater(); 
				//根据指定的布局文件创建一个具有层级关系的View对象 
				//第二个参数为View对象的根节点，即LinearLayout的ID 
				View layout = inflater.inflate(R.layout.view_toast, (ViewGroup) findViewById(R.id.view_toast_root));
				//查找ImageView控件 
				//注意是在layout中查找 
				TextView text = (TextView) layout.findViewById(R.id.text); 
				text.setText("请勿连续操作"); 
				Toast toast = new Toast(getApplicationContext()); 
				//设置Toast显示位置(起点位置,水平向右位移,垂直向下位移) 
//			toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1150); 
				toast.setDuration(Toast.LENGTH_SHORT); 
				//让Toast显示为我们自定义的样子 
				toast.setView(layout); 
				toast.show(); 
				break;
			}
			if (edtFeedback.getText().toString().trim().equals("")) {
				//获取LayoutInflater对象，该对象能把XML文件转换为与之一直的View对象 
				LayoutInflater inflater = getLayoutInflater(); 
				//根据指定的布局文件创建一个具有层级关系的View对象 
				//第二个参数为View对象的根节点，即LinearLayout的ID 
				View layout = inflater.inflate(R.layout.view_toast, (ViewGroup) findViewById(R.id.view_toast_root));
				//查找ImageView控件 
				//注意是在layout中查找 
				TextView text = (TextView) layout.findViewById(R.id.text); 
				text.setText("您输入的内容为空，请重新输入"); 
				Toast toast = new Toast(getApplicationContext()); 
				//设置Toast显示位置(起点位置,水平向右位移,垂直向下位移) 
//			toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1150); 
				toast.setDuration(Toast.LENGTH_SHORT); 
				//让Toast显示为我们自定义的样子 
				toast.setView(layout); 
				toast.show(); 
				break;
			}
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		EditText _v=(EditText)v;
        if (!hasFocus) {// 失去焦点
            _v.setHint(_v.getTag().toString());
        } else {
            String hint=_v.getHint().toString();
            _v.setTag(hint);
            _v.setHint("");
        }
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		flag = 0;
	}
	
}
