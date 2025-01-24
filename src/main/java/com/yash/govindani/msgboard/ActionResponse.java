package com.yash.govindani.msgboard;

public class ActionResponse<T> implements java.io.Serializable {
    private boolean success;
    private String error;
    private T value;
    public ActionResponse() {
        this.success = false;
        this.error = null;
        this.value = null;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean getSuccess() {
        return this.success;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getError() {
        return this.error;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public T getValue() {
        return this.value;
    }
}