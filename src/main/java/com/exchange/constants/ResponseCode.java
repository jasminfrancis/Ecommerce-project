package com.exchange.constants;

public class ResponseCode {
	public static  final int SUCCESS=200;
	public static  final int NO_CONTENT=204;
	public static  final int PARTIAL_CONTENT=206;
	public static  final int BAD_REQUEST=400;
	public static  final int UNAUTHORIZED=401;
	public static  final int FORBIDDEN=403;
	public static  final int NOT_FOUND=404;
	public static  final int METHOD_NOT_ALLOWED=405;
	public static  final int UNSUPPORTED_MEDIA_TYPE=415;
	public static  final int INTERNAL_SERVER_ERROR=500;
	public static  final int BAD_GATEWAY=502;
	public static  final int SERVICE_UNAVAILABLE=503;
	public static  final int GATEWAY_TIMEOUT=504;
	public static  final int NETWORK_TIMEOUT_CONNECTION_ERROR=599;
	public static  final int CONFLICT=409;
	public static  final int FAILURE=410;


	private ResponseCode(){
		throw new UnsupportedOperationException("Unsupported operation exceptions");
	}
}
