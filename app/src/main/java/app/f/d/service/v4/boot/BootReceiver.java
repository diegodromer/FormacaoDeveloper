package app.f.d.service.v4.boot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import app.f.d.service.v4.view.SplashActivity;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, SplashActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}

