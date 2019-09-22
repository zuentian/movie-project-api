package com.zuer.zuerlvdoubanauth.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubancommon.exception.BaseException;
import com.zuer.zuerlvdoubancommon.exception.FeignBaseException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/*
捕获Feign异常处理
 */
@Configuration

    /**
     * 重新实现feign的异常处理，捕捉restful接口返回的json格式的异常信息
     *
     */
public class UserErrorDecoder implements ErrorDecoder {

    @Override
    public FeignBaseException decode(String methodKey, Response response) {
        FeignBaseException exception = null;
        ObjectMapper mapper = new ObjectMapper();
        //空属性处理
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //禁止使用int代表enum的order来反序列化enum
        mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        try {
            String json = Util.toString(response.body().asReader());
            if (StringUtils.isEmpty(json)) {
                return null;
            }
            FeignFaileResult result = mapper.readValue(json, FeignFaileResult.class);
            // 业务异常包装成自定义异常类MyException
            System.out.println("Fegin错误信息："+result.getException());
            if (result.getStatus() != HttpStatus.OK.value()) {
                int status=result.getStatus();
                if(status==404){
                    exception = new FeignBaseException("服务调用异常！",result.getStatus());
                }else if(status==500){
                    exception = new FeignBaseException("数据处理异常！",result.getStatus());
                }else {
                    exception = new FeignBaseException("系统异常！",result.getStatus());
                }
                exception.setMessageDetail(result.getMessage());
                exception.setMethod(methodKey);

            }
        } catch (IOException ex) {

        }
        return exception;
    }
}
