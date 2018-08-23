package com.example.my_dell.loginappexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {
private EditText Name,Username,Userpassword;
private Button bn_register;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
 Name = view.findViewById(R.id.reg_name);
 Username = view.findViewById(R.id.reg_username);
 Userpassword = view.findViewById(R.id.reg_password);
 bn_register = view.findViewById(R.id.bn_register);
 bn_register.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
performRegistration();
     }
 });

        return view;
    }

    public void performRegistration()
    {
        String name = Name.getText().toString();
        String username = Username.getText().toString();
        String password = Userpassword.getText().toString();
        Call<User> call = MainActivity.apiInterface.performRegistration(name,username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.displayToast("Registration success...");

                }
                else if(response.body().getResponse().equals("exist"))
                {
                    MainActivity.prefConfig.displayToast(" User Already exist...");
                }
                else if (response.body().getResponse().equals("error"))
                {
                    MainActivity.prefConfig.displayToast("Something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                MainActivity.prefConfig.displayToast("Failure");
            }
        });
        Name.setText("");
        Userpassword.setText("");
        Username.setText("");

    }

}
