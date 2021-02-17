package com.limecoders.informativeapp;

import android.app.ProgressDialog;
import android.content.Context;

public class Utils {
    private static ProgressDialog pDialog;

    public static void initpDialog(Context context, String msg) {

        pDialog = new ProgressDialog(context);
        pDialog.setMessage(msg);
        pDialog.setCancelable(false);
    }

    public static void showpDialog() {

        if (!pDialog.isShowing()) pDialog.show();
    }

    public static void hidepDialog() {

        if (pDialog.isShowing()) pDialog.dismiss();
    }
}
