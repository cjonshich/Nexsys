package com.cbolije.nexsys.model.dto;

public class RestResponse<T> {
    private T respuesta;
    private String error;

    public T getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(T respuesta) {
        this.respuesta = respuesta;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
