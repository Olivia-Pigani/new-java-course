package com.example.blog_spring_mvc.controller;

import com.example.blog_spring_mvc.model.BlogPost;
import com.example.blog_spring_mvc.model.Commentary;
import com.example.blog_spring_mvc.service.BlogServiceImpl;
import com.example.blog_spring_mvc.service.IBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/details/{postId}")
    public String getPostDetailsAndCommentaries(@PathVariable("postId")UUID id, Model model){
        BlogPost blogPost = blogService.getBlogPostById(id);

        BlogServiceImpl castedRepo = (BlogServiceImpl) blogService;
        List<Commentary> commentaryList = castedRepo.getAllCommentariesByPostId(id);

        if (blogPost != null){
            model.addAttribute("blogPost",blogPost);
            model.addAttribute("commentaryList",commentaryList);
            model.addAttribute("blogName",blogName);
            model.addAttribute("contactEmail",contactEmail);
            return "post-details";
        }else {
            return "redirect:/";
        }

        }




}
