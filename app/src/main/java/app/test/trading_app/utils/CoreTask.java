package app.test.trading_app.utils;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.widget.Toast;

import app.test.trading_app.R;
import app.test.trading_app.databinding.ActivityMainBinding;


public class CoreTask {

    public void navTask(Activity activity,MenuItem menuItem, Context context, ActivityMainBinding binding){



        if (R.id.nav_left_share_app == menuItem.getItemId()) {
            binding.dl.closeDrawers();
            try {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                intent.putExtra("android.intent.extra.TEXT", context.getString(R.string.str_share_app_pre_text)+"\nhttp://play.google.com/store/apps/details?id=" + context.getPackageName());
                context.startActivity(Intent.createChooser(intent, ""+context.getString(R.string.str_share_with)));
            } catch (Exception unused) {
                Toast.makeText(context, ""+context.getString(R.string.str_unable_to_send_try_again), Toast.LENGTH_LONG).show();
            }
        }
        if (R.id.nav_left_privacy == menuItem.getItemId()) {
            binding.dl.closeDrawers();
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.privacy_policy)));
                context.startActivity(browserIntent);
            } catch (Exception unused2) {
                Toast.makeText(context, ""+context.getString(R.string.str_unable_to_send_try_again), Toast.LENGTH_LONG).show();
            }
        }
        if (R.id.nav_left_rate_app == menuItem.getItemId()) {
            binding.dl.closeDrawers();
                Uri uri = Uri.parse("market://details?id="+ context.getPackageName());
                Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    context.startActivity(myAppLinkToMarket);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, ""+ R.string.str_unable_to_find_market, Toast.LENGTH_LONG).show();
                }
        }

        if (R.id.nav_left_exit == menuItem.getItemId()) {
            binding.dl.closeDrawers();
            System.exit(0);
        }

    }

    public void getAppReview(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, ""+ R.string.str_unable_to_find_market, Toast.LENGTH_LONG).show();
        }
    }
}

