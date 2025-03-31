package com.example.demotbl.controller;


import com.example.demotbl.models.ReqeustTest;
import com.example.demotbl.models.ResponseTest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class RestControllerTest {

    @PostMapping("/hello")
    public ResponseTest getHello(@RequestBody ReqeustTest reqeustTest) {
        System.out.println(reqeustTest.toString());
        reqeustTest.setId(100);
        ResponseTest responseTest = new ResponseTest();
        responseTest.setData(reqeustTest);
        responseTest.setCodigo("0404");
        responseTest.setMessage("Faltaron campos");
        return responseTest;
    }

    @GetMapping("/hello2")
    public String getHello2( @RequestBody ReqeustTest reqeustTest) {
        System.out.println(reqeustTest.toString());
        reqeustTest.setId(100);
        return "Hola test ";
    }
}
