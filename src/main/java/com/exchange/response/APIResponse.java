package com.exchange.response;

public class APIResponse<T>  extends BaseResponse{


private T response;

public APIResponse(String message,String status, T response,int httpCode) {
	super(message,status,httpCode);
	this.response=response;
}

public APIResponse(String message,String status,int httpCode) {
	super(message,status,httpCode);
}

public T getResponse() {
return response;
}
public void setResponse(T response) {
this.response = response;
}

}
