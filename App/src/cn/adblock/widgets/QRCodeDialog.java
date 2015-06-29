package cn.adblock.widgets;

import java.util.Hashtable;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import cn.adblock.R;

/**
 * TODO
 * 
 * @author Smile<lijianhy1990@gmail.com>
 * @date 2015年6月22日
 */
public class QRCodeDialog extends Dialog implements android.view.View.OnClickListener {

	private Button btnClose;
	private ImageView imgBack;
	private ImageView imgQR;

	public QRCodeDialog(Context context) {
		this(context, R.style.Dialog_N);
	}

	public QRCodeDialog(Context context, int theme) {
		super(context, theme);
		initView(context);
		setCanceledOnTouchOutside(false);
		setCancelable(false);
	}

	private void initView(Context context) {
		setContentView(R.layout.dialog_qrcode);
		btnClose = (Button) findViewById(R.id.dqr_btn_close);
		imgBack = (ImageView) findViewById(R.id.dqr_img_back);
		imgQR = (ImageView) findViewById(R.id.dqr_img_qr);
		btnClose.setOnClickListener(this);
		imgBack.setOnClickListener(this);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onClick(View v) {
		dismiss();
	}

}
