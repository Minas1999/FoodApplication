package com.hfad.food.Retorfit.Interfaces;

import com.hfad.food.Retorfit.API_Models.API_Departments;
import com.hfad.food.Retorfit.API_Models.API_Food;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HolderApi {
    @GET("api/Products")
    Call<List<API_Food>> getFoods();


    @GET("api/Departments")
    Call<List<API_Departments>> getDepartments();

}
