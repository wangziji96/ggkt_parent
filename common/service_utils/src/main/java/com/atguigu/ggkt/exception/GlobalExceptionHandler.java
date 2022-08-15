package com.atguigu.ggkt.exception;

import com.atguigu.ggkt.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author wzj
 * @Date 2022/8/15 11:10
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)//指定针对那个异常处理，所有异常继承Exception
    @ResponseBody
    public Result error(Exception e){
        System.out.println("全局...");
        e.printStackTrace();
        return Result.fail(null).message("执行全局异常处理");
    }

    //特定异常处理ArithmeticException
    @ExceptionHandler(ArithmeticException.class)//指定针对那个异常处理，所有异常继承Exception
    @ResponseBody
    public Result error(ArithmeticException e){
        System.out.println("特定...");
        e.printStackTrace();
        return Result.fail(null).message("执行ArithmeticException异常处理");
    }

    //GgktException
    //特定异常处理ArithmeticException
    @ExceptionHandler(GgktException.class)//指定针对那个异常处理，所有异常继承Exception
    @ResponseBody
    public Result error(GgktException e){
        e.printStackTrace();
        //当手动抛出异常是，可以将设置的状态码和信息动态地添加进来
        return Result.fail(null).code(e.getCode()).message(e.getMsg());
    }

}
