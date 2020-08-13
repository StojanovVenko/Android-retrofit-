package com.example.retrofit.myreceiptsApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyReceiptsApi {

    @GET("firmi/{id}")
    Call<Firma> getFirma(@Path("id") int idFirma);
}
