package kerchek.app.mesele.sual.testapplication.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import kerchek.app.mesele.sual.testapplication.R;

public class Utils {
    public static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        if (!TextUtils.isEmpty(message))
            progressDialog.setMessage(message);
        else progressDialog.setMessage(context.getString(R.string.please_wait));
        return progressDialog;
    }

    public static ProgressDialog createProgressDialog(Context context) {
        return createProgressDialog(context, null);
    }
}