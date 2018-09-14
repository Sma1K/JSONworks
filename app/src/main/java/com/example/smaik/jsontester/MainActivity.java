package com.example.smaik.jsontester;

import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    ImageView imageView;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image_nasa);
        //textView = findViewById(R.id.text_url);
        button = findViewById(R.id.get_btn);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(final View view) {

        view.setEnabled(false);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://api.nasa.gov/planetary/apod?api_key=C912d1S0zaFQAWxZxCr6DaPz7UIy8lhU1nzzoAbT", new AsyncHttpResponseHandler() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(responseBody != null)
                {
                    String str = new String(responseBody, StandardCharsets.UTF_8);
                    try {
                        JSONObject myResponse = new JSONObject(str);
                        Toast.makeText(getApplicationContext(), myResponse.getString("url"), Toast.LENGTH_SHORT).show();
                        Picasso.get().load(myResponse.getString("url")).into(imageView);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                view.setEnabled(true);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                view.setEnabled(true);

            }
        });
    }






    /*public void setImageView(ImageView imageView, TextView textView) throws Exception {




    }*/
}
/*String url = "https://api.nasa.gov/planetary/apod?api_key=C912d1S0zaFQAWxZxCr6DaPz7UIy8lhU1nzzoAbT";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection)
                obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        textView.setText(String.valueOf(response.toString()));
        Toast.makeText(this,   response.toString() , Toast.LENGTH_SHORT).show();

        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        String image_url = myResponse.getString("url");*/