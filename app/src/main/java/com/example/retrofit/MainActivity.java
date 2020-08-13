package com.example.retrofit;

import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.retrofit.myreceiptsApi.Firma;
import com.example.retrofit.myreceiptsApi.MyReceiptsApi;
import org.w3c.dom.Text;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.retrofit.jsonPlacheholderApi.MyApiService;
import com.example.retrofit.jsonPlacheholderApi.Post;

public class MainActivity extends AppCompatActivity {
    TextView textResult;
    TextView textFirma;

    MyApiService jsonPlaceholderApiService;
    MyReceiptsApi myReceiptsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVariables();

        retrofitJsonPlacheholderApi();
        retrofitMyReceiptsApi();

    }

    private void initVariables() {
        textResult = (TextView) findViewById(R.id.txtResult);
        textFirma = (TextView) findViewById(R.id.txtFirma);
    }

    private void retrofitJsonPlacheholderApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholderApiService = retrofit.create(MyApiService.class);

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

    private void retrofitMyReceiptsApi() {
        Retrofit mrRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.15:8080/api/test/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myReceiptsService = mrRetrofit.create(MyReceiptsApi.class);

        Call<Firma> callFirma = myReceiptsService.getFirma(1);

        callFirma.enqueue(new Callback<Firma>() {
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
