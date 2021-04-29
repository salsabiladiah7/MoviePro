package com.salsabila.moviepro;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MService {
    @GET("/")
    Call<MResponse> getMo();
}
