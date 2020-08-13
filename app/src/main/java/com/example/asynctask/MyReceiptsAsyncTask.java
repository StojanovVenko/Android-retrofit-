package com.example.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.client.MyReceiptsClient;
import com.example.model.Firma;
import com.example.service.MyReceiptsService;
import retrofit2.Call;

import java.io.IOException;

public class MyReceiptsAsyncTask extends AsyncTask<Integer, Integer, Firma> {
    TextView textView;
    ProgressBar progressBar;

    public MyReceiptsAsyncTask(TextView textView, ProgressBar progressBar) {
        super();
        this.textView = textView;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // make visible loader
        this.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Firma doInBackground(Integer... integers) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Call<Firma> firma = MyReceiptsClient.getService().getFirma(integers[0]);
        try {
            return firma.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Firma firma) {
        super.onPostExecute(firma);
        //make loader invisible and update textView

        if(firma == null) {
            this.textView.setText("Firma is null");
            this.progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        this.textView.setText("Firma: " + firma.getIme() + "\n" + "Adresa: " + firma.getAdresa());
        this.progressBar.setVisibility(View.INVISIBLE);
    }
}
