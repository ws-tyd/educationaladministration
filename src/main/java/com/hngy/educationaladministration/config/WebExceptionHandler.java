package com.hngy.educationaladministration.config;

import com.hngy.educationaladministration.Exception.LoginTimeoutException;
import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 统一异常处理器
 */

@ControllerAdvice
//@ResponseBody
public class WebExceptionHandler {

    @Autowired
    HttpServletResponse response;

    private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler
    public Msg methodArgumentNotValidException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        String errorMsg = "";
        int index= 1;
        for(FieldError e: result.getFieldErrors())
        {
            errorMsg += index++ +"."+ e.getDefaultMessage()+"<br>";
        }

        return Msg.error(errorMsg);
    }


    /**
     * 登录超时
     * @param e
     * @throws IOException
     */
    @ResponseBody
    @ExceptionHandler
    public String LoginTimeOutException(LoginTimeoutException e) throws IOException {
//        response.sendError(404);
//        response.sendRedirect("404");
        return "登录超时 或 没有登录";
    }

    @ResponseBody
    @ExceptionHandler
    public Msg myException(MyException e){
//        e.printStackTrace();
        return Msg.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler
    public Msg SQL1(DataIntegrityViolationException sqlException)
    {
        return Msg.error("数据冲突，或缺失！");
    }

    @ResponseBody
    @ExceptionHandler
    public Msg httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        return Msg.error("请求方法不存在");
    }

    /**
     * 一切其他异常都跳404
     * @param e
     * @throws IOException
     */

    @ResponseBody
    @ExceptionHandler
    public String exception(MaxUploadSizeExceededException e) {
        log.error("{}","文件过大");
        return "文件过大";
    }


    @ExceptionHandler
    public void exception(Exception e) throws IOException {
        log.error("{}",e);
        response.setStatus(404);
        response.sendRedirect("/error/404");
    }





}
