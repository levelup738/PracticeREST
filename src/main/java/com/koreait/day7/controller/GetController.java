package com.koreait.day7.controller;

import com.koreait.day7.model.network.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api") // localhost:9090/api
public class GetController {

    // localhost:9090/api/getMethod
    @RequestMapping(method = RequestMethod.GET, path="/getMethod")
    public String getRequest(){
        return "API Test!";
    }

    // localhost:9090/api/header
    @GetMapping("/header")
    public Header getHeader(){
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
