package cn.adblock.widgets;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.adblock.R;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;

/**
 * TODO
 * 
 * @author Smile<lijianhy1990@gmail.com>
 * @date 2015年6月22日
 */
public class ShareDialog extends Dialog implements OnItemClickListener {

	UMSocialService mController = UMServiceFactory
			.getUMSocialService("com.umeng.share");
	private List<SHARE_MEDIA> mPlatforms;

	private MSnsListener mListener;

	public ShareDialog(Context context) {
		this(context, R.style.Dialog_Notitle);
	}

	public ShareDialog(Context context, int theme) {
		super(context, theme);
		setCancelable(true);
		setCanceledOnTouchOutside(true);
		initView(context);
	}

	private void initView(Context context) {
		setContentView(R.layout.dialog_share);
		GridView gv = (GridView) findViewById(R.id.gridview);
		GridAdapter adapter = new GridAdapter();
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(this);
		mListener = new MSnsListener();
		mPlatforms = new ArrayList<SHARE_MEDIA>();
		mPlatforms.add(SHARE_MEDIA.SINA);
		mPlatforms.add(SHARE_MEDIA.WEIXIN_CIRCLE);
		mPlatforms.add(null);
		mPlatforms.add(SHARE_MEDIA.QQ);
		mPlatforms.add(SHARE_MEDIA.WEIXIN);
		mPlatforms.add(SHARE_MEDIA.QZONE);
		mPlatforms.add(SHARE_MEDIA.RENREN);
		mPlatforms.add(SHARE_MEDIA.TENCENT);
		mPlatforms.add(SHARE_MEDIA.SMS);
		mPlatforms.add(SHARE_MEDIA.EMAIL);
		mPlatforms.add(SHARE_MEDIA.DOUBAN);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (position >= mPlatforms.size() || mPlatforms.get(position) == null) {
			return;
		}
		mController.directShare(getContext(), mPlatforms.get(position),
				mListener);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	class GridAdapter extends BaseAdapter {

		final int[] imgIds = { R.drawable.share_weibo,
				R.drawable.share_weixin_friends, R.drawable.share_tieba,
				R.drawable.share_qq, R.drawable.share_weixin,
				R.drawable.share_qzone, R.drawable.share_renren,
				R.drawable.share_tencent, R.drawable.share_msg,
				R.drawable.share_mail, R.drawable.share_douban };
		final String[] items = { "新浪微博", "朋友圈", "百度贴吧", "腾讯QQ", "微信", "QQ空间",
				"人人网", "腾讯微博", "短信", "邮件", "豆瓣" };
		LayoutInflater lf;

		@Override
		public int getCount() {
			return items.length + 1;
		}

		@Override
		public Object getItem(int position) {
			return "";
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (lf == null) {
				lf = LayoutInflater.from(parent.getContext());
			}
			convertView = lf.inflate(R.layout.item_share, null);
			ImageView imgView = (ImageView) convertView
					.findViewById(R.id.ishare_img);
			TextView textView = (TextView) convertView
					.findViewById(R.id.ishare_text);
			if (position < items.length) {
				imgView.setImageResource(imgIds[position]);
				textView.setText(items[position]);
			}
			return convertView;
		}

	}

	class MSnsListener implements SnsPostListener {

		@Override
		public void onComplete(SHARE_MEDIA arg0, int arg1, SocializeEntity arg2) {
		}

		@Override
		public void onStart() {

		}
	}

}
