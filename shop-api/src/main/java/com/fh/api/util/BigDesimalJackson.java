package com.fh.api.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 针对jackson处理BigDecimal小数位.00自动截取问题处理,
 * 需要在实体类的相关属性上加 @JsonSerialize(using = BigDesimalJackson.class)
 */

public class BigDesimalJackson extends JsonSerializer<BigDecimal> {


    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if(null==bigDecimal){
            jsonGenerator.writeString("");
        }else {
            jsonGenerator.writeString(bigDecimal.toString());
        }
    }
}
