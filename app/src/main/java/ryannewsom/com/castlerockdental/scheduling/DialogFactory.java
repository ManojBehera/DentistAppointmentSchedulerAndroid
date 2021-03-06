package ryannewsom.com.castlerockdental.scheduling;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import ryannewsom.com.castlerockdental.R;
import ryannewsom.com.castlerockdental.networking.NetworkingError;

/**
 * Creates Dialogs for various scenarios
 */
public class DialogFactory {
    private DialogFactory(){} //util class

    public static AlertDialog getDialogForError(Activity activity, NetworkingError error){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage(error.getMessage())
                .setTitle(R.string.dialog_title);

        return builder.create();
    }

    public static AlertDialog getSuccessDialog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage("Appointment Made")
                .setTitle("Success!");

        return builder.create();
    }
}
