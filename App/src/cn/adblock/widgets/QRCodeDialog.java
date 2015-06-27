package cn.adblock.widgets;

import java.util.Hashtable;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.ImageView;
import cn.adblock.R;

/**
 * TODO
 * 
 * @author Smile<lijianhy1990@gmail.com>
 * @date 2015年6月22日
 */
public class QRCodeDialog extends Dialog {

	private Button btnClose;
	private ImageView imgQR;

	public QRCodeDialog(Context context) {
		this(context, R.style.Dialog_N);
	}

	public QRCodeDialog(Context context, int theme) {
		super(context, theme);
		initView(context);
	}

	private void initView(Context context) {
		setContentView(R.layout.dialog_qrcode);
		btnClose = (Button) findViewById(R.id.dqr_btn_close);
		imgQR = (ImageView) findViewById(R.id.dqr_img_qr);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

}
