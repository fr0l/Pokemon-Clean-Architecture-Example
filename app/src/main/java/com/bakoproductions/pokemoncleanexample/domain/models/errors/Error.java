package com.bakoproductions.pokemoncleanexample.domain.models.errors;

import java.io.Serializable;

/**
 * Created by Michael on 15/4/2017.
 */

public class Error implements Serializable {
    public static final int NETWORK_ERROR_CODE = -1;

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNetworkError() {
        return status == NETWORK_ERROR_CODE;
    }
}
