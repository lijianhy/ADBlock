package cn.adblock.view;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.adblock.R;
import cn.adblock.adapter.ViewPagerAdapter;
import cn.adblock.app.Constans;
import cn.adblock.utils.SharedPreferencesUtils;

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
	private View[] texts = new View[4];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferencesUtils.setParam(this, Constans.KEY_IS_FIRST, false);
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
		for (int i = 0; i < pics.length - 1; i++) {
			ImageView iv = new ImageView(this);
			iv.setId(IMG_RID+i);
			iv.setLayoutParams(lp);
			iv.setImageResource(pics[i]);
			views.add(iv);
		}
		View view = LayoutInflater.from(this).inflate(R.layout.view_gui_start, null);
		view.findViewById(R.id.vgs_view_start).setOnClickListener(this);
		texts[0] = view.findViewById(R.id.vgs_img_one);
		texts[1] = view.findViewById(R.id.vgs_img_two);
		texts[2] = view.findViewById(R.id.vgs_img_three);
		texts[3] = view.findViewById(R.id.vgs_img_four);
		views.add(view);
		vpAdapter = new ViewPagerAdapter(views);
		viewPager.setAdapter(vpAdapter);
		viewPager.setOnPageChangeListener(this);
		dots[0].setSelected(true);
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
		if (v.getId() == R.id.vgs_view_start) {
			for (int i = 0; i < texts.length; i++) {
				ObjectAnimator oa = ObjectAnimator.ofFloat(texts[i], "haha", 1f,1.5f,1f);
				oa.addUpdateListener(new ScaleAnimatorListener(texts[i]));
				oa.setStartDelay(i*100);
				oa.setDuration(300);
				if (i == texts.length - 1) {
					oa.addListener(new AnimatorListenerAdapter(){
						@Override
						public void onAnimationEnd(Animator animation) {
							super.onAnimationEnd(animation);
							startActivity(new Intent(GuiActivity.this, MainActivity.class));
							finish();
						}
					});
				}
				oa.start();
			}
		}
	}
	
	class ScaleAnimatorListener implements AnimatorUpdateListener{
		View view;
		public ScaleAnimatorListener(View v) {
			view = v;
		}
		
		@Override
		public void onAnimationUpdate(ValueAnimator animation) {
			float value = (float) animation.getAnimatedValue();
			view.setScaleX(value);
			view.setScaleY(value);
		}
	}

}
