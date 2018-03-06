package com.neman.morph.morphwallet.Activity;

/**
 * Created by albert on 3/4/18.
 * and roccooooo
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neman.morph.morphwallet.MainActivity;
import com.neman.morph.morphwallet.Model.User;
import com.neman.morph.morphwallet.R;
import com.neman.morph.morphwallet.Utils.HTTPDataHandler;

import java.lang.reflect.Type;
import java.util.List;

//import butterknife.ButterKnife;
//import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    public EditText _emailText;
    public EditText _passwordText;
    public Button _loginButton;
    public TextView _signupLink;

    // API key: 6D0eWBFJOgEvStu_kwLQQ0a70k2-OBFL

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);

       // ButterKnife.inject(this);
        _loginButton = (Button) findViewById(R.id.btn_login);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink = (TextView) findViewById(R.id.link_signup);

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Snackbar.make(v, "creating new acct", Snackbar.LENGTH_LONG).show();
                /* TODO
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                */
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();





        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog pd = new ProgressDialog(LoginActivity.this);

        @Override
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];
            HTTPDataHandler http = new HTTPDataHandler();
            stream = http.GetHTTPData(urlString);

            return stream;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd.setTitle("Please wait... ");

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // Done process
            Gson gson = new Gson();

            // TODO left off at 33:33 from tutorial.. but getting protected error
           // Type listType = new TypeToken<List<User>>()().getType();
           // users=gson.fromJson(s,listType); //
            pd.dismiss();
        }


    }
}