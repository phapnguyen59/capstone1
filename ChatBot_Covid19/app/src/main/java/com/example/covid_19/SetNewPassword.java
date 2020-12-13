package com.example.covid_19;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetNewPassword extends AppCompatActivity {


    Button next_btn;
    TextInputLayout password, confirm;
    ProgressBar progressBar;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

        progressBar = findViewById(R.id.Progressbar);
        password = findViewById(R.id.NewPassword);
        confirm = findViewById(R.id.ConfirmPassword);
        next_btn = findViewById(R.id.next_btn);


    }
    public void setNewPass(View view){
        if(!isConnected(this)){
            final ProgressDialog mDialog = new ProgressDialog(SetNewPassword.this);
            mDialog.setMessage("Please connect internet ... ");
            mDialog.show();
            return;
        }
        if (!validatePassword() | !validateConfirmPassword()) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        String _newPassword = password.getEditText().getText().toString().trim();
        String _phoneNumber = getIntent().getStringExtra("phoneNo");

        reference = FirebaseDatabase.getInstance().getReference("user");
        reference.child(_phoneNumber).child("password").setValue(_newPassword);
        startActivity(new Intent(getApplicationContext(),ForgetPasswordSuccessMessage.class));
        finish();
    }

    // check password
    private boolean validatePassword() {
        final String val = password.getEditText().getText().toString().trim();
        final String checkPassword ="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d^a-zA-Z0-9].{8,50}$";

        if (val.isEmpty()) {
            password.setError("Field can not be empty");
            return false;

        } else if (!val.matches(checkPassword)) {
            password.setError("Wrong Password!");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String val = confirm.getEditText().getText().toString().trim();
        String checkPassword ="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d^a-zA-Z0-9].{5,50}$";

        if (val.isEmpty()) {
            confirm.setError("Field can not be empty");
            return false;

        } else if (!val.matches(checkPassword)) {
            confirm.setError("Wrong Password!");
            return false;
        } else {
            confirm.setError(null);
            confirm.setErrorEnabled(false);
            return true;
        }
    }
    //check internet
    private boolean isConnected(SetNewPassword setNewPassword) {
        ConnectivityManager connectivityManager = (ConnectivityManager) setNewPassword.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn =connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn =connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifiConn!=null&&wifiConn.isConnected()||(mobileConn!=null&&mobileConn.isConnected())){
            return true;
        }
        else return false;
    }


}