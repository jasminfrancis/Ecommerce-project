package com.exchange.response;

public abstract  class BaseResponse {
    private String message;
    private String status;
    private int httpCode;

    public BaseResponse(String message, String status,int httpCode) {
        this.message = message;
        this.status = status;
        this.httpCode=httpCode;
    }

    public void setHttpCode(int httpCode){
        this.httpCode=httpCode;
    }
    public int getHttpCode(){
        return httpCode;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
