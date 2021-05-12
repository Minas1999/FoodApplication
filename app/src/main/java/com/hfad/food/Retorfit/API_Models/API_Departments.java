package com.hfad.food.Retorfit.API_Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_Departments {

    @SerializedName("id")
    @Expose
    private int dep_id;

    private String dep_name;

    public int getDep_id() {
        return dep_id;
    }

    public String getDep_name() {
        return dep_name;
    }
}
