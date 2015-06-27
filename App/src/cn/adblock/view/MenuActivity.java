package cn.adblock.view;

import cn.adblock.R;
import cn.adblock.R.layout;
import cn.adblock.widgets.ConfirmDialog;
import cn.adblock.widgets.ShareDialog;
import cn.adblock.widgets.ConfirmDialog.ConfirmListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends BaseActivity implements OnClickListener,ConfirmListener {
	
	private View viewSetting;
	private View viewMode;
	private View ViewSuggest;
	private View viewAbout;
	private View viewFeedback;
	private View viewCheckupdate;
	private Button btnLogout;
	
	ConfirmDialog dialog ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		dialog = new ConfirmDialog(this);
		initView();
		setListener();
	}
	
	private void initView(){
		viewAbout = (View) findViewById(R.id.amenu_view_about);
		viewSetting = (View) findViewById(R.id.amenu_view_setting);
		viewMode = (View) findViewById(R.id.amenu_view_mode);
		ViewSuggest = (View) findViewById(R.id.amenu_view_suggest);
		viewFeedback = (View) findViewById(R.id.amenu_view_feedback);
		viewCheckupdate = (View) findViewById(R.id.amenu_view_checkupdate);
		btnLogout = (Button) findViewById(R.id.amenu_btn_logout);
	}
	
	private void setListener(){
		viewAbout.setOnClickListener(this);
		viewCheckupdate.setOnClickListener(this);
		viewSetting.setOnClickListener(this);
		viewMode.setOnClickListener(this);
		ViewSuggest.setOnClickListener(this);
		viewFeedback.setOnClickListener(this);
		btnLogout.setOnClickListener(this);
		dialog.setConfirmListener(this);
	}

	@Override
	public void onClick(View v) {
		int vid = v.getId();
		switch (vid) {
		case R.id.amenu_view_about:
			onAboutClick();
			break;
		case R.id.amenu_view_setting:
			onSettingClick();
			break;
		case R.id.amenu_view_mode:
			onModeClick();
			break;
		case R.id.amenu_view_suggest:
			onSuggestClick();
			break;
		case R.id.amenu_view_feedback:
			onFeedbackClick();
			break;
		case R.id.amenu_view_checkupdate:
			onCheckupdateClick();
			break;
		case R.id.amenu_btn_logout:
			onLogoutClick();
			break;
		}
	}
	
	private void onLogoutClick() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("你确定要离开吗？");
		builder.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int whichButton) {
						Intent intent = new Intent(Intent.ACTION_MAIN);  
                        intent.addCategory(Intent.CATEGORY_HOME);  
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
                        startActivity(intent);  
                        android.os.Process.killProcess(android.os.Process.myPid());
						dialog.dismiss();
					}
				});
		builder.setNegativeButton("取消",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int whichButton) {
						dialog.dismiss();
					}
				});
		builder.create().show();
	}

	public void onBack(View v) {
		this.finish();
	}

	private void onCheckupdateClick() {
		dialog.show();
	}

	private void onFeedbackClick() {
		startActivity(new Intent(MenuActivity.this,FeedBackActivity.class));
	}

	private void onSuggestClick() {
		startActivity(new Intent(MenuActivity.this,SoftDetailActivity.class));
	}

	private void onModeClick() {
		startActivity(new Intent(MenuActivity.this,ModeSelcetActivity.class));
	}

	private void onSettingClick() {
		startActivity(new Intent(MenuActivity.this,SettingActivity.class));
	}

	private void onAboutClick() {
		startActivity(new Intent(MenuActivity.this,AboutActivity.class));
	}

	@Override
	public void onConfirmClick() {
		//获取LayoutInflater对象，该对象能把XML文件转换为与之一直的View对象 
		LayoutInflater inflater = getLayoutInflater(); 
		//根据指定的布局文件创建一个具有层级关系的View对象 
		//第二个参数为View对象的根节点，即LinearLayout的ID 
		View layout = inflater.inflate(R.layout.view_toast, (ViewGroup) findViewById(R.id.view_toast_root));
		//查找ImageView控件 
		//注意是在layout中查找 
		TextView text = (TextView) layout.findViewById(R.id.text); 
		text.setText("已经是最新版本....."); 
		Toast toast = new Toast(getApplicationContext()); 
		//设置Toast显示位置(起点位置,水平向右位移,垂直向下位移) 
//		toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1150); 
		toast.setDuration(Toast.LENGTH_SHORT); 
		//让Toast显示为我们自定义的样子 
		toast.setView(layout); 
		toast.show(); 
	}
	
}
