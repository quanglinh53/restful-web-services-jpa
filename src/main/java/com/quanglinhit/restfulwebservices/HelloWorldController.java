package com.quanglinhit.restfulwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

//    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello John Baron";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloBean(){
        return new HelloWorldBean("Hello bean");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello , %s", name));
    }

    @GetMapping(path = "/internationalized")
    public String international(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("good.morning.messages", null, locale);
    }
}
