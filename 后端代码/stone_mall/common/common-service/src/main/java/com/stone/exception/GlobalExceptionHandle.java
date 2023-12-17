package com.stone.exception;

import com.stone.model.vo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandle {
    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result errorHandle(Exception e){
        e.printStackTrace();
        return Result.fail(null).message(e.getMessage());
    }
    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result errorHandle(ArithmeticException e){
        e.printStackTrace();
        return Result.fail(null).message("执行特定异常方法");
    }
    /**
     * spring security异常
     * @param e
     * @return
     */
    //自定义异常处理
    @ExceptionHandler(StoneException.class)
    @ResponseBody
    public Result errorHandle(StoneException e){
        e.printStackTrace();
        return Result.fail(null).code(e.getCode()).message(e.getMsg());
    }
}
