package com.exchange.main;

import com.exchange.constants.ResponseCode;
import com.exchange.constants.ResponseDescription;
import com.exchange.constants.ResponseMessage;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseMessageTest {

    @Test
    void testResponseMessage(){

        assertEquals("Sucess", ResponseMessage.SUCCESS);


    }
    @Test
    void testPrivateConstructor ()throws Exception{

        Constructor<ResponseMessage> constructor = ResponseMessage.class.getDeclaredConstructor ();
        constructor.setAccessible (true);
        Exception exception = assertThrows (InvocationTargetException.class, constructor::newInstance);
        assertTrue (exception.getCause () instanceof UnsupportedOperationException) ;
    }
}
