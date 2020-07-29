 package com.kaps.valetadmin.ui;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.Snackbar;
import com.kaps.valetadmin.R;
import com.kaps.valetadmin.models.MessageResponse;
import com.kaps.valetadmin.models.Valet;
import com.kaps.valetadmin.viewmodel.RegisterViewModel;

 /**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }
    private String mRole;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonRegister = view.findViewById(R.id.btn_register);

        final RegisterViewModel viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        // edit text
        final EditText fname = view.findViewById(R.id.et_first_name);
        final EditText lname = view.findViewById(R.id.et_last_name);
        final EditText email = view.findViewById(R.id.et_email);
        final EditText password = view.findViewById(R.id.et_password);
        final EditText confirmPassword = view.findViewById(R.id.et_confirm_password);
        final RadioGroup radioGroup = view.findViewById(R.id.radio_group_role);

        // perform sth when the button is clicked
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mpassword = password.getText().toString().trim();
                String mconfirmPassword = confirmPassword.getText().toString().trim();
                String mfname = fname.getText().toString().trim();
                String mlame = lname.getText().toString().trim();
                String memail = email.getText().toString().trim();

                onRadioButtonClicked(radioGroup.getCheckedRadioButtonId());



                if( mpassword.compareTo(mconfirmPassword) == 0 ){
                    // create a valet object
                    Valet valet = new Valet(mfname, mlame, memail, mpassword, mRole);
                    if( isConnected()) {
                        // add valet to the db
                        viewModel.register(valet).observe(getViewLifecycleOwner(), new Observer<MessageResponse>() {
                            @Override
                            public void onChanged(MessageResponse messageResponse) {
                                if (Integer.parseInt(messageResponse.getStatus()) == 0) {
                                    password.setText("");
                                    confirmPassword.setText("");
                                    fname.setText("");
                                    lname.setText("");
                                    email.setText("");
                                    mRole = "";
                                    radioGroup.clearCheck();
                                }
                                Snackbar.make(view, messageResponse.getMessage(), Snackbar.LENGTH_LONG).show();
                                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_valetListFragment);
                            }
                        });
                    }else
                        Snackbar.make(view, "No internet Connection", Snackbar.LENGTH_LONG).show();
                }
                else {
                    password.setText("");
                    confirmPassword.setText("");
                    Snackbar.make(view, "Passwords do not match", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onRadioButtonClicked(int id){
        // is button checked?


        switch( id){
            case R.id.radio_admin:
                mRole = "ADMIN";
                break;
            case R.id.radio_valet:
                 mRole = "REGULAR";
                break;
        }
    }

    public boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return  netInfo != null && netInfo.isConnected();
    }
}
