package cn.adblock.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.TextView;
import cn.adblock.R;
import cn.adblock.widgets.ExpandableTextView;

public class SoftDetailActivity extends BaseActivity {

	private TextView textSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soft_detail);
		textSize = (TextView) findViewById(R.id.asd_text_size);
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
		ExpandableTextView expand = (ExpandableTextView) findViewById(R.id.asd_expand);
		expand.setText(getString(R.string.soft_detail));
	}

	public void onBack(View v) {
		this.finish();
	}

}
