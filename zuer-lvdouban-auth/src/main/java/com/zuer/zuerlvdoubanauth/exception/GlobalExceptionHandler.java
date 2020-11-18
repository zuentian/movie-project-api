package com.zuer.zuerlvdoubanauth.exception;

import com.zuer.zuerlvdoubanauth.feginservice.ExceptionInfoFeignService;
import com.zuer.zuerlvdoubancommon.entity.ExceptionInfo;
import com.zuer.zuerlvdoubancommon.exception.BaseException;
import com.zuer.zuerlvdoubancommon.exception.BaseHandleException;
import com.zuer.zuerlvdoubancommon.exception.FeignBaseException;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

//全局捕获异常处理类
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @Autowired
    private ExceptionInfoFeignService exceptionInfoFeignService;

    @ExceptionHandler(BaseException.class)
    public BaseException baseExceptionHandler(HttpServletResponse response, BaseException ex) throws Exception{
        int status=401;
        System.out.println("全局捕获异常处理类BaseException:"+ex);
        response.setStatus(status);

        return new BaseException(ex.getMessage(),ex.getStatus());
    }
    @ExceptionHandler(FeignBaseException.class)
    public FeignBaseException feignBaseException(HttpServletResponse response, FeignBaseException ex)  throws Exception{
        System.out.println("Feign全局捕获异常处理类FeignBaseException:"+ex);
        response.setStatus(ex.getStatus());
        ExceptionInfo exceptionInfo=new ExceptionInfo();
        exceptionInfo.setId(UUID.randomUUID().toString());
        exceptionInfo.setStatus(String.valueOf(ex.getStatus()));
        exceptionInfo.setErrMessage(ex.getMessage());
        exceptionInfo.setErrType("FeignBaseException");
        exceptionInfo.setErrDetail(ex.getMessageDetail());
        if(StringUtils.isNotBlank(ex.getMethod())){
            String [] erros=ex.getMethod().split("#");
            if(erros.length>=2){
                exceptionInfo.setErrMethod(ex.getMethod().split("#")[1]);
                exceptionInfo.setErrFileName(ex.getMethod().split("#")[0]);
            }else{
                exceptionInfo.setErrMethod(ex.getMethod());
            }
        }
        EntityUtils.setCreateInfo(exceptionInfo);
        try{
            exceptionInfoFeignService.insertExceptionInfo(exceptionInfo);//错误信息落入数据库
        }catch (Exception e){
            throw  new Exception("系统异常！");
        }
        return new FeignBaseException(ex.getMessage(),ex.getStatus());
    }

    @ExceptionHandler(BaseHandleException.class)
    public BaseHandleException baseHandleExceptionHandler(HttpServletResponse response, BaseException ex) throws Exception{
        int status=401;
        System.out.println("全局捕获异常处理类BaseHandleException:"+ex);
        response.setStatus(status);
        ExceptionInfo exceptionInfo=new ExceptionInfo();
        exceptionInfo.setId(UUID.randomUUID().toString());
        exceptionInfo.setStatus(String.valueOf(status));
        exceptionInfo.setErrMessage(ex.getMessage());
        exceptionInfo.setErrType("BaseHandleException");
        StackTraceElement stackTraceElement=ex.getStackTrace()[0];
        exceptionInfo.setErrClass(stackTraceElement.getClassName());//报错类的路径
        exceptionInfo.setErrFileName(stackTraceElement.getFileName());//报错类的文件名
        exceptionInfo.setErrMethod(stackTraceElement.getMethodName());//报错的方法
        exceptionInfo.setErrLineNumber(stackTraceElement.getLineNumber());//报错的行数
        EntityUtils.setCreateInfo(exceptionInfo);
        try{
            exceptionInfoFeignService.insertExceptionInfo(exceptionInfo);//错误信息落入数据库
        }catch (Exception e){
            throw  new Exception("系统异常！");
        }

        return new BaseHandleException(ex.getMessage(),ex.getStatus());
    }
}
