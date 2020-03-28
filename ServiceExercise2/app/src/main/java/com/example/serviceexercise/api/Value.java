package com.example.serviceexercise.api;

import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("id")
    private int id;

    @SerializedName("joke")
    private String joke;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public Value(int id, String joke) {
        this.id = id;
        this.joke = joke;
    }
}