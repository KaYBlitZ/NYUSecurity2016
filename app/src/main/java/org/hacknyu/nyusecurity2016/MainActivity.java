package org.hacknyu.nyusecurity2016;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.hacknyu.nyusecurity2016.fragments.LoginFragment;
import org.hacknyu.nyusecurity2016.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getFragmentManager().beginTransaction().add(R.id.layout, new LoginFragment(), Constants.TAG_LOGIN).commit();

//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
    }
}
