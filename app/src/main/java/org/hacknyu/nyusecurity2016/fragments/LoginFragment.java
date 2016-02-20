package org.hacknyu.nyusecurity2016.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.hacknyu.nyusecurity2016.R;
import org.hacknyu.nyusecurity2016.tasks.LoginTask;
import org.hacknyu.nyusecurity2016.tasks.RegisterTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        final EditText email = (EditText) view.findViewById(R.id.edit_email);
        final EditText password = (EditText) view.findViewById(R.id.edit_password);

        Button login = (Button) view.findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginTask().execute(email.getText().toString(), password.getText().toString());
            }
        });

        TextView register = (TextView) view.findViewById(R.id.txt_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterDialog(getActivity());
            }
        });

        return view;
    }

    public void showRegisterDialog(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.register, null);
        final EditText first = (EditText) dialogView.findViewById(R.id.edit_first_name);
        final EditText last = (EditText) dialogView.findViewById(R.id.edit_last_name);
        final EditText email = (EditText) dialogView.findViewById(R.id.edit_email);
        final EditText password = (EditText) dialogView.findViewById(R.id.edit_password);

        new AlertDialog.Builder(getActivity()).setTitle("Register")
                .setView(dialogView)
                .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new RegisterTask(getActivity()).execute(first.getText().toString(), last.getText().toString(),
                                email.getText().toString(), password.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
