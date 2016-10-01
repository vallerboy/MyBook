package pl.oskarpolak.mybook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by OskarPraca on 2016-10-01.
 */

public class Utils {
    public static void showMessageWithoutAction(Context con, String text, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(con);

        builder.setMessage(text);
        builder.setTitle(title);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
