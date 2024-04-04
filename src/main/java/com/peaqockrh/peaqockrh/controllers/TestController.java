package com.peaqockrh.peaqockrh.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<?> testApi(){
        return ResponseEntity.ok(Arrays.asList("hhjh","ghghgh"));
    }

}
