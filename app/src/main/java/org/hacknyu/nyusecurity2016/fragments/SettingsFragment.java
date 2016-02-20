package org.hacknyu.nyusecurity2016.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.hacknyu.nyusecurity2016.Constants;
import org.hacknyu.nyusecurity2016.MenuAction;
import org.hacknyu.nyusecurity2016.R;

/**
 * Created by Kenneth on 2/20/2016.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            PreferenceFragment fragment = (PreferenceFragment) getFragmentManager().findFragmentByTag(Constants.TAG_SETTINGS);
            if (fragment == null) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.layout, new SettingsFragment(), Constants.TAG_SETTINGS)
                        .addToBackStack(null)
                        .commit();
            } else {
                getFragmentManager().beginTransaction()
                        .replace(R.id.layout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
            return true;
        } else if (id == R.id.action_about) {
            MenuAction.showAboutDialog(getActivity());
        }

        return super.onOptionsItemSelected(item);
    }
}
