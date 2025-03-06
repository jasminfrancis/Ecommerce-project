package com.exchange.response;

public class ErrorResponse  extends BaseResponse{
    private String errorCode;

    public ErrorResponse(String message, String status, String errorCode,int httpCode) {
        super(message, status,httpCode);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
