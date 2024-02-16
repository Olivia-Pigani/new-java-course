package com.example.blog_spring_mvc.controller;

import com.example.blog_spring_mvc.model.BlogPost;
import com.example.blog_spring_mvc.service.IBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogController {


    private final IBlogService blogService;


    @Value("my recipes blog")
    private String blogName;

    @Value("hello@myrecipes.com")
    private String contactEmail;

    @GetMapping
    public String getHome(Model model) {
        List<BlogPost> blogPosts = blogService.getAllBlogPost();
        model.addAttribute("blogPosts",blogPosts);
        model.addAttribute("blogName",blogName);
        model.addAttribute("contactEmail",contactEmail);
        return "home";
    }

    @GetMapping("/posts-list")
    public String getAllPosts(Model model){
        List<BlogPost> blogPosts = blogService.getAllBlogPost();
        model.addAttribute("blogPosts",blogPosts);
        return "posts-list";
    }


}
