package com.sgk;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Log4j2
public class ExceptionLoggingTest {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionLoggingTest.class);

    private class MyException extends Exception {

        @Getter
        private String response;

        public MyException(String s, Throwable throwable, String response) {
            super(s, throwable);
            this.response = response;
        }
    }

    private void throwingMethod() throws Exception {
        throw new MyException("Exceptional message", null, "Server response: JSON: {}");
    }


    @Test
    public void loggingExceptionInLastParameterTest() throws Exception {
        try {
            throwingMethod();
        } catch (MyException e) {
//            log.error("Lombok: catch exception's response: {}", e.getResponse(), e);
            LOG.error("SLF4J Factory: catch exception's response: {}", e.getResponse(), e);
        }
    }
}
