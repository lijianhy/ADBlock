package cn.adblock.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import cn.adblock.R;
import cn.adblock.utils.ToastUtil;

public class FeedBackActivity extends BaseActivity implements OnClickListener,
		OnFocusChangeListener {

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

	private void initView() {
		edtFeedback = (EditText) findViewById(R.id.afb_edt_feedback);
		btnSubmit = (Button) findViewById(R.id.afb_btn_submit);
	}

	private void setListener() {
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
			if (flag == 0
					&& !edtFeedback.getText().toString().trim().equals("")) {
				ToastUtil.show(this, "意见反馈成功，谢谢");
				flag = 1;
				break;
			}
			if (flag == 1) {
				ToastUtil.show(this, "请勿连续操作");
				break;
			}
			if (edtFeedback.getText().toString().trim().equals("")) {
				ToastUtil.show(this, "您输入的内容为空，请重新输入");
				break;
			}
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		ToastUtil.cancel();
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		EditText _v = (EditText) v;
		if (!hasFocus) {// 失去焦点
			_v.setHint(_v.getTag().toString());
		} else {
			String hint = _v.getHint().toString();
			_v.setTag(hint);
			_v.setHint("");
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		flag = 0;
	}

}
