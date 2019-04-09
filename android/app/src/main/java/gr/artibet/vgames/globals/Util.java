package gr.artibet.vgames.globals;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import gr.artibet.vgames.R;

public class Util {

    // ---------------------------------------------------------------------------------------
    // Toast error message
    // ---------------------------------------------------------------------------------------
    public static void errorToast(Activity activity, String message) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_error, (ViewGroup) activity.findViewById(R.id.error_toast_root));

        TextView text = layout.findViewById(R.id.error_toast_text);
        text.setText(message);
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    // ---------------------------------------------------------------------------------------
    // Toast success message
    // ---------------------------------------------------------------------------------------
    public static void successToast(Activity activity, String message) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_success, (ViewGroup) activity.findViewById(R.id.success_toast_root));

        TextView text = layout.findViewById(R.id.success_toast_text);
        text.setText(message);
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
