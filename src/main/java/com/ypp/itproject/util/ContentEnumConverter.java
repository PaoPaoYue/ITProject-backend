package com.ypp.itproject.util;

import com.ypp.itproject.vo.ContentEnum;
import org.springframework.core.convert.converter.Converter;

public class ContentEnumConverter implements Converter<String, ContentEnum> {
    @Override
    public ContentEnum convert(String source) {
        System.out.println(source);
        try {
            return ContentEnum.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}