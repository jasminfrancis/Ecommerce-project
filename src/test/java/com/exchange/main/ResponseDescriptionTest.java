package com.exchange.main;
import com.exchange.constants.ResponseDescription;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.junit.jupiter .MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ResponseDescriptionTest {


    @Test
    void testResponseDescriptions( ){
         assertEquals ("Incomplete data", ResponseDescription.INCOMPLETE_DATA);
                assertEquals("Sucess", ResponseDescription .SUCCESS);
                assertEquals ("File Not Found", ResponseDescription.FILE_NOT_FOUND);
                assertEquals ("Login success", ResponseDescription.LOGIN_SUCESS);

    }
    @Test
    void testPrivateConstructor ()throws Exception{
        Constructor< ResponseDescription> constructor = ResponseDescription.class.getDeclaredConstructor ();
                constructor.setAccessible (true);
        Exception exception = assertThrows (InvocationTargetException.class, constructor::newInstance);
        assertTrue (exception.getCause () instanceof UnsupportedOperationException) ;
    }


}

