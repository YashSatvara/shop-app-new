package com.example.shopapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultSavelogin {

    @SerializedName("savelogin")
    @Expose
    private List<Savelogin> savelogin = null;

    public List<Savelogin> getSavelogin() {
        return savelogin;
    }

    public void setSavelogin(List<Savelogin> savelogin) {
        this.savelogin = savelogin;
    }
}
