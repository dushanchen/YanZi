package com.yanzi.common.controller.converter;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.yanzi.common.controller.response.ResponseEntityWrapper;

public class CustomStringConverter extends AbstractHttpMessageConverter<Object> {

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {
        OutputStream out = outputMessage.getBody();
        Object data = null;
        if (obj instanceof ResponseEntityWrapper) {
            ResponseEntityWrapper response = (ResponseEntityWrapper) obj;
            data = response.getMessage();
        }
        if (data == null) {
            data = new String("");
        }
        byte[] bytes = data.toString().getBytes();
        out.write(bytes);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        throw new HttpMessageNotReadableException("HTTP message read method not supported");
    }

}
