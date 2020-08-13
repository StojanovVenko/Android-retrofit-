package com.example.service;

import com.example.model.Firma;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyReceiptsService {

    @GET("firmi/{id}")
    Call<Firma> getFirma(@Path("id") int idFirma);
}
