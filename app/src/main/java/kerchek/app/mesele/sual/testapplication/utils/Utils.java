package kerchek.app.mesele.sual.testapplication.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.net.UnknownHostException;

import kerchek.app.mesele.sual.testapplication.R;
import retrofit2.HttpException;

public class Utils {

    private Utils() {
        // This utility class is not publicly instantiable
    }

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

    public static void showAppropriateError(Context context, Throwable throwable) {

        if (throwable instanceof HttpException) {

            if (!TextUtils.isEmpty(throwable.getMessage())) {
                Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }

        } else {
            if (throwable instanceof UnknownHostException) {
                Toast.makeText(context, R.string.network_unavailable, Toast.LENGTH_SHORT).show();
            } else {
                if (!TextUtils.isEmpty(throwable.getMessage())) {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}