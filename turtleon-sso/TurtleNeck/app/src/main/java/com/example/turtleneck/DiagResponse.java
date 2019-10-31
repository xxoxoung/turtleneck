package com.example.turtleneck;

import com.google.gson.annotations.Expose;

import java.lang.reflect.Array;

public class DiagResponse {

    //@SerializedName("confirm")
    @Expose
    private String Diag;

    public DiagResponse(String Diag) {
        this.Diag = Diag;
    }

    public String getDiag() { return Diag; }

    public void setDiag(String diag) { this.Diag = diag; }
}
