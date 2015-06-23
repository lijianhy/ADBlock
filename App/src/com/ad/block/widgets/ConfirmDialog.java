package com.ad.block.widgets;

import com.ad.block.R;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmDialog extends Dialog implements
		android.view.View.OnClickListener {

	private TextView textTitle;
	private Button btnConfirm;
	private Button btnCancel;

	private ConfirmListener mListener;

	public ConfirmDialog(Context context) {
		this(context, R.style.Dialog_Notitle);
	}

	public ConfirmDialog(Context context, int theme) {
		super(context, theme);
		init();
	}

	private void init() {
		setContentView(R.layout.dialog_confirm);
		findViewById(R.id.dc_view_bg).setOnClickListener(this);
		btnConfirm = (Button) findViewById(R.id.dc_btn_confirm);
		btnCancel = (Button) findViewById(R.id.dc_btn_cancel);
		btnCancel.setOnClickListener(this);
		btnConfirm.setOnClickListener(this);
		textTitle = (TextView) findViewById(R.id.dc_text_title);
	}

	public ConfirmDialog setTitle(String title) {
		textTitle.setText(title);
		return this;
	}
	
	public ConfirmDialog setOneBtn(){
		btnCancel.setVisibility(View.GONE);
		btnConfirm.setBackgroundResource(R.drawable.selector_btn_bottom);
		return this;
	}

	public ConfirmDialog setConfirmListener(ConfirmListener listener) {
		this.mListener = listener;
		return this;
	}

	@Override
	public void onClick(View v) {
		int vid = v.getId();
		switch (vid) {
		case R.id.dc_view_bg:
		case R.id.dc_btn_cancel:
			dismiss();
			break;
		case R.id.dc_btn_confirm:
			onConfirm();
			break;
		}
	}

	private void onConfirm() {
		if (mListener != null) {
			mListener.onConfirmClick();
		}
	}

	public interface ConfirmListener {
		void onConfirmClick();
	}

}
