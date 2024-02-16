package com.example.blog_spring_mvc.controller;

import com.example.blog_spring_mvc.service.IBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BlogController {


    private final IBlogService blogService;


    @GetMapping
    public String getHome(){
        return "home";
    }




}
