package com.example.ayaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.userlogin);
        pass = findViewById(R.id.passwordlogin);
        btn = findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = user.getText().toString();
                String Password = pass.getText().toString();
                login(Username, Password);
            }
        });
    }

    private void openDashboard() {
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }

    private void login(final String Username, final String Password) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String inputData;
        try {
            inputData = "Username=" + URLEncoder.encode(Username, "UTF-8") + "&Password=" + URLEncoder.encode(Password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody body = RequestBody.create(mediaType, inputData);

        Request request = new Request.Builder()
                .url("https://smarthome.mynalibe.site/app/get.php")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                    openDashboard();
                                } else if (status.equals("error")) {
                                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
