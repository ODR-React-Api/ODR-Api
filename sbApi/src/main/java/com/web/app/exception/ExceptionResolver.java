package com.web.app.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
只要在实现了 HandlerExceptionResolver 接口的类上，增加了 @Component 注解，
能够让 SpringMvc 装载，那么当出现相应类型的异常后，就会自动捕获执行该类的相应的方法处理

需要注意的是：实现了 HandlerExceptionResolver 接口的异常处理类，加载的比较晚，
在 Controller 接收完参数后，才会进行异常监控，
所以当 Controller 接收参数中出现问题时（比如类型转换错误），这里是监控不到的，
因此在实际开发中，很少使用这种全局异常处理方案。

比较不爽的是：这里要返回一个 ModelAndView ，也就是需要跳转到一个页面，因此不够灵活
*/

//此处的 ExceptionResolver 和 ExceptionAdvice 的 @Component 注解不要同时开启
//@Component
public class ExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {

        System.out.println("捕获到了异常：" + ex);

        // 根据异常类型，确定异常处理方式
        ModelAndView mv = new ModelAndView();
        if (ex instanceof NullPointerException) {
            mv.addObject("msg", "发生了空指针异常");
        } else if (ex instanceof ArithmeticException) {
            mv.addObject("msg", "发生了算数运算异常");
        } else {
            mv.addObject("msg", ex.getMessage());
        }
        mv.setViewName("error");
        return mv;
    }
}