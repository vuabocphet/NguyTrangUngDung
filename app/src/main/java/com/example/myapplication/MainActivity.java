package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public enum ICON_COLOUR {RED, BLUE, WHITE}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void setIcon(ICON_COLOUR targetColour) {
        int componentEnabledStateEnabled = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        for (ICON_COLOUR value : ICON_COLOUR.values()) {
            if (value == targetColour) {
                componentEnabledStateEnabled = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
                break;
            }
        }
        getPackageManager().setComponentEnabledSetting(
                new ComponentName(BuildConfig.APPLICATION_ID, BuildConfig.APPLICATION_ID + "." + targetColour.name()),
                componentEnabledStateEnabled, PackageManager.DONT_KILL_APP
        );
        getPackageManager().setComponentEnabledSetting(
                new ComponentName(BuildConfig.APPLICATION_ID, BuildConfig.APPLICATION_ID + "." + ICON_COLOUR.RED.name()),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
        );
    }

    public void showSetting(View view) {
        this.setIcon(ICON_COLOUR.WHITE);
    }
}