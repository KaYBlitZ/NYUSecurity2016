package org.hacknyu.nyusecurity2016;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Kenneth on 2/20/2016.
 */
public class MenuAction {
    public static void showAboutDialog(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("About")
                .setMessage("This app allows you to notify others of serious trouble you may encounter within NYU campus. " +
                        "It also allows you to receive notifications from others so you can help out too! Let's build a robust community.")
                .setPositiveButton("OK", null)
                .show();
    }
}
