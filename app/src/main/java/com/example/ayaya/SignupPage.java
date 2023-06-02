package com.example.ayaya;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignupPage extends AppCompatActivity {
    private static final String TAG = "SignupPage";
    private EditText user,pass,conpass,email;
    private Button btn;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        user = (EditText) findViewById(R.id.signup_user);
        email = (EditText) findViewById(R.id.signup_email);
        pass = (EditText) findViewById(R.id.signup_password);
        conpass = (EditText) findViewById(R.id.signup_confirmpassword);
        btn = (Button) findViewById(R.id.signup_button);
        txt = (TextView) findViewById(R.id.Confirmwarning);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = user.getText().toString();
                String Password = pass.getText().toString();
                String Confirmpass = conpass.getText().toString();
                String Email = email.getText().toString();

                if (!Confirmpass.equals(Password)) {
                    txt.setVisibility(View.VISIBLE);
                } else if (Confirmpass.equals(Password)) {
                    txt.setVisibility(View.INVISIBLE);
                    signup(Username, Email, Password);
                }
            }
        });
    }

    private void signup(final String Username, final String Email, final String Password) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String inputData;
        try {
            inputData = "Username=" + URLEncoder.encode(Username, "UTF-8") + "&Password=" + URLEncoder.encode(Password, "UTF-8") + "&Email=" + URLEncoder.encode(Email, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(SignupPage.this, "Unsupported Encoding", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody body = RequestBody.create(mediaType, inputData);

        Request request = new Request.Builder()
                .url("https://smarthome.mynalibe.site/app/post.php")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Error occurred: " + e.getMessage());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SignupPage.this, "error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        JSONObject jsonResponse = new JSONObject(responseBody);
                        String status = jsonResponse.getString("status");
                        String message = jsonResponse.getString("message");

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (status.equals("success")) {
                                    Toast.makeText(SignupPage.this, message, Toast.LENGTH_SHORT).show();
                                    finish();
                                } else if (status.equals("error")) {
                                    Toast.makeText(SignupPage.this, message, Toast.LENGTH_SHORT).show();
                                } else if (status.equals("retry")) {
                                    Toast.makeText(SignupPage.this, message, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignupPage.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(TAG, "Error occurred: " + e.getMessage());
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SignupPage.this, "An error occurred", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SignupPage.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}