package com.exchange.main;

import com.exchange.constants.ResponseCode;
import com.exchange.constants.ResponseDescription;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseCodeTest {

    @Test
    void testResponseCode( ){
        assertEquals (200, ResponseCode.SUCCESS);


    }

    @Test
    void testPrivateConstructor ()throws Exception{
        Constructor<ResponseCode> constructor = ResponseCode.class.getDeclaredConstructor ();
        constructor.setAccessible (true);
        Exception exception = assertThrows (InvocationTargetException.class, constructor::newInstance);
        assertTrue (exception.getCause () instanceof UnsupportedOperationException) ;
    }
}
