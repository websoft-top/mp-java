package com.gxwebsoft.common.core.config;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

public class HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public HttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        setSupportedMediaTypes(mediaTypes);
    }
}
