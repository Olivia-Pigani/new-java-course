package com.example.blog_spring_mvc.controller;

import com.example.blog_spring_mvc.dto.AdminDTO;
import com.example.blog_spring_mvc.entity.BlogPost;
import com.example.blog_spring_mvc.entity.Commentary;
import com.example.blog_spring_mvc.service.BlogServiceImpl;
import com.example.blog_spring_mvc.service.IBlogService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    // HOME
    @GetMapping("/")
    public String getHome(Model model) {
        List<BlogPost> blogPosts = blogService.getAllBlogPost();
        model.addAttribute("blogPosts", blogPosts);
        model.addAttribute("blogName", blogName);
        model.addAttribute("contactEmail", contactEmail);
        return "home";
    }

    // BLOG POST DETAILS

    @GetMapping("/details/{postId}")
    public String getPostDetailsAndCommentaries(@PathVariable("postId") UUID id, Model model) {
        BlogPost blogPost = blogService.getBlogPostById(id);

        BlogServiceImpl castedRepo = (BlogServiceImpl) blogService;
        List<Commentary> commentaryList = castedRepo.getAllCommentariesByPostId(id);

        if (blogPost != null) {
            model.addAttribute("blogPost", blogPost);
            model.addAttribute("commentaryList", commentaryList);
            model.addAttribute("blogName", blogName);
            model.addAttribute("contactEmail", contactEmail);
            return "post-details";
        } else {
            return "redirect:/";
        }

    }

    // BLOG POST FORM

    @GetMapping("/post-form")
    public String getThePostForm(Model model) {

        model.addAttribute("blogPost", new BlogPost());

        return "post-form";

    }


    @PostMapping("/addAPost")
    public String addAPostLogic(@Valid @ModelAttribute BlogPost blogPost, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("blogPost", blogPost);
            return "post-form";
        }else {
            blogService.saveBlogPost(blogPost);
            return "redirect:/";
        }
    }

    // COMMENTARY

    @GetMapping("/com-form/{postId}")
    public String getCommentaryFormByPostId(@PathVariable("postId") UUID id, Model model) {
        BlogPost blogPost = blogService.getBlogPostById(id);
        if (blogPost != null) {
            model.addAttribute("blogPost", blogPost);
            model.addAttribute("commentary", new Commentary());
            return "com-form";

        } else {
            return "redirect:/details/{postId}";
        }
    }

    @PostMapping("/addCommentary/{postId}")
    public String submitACommentaryForAPost(@PathVariable("postId") UUID id, @Valid @ModelAttribute("commentary") Commentary commentary, BindingResult bindingResult, Model model) {
        BlogPost blogPost = blogService.getBlogPostById(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("blogPost", blogPost);
            return "com-form";
        } else {
            commentary.setBlogPost(blogPost);
            blogService.saveCommentary(commentary);
            return "redirect:/details/" + id;
        }

    }


    // AUTHENTIFICATION

    @GetMapping("/auth-form")
    public String getAuthForm(Model model) {
        model.addAttribute("adminDTO", new AdminDTO());
        return "auth-form";
    }

    @PostMapping("/signin")
    public String signIn(@ModelAttribute("adminDTO") AdminDTO adminDTO, Model model, HttpSession session) {
        BlogServiceImpl castedService = (BlogServiceImpl) blogService;
        if (castedService.signInByPasswordAndEmail(adminDTO.getPassword(), adminDTO.getAdminMail())) {
            session.setAttribute("isLogged", true);
            return "redirect:/";
        } else {
            session.setAttribute("isLogged", false);
            return "auth-form";
        }

    }

    @GetMapping("signout")
    public String signOut(HttpSession session) {
        session.setAttribute("isLogged", false);
        return "redirect:/";
    }



}
