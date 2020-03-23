package com.example.threadingexercise.api;


import com.google.gson.annotations.SerializedName;

public class NorrisQuote {

    @SerializedName("type")
    private String type;

    @SerializedName("value")
    private Value value;


    public NorrisQuote(String type, Value value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
