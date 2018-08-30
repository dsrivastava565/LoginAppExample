package com.example.my_dell.loginappexample;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
private TextView RegText;
private EditText username1,password1;
private Button submit;
OnLoginFormActivityListener loginFormActivityListener;
public interface OnLoginFormActivityListener
{
    public void performRegister();
    public void performLogin(String name);

}

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        RegText = view.findViewById(R.id.RegisterView);
        username1 = view.findViewById(R.id.login_usernme);
        int maxLength = 10;
        username1.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        password1 = view.findViewById(R.id.login_pass);
        password1.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        submit = view.findViewById(R.id.login_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
performLogin();
            }
        });

        RegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
loginFormActivityListener.performRegister();

            }
        });
        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener = (OnLoginFormActivityListener) activity;
    }
    private void performLogin()
    {
        String username = username1.getText().toString();
        String password = password1.getText().toString();
        ApiInterface data = ApiClient.getApiClient().create(ApiInterface.class);
        Call<User> call = data.performUserLogin(username,password);
        Log.e("AAAAAA" , username+"   "+password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("CHECK" , new Gson().toJson(response.body()));
                Toast.makeText(getContext() , "HELLO" , Toast.LENGTH_SHORT).show();
                assert response.body() != null;
                if (response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.displayToast("success");
                    MainActivity.prefConfig.writeLoginStatus(true);
                    assert response.body() != null;
                    loginFormActivityListener.performLogin(response.body().getName());

                }
                else {
                    assert response.body() != null;
                    if("failed".equals(response.body().getResponse()))
                    {
                        MainActivity.prefConfig.displayToast("Login failed");
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR" , t.toString());
                MainActivity.prefConfig.displayToast("failure...");
            }
        });

        username1.setText("");
        password1.setText("");

    }
}
