package com.czhappy.commonindexdemo.okhttp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.support.annotation.Nullable;
import android.view.Window;

import com.czhappy.commonindexdemo.utils.Global;
import com.czhappy.commonindexdemo.utils.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.request.BaseRequest;

import okhttp3.Response;


/**
 * Created by zhy on 15/12/14.
 */
public abstract class LoadingDialogCallback extends AbsCallback<String>
{
	private Context context;
	private ProgressDialog dialog;
	private String dialogStr;
	
	public LoadingDialogCallback(Context context) {
		super();
		this.context = context;
	}
	
	public LoadingDialogCallback(Context context, String dialogStr) {
		super();
		this.context = context;
		this.dialogStr = dialogStr;
	}

	@Override
	public void onBefore(BaseRequest request) {
		super.onBefore(request);
		request.params("deviceid", Global.DEVICE_ID)
				.params("version", Global.VERSION_CODE)
				.params("app_channel", Global.APP_CHANNEL);
		if(Global.user!=null && !Utils.isEmpty(Global.user.getUser_id())){
			request.params("user_id", Global.user.getUser_id());
		}
		if (!Utils.isEmpty(dialogStr)) {
			dialog = new ProgressDialog(context);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setCanceledOnTouchOutside(false);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					OkGo.getInstance().cancelTag(context);
				}
			});
			dialog.setMessage(dialogStr);
			dialog.show();
		}
	}

	@Override
	public void onAfter(@Nullable String s, @Nullable Exception e) {
		super.onAfter(s, e);
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}


	@Override
	public String convertSuccess(Response response) throws Exception {
		String s = StringConvert.create().convertSuccess(response);
		response.close();
		return s;
	}
}
