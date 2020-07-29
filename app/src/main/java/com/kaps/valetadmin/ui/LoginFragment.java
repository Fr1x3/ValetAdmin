package com.kaps.valetadmin.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.kaps.valetadmin.R;
import com.kaps.valetadmin.Utils.SharedPreferenceUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // textview

        final EditText etUser = view.findViewById(R.id.etUserName);
        final EditText etPass = view.findViewById(R.id.etPassword);

        Button btnLogin = view.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString().trim();
                String password = etPass.getText().toString().trim();

                if( user.isEmpty() || password.isEmpty())
                    Snackbar.make(v, "Please fill in the details", Snackbar.LENGTH_LONG).show();
                else if( user.compareTo(SharedPreferenceUtil.getString("user")) == 0
                            && password.compareTo(SharedPreferenceUtil.getString("password")) == 0)
                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_valetListFragment);
                else
                    Snackbar.make(v, "Incorrect credentials entered", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
