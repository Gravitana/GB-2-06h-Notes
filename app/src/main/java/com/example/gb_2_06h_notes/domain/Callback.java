package com.example.gb_2_06h_notes.domain;

public interface Callback<T> {

    void onSuccess(T value);

    void onError(Throwable error);
}
