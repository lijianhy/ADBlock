package cn.adblock.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.adblock.R;
import cn.adblock.widgets.ExpandableTextView;
import cn.adblock.widgets.SaftPopWindow;

public class SoftDetailActivity extends BaseActivity implements OnClickListener {

	private TextView textSize;
	private View viewBack;
	private View viewSaft;
	private ExpandableTextView expand;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soft_detail);
		initView();
		setListener();
		SpannableStringBuilder ss = new SpannableStringBuilder("大小：");
		int length = ss.length();
		ss.append("5.68MB");
		ss.setSpan(new StrikethroughSpan(), length, ss.length(),
				Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		length = ss.length();
		ss.append(" 1.76MB");
		ss.setSpan(new ForegroundColorSpan(Color.parseColor("#FF7F50")),
				length, ss.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		textSize.setText(ss);
		SpannableStringBuilder ssb = new SpannableStringBuilder("缩进");
		ssb.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0,
				ssb.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		ssb.append(getString(R.string.soft_detail));
		expand.setText(ssb);
	}

	public void onBack(View v) {
		this.finish();
	}

	private void initView() {
		textSize = (TextView) findViewById(R.id.asd_text_size);
		expand = (ExpandableTextView) findViewById(R.id.asd_expand);
		viewBack = (View) findViewById(R.id.about_view_back);
		viewSaft = (View) findViewById(R.id.asd_view_saft);
	}

	private void setListener() {
		viewBack.setOnClickListener(this);
		viewSaft.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int vid = v.getId();
		switch (vid) {
		case R.id.about_view_back:
			finish();
			break;
		case R.id.asd_view_saft:
			onSaftClick();
			break;
		}
	}

	private void onSaftClick() {
		int[] location = new int[2];
		viewSaft.getLocationInWindow(location);
		new SaftPopWindow(this).showPopupWindow(viewSaft);
	}

}
