package com.ad.block.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ad.block.R;
import com.ad.block.adapter.ViewPagerAdapter;
import com.ad.block.app.Constans;
import com.ad.block.utils.SharedPreferencesUtils;

/**
 * TODO
 * 
 * @author Smile<lijianhy1990@gmail.com>
 * @date 2015年6月20日
 */
public class GuiActivity extends BaseActivity implements OnPageChangeListener, OnClickListener {

	private static final int IMG_RID = 0x00FF0066;
	private static final int[] pics = { R.drawable.img_gui1,
			R.drawable.img_gui2, R.drawable.img_gui3, };
	private ViewPager viewPager;
	private View[] dots;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		SharedPreferencesUtils.setParam(this, Constans.KEY_IS_FIRST, false);
		setContentView(R.layout.activity_guid);
		dots = new View[3];
		views = new ArrayList<View>();
		LinearLayout linDots = (LinearLayout) findViewById(R.id.agui_lin_dots);
		viewPager = (ViewPager) findViewById(R.id.agui_viewpager);
		for (int i = 0; i < 3; i++) {
			dots[i] = linDots.getChildAt(i);
		}
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		for (int i = 0; i < pics.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setId(IMG_RID+i);
			iv.setLayoutParams(lp);
			iv.setImageResource(pics[i]);
			iv.setOnClickListener(this);
			views.add(iv);
		}
		vpAdapter = new ViewPagerAdapter(views);
		viewPager.setAdapter(vpAdapter);
		viewPager.setOnPageChangeListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int index) {
		for (int i = 0; i < dots.length; i++) {
			dots[i].setSelected(i == index ? true : false);
		}
	}

	@Override
	public void onClick(View v) {
		final int index = v.getId() - IMG_RID;
		if (index == 2) {
			startActivity(new Intent(this, MainActivity.class));
			finish();
		} else {
			viewPager.setCurrentItem(index+1, true);
		}
	}

}
