package com.example;

import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asynctask.MyReceiptsAsyncTask;
import com.example.client.JsonPlaceholderClient;
import com.example.client.MyReceiptsClient;
import com.example.retrofit.R;
import com.example.model.Firma;
import com.example.service.MyReceiptsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.service.JsonPlaceholderService;
import com.example.model.Post;

public class MainActivity extends AppCompatActivity {
    TextView textResult;
    TextView textFirma;
    ProgressBar progressBar;

    JsonPlaceholderService jsonPlaceholderApiService;
    MyReceiptsService myReceiptsService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVariables();

        testJsonPlaceholderApi();
//        testMyReceiptsApi();
        testMyReceiptsApiWithAsyncTask();
    }

    private void testMyReceiptsApiWithAsyncTask() {
        MyReceiptsAsyncTask asyncTask = new MyReceiptsAsyncTask(textFirma, progressBar);
        asyncTask.execute(1);
    }

    private void initVariables() {
        textResult = (TextView) findViewById(R.id.txtResult);
        textFirma = (TextView) findViewById(R.id.txtFirma);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        jsonPlaceholderApiService = JsonPlaceholderClient.getService();
        myReceiptsService = MyReceiptsClient.getService();
    }

    private void testJsonPlaceholderApi() {
        Call<Post> call = jsonPlaceholderApiService.getPost(1);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful()) {
                    textResult.setText("Code: " + response.code());
                    return;
                }
                Post post = response.body();
                textResult.setText(post.getTitle());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("RETROFIT: ", "onFaulure");
                textResult.setText(t.getLocalizedMessage());
            }
        });
    }

    private void testMyReceiptsApi() {
        Call<Firma> call = myReceiptsService.getFirma(1);

        call.enqueue(new Callback<Firma>() {
            @Override
            public void onResponse(Call<Firma> call, Response<Firma> response) {
                if(!response.isSuccessful()) {
                    textFirma.setText("Code: " + response.code());
                    return;
                }
                Firma f = response.body();
                textFirma.setText(f.getIme() + " - " + f.getAdresa());
            }

            @Override
            public void onFailure(Call<Firma> call, Throwable t) {
                textFirma.setText(t.getLocalizedMessage());
            }
        });
    }
}
