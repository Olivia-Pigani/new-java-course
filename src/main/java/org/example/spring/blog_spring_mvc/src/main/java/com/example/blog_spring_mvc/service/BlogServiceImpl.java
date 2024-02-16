package com.example.blog_spring_mvc.service;

import com.example.blog_spring_mvc.model.BlogPost;
import com.example.blog_spring_mvc.model.Commentary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements IBlogService {

    private final Map<UUID,BlogPost> blogPosts;
    private final Map<UUID,Commentary> commentaries;

    public BlogServiceImpl() {
        this.blogPosts = new HashMap<>();
        this.commentaries = new HashMap<>();


        BlogPost applePie = BlogPost.builder()
                .id(UUID.randomUUID())
                .title("Simple apple pie recipe")
                .postContent("test test")
                .postDate(Date.from(Instant.now()))
                .authorName("Phillipe Etchebest")
                .imageUrl("/image/apple-pie.jpg")
                .build();


        Commentary com1ApplePie = Commentary.builder()
                .userName("andré")
                .email("andre@gmail.com")
                .content("very cool recipe !")
                .blogPost(applePie)
                .build();


        blogPosts.put(applePie.getId(),applePie);
        commentaries.put(com1ApplePie.getId(),com1ApplePie);


    }

    // CRUD
    @Override
    public BlogPost saveBlogPost(BlogPost newBlogPost) {
        if (newBlogPost.getId() == null){
            newBlogPost.setId(UUID.randomUUID());
           return blogPosts.put(newBlogPost.getId(),newBlogPost);
        }

        return null;
    }

    @Override
    public BlogPost getBlogPostById(UUID id) {
        return blogPosts.values().stream().filter(blogPost -> blogPost.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<BlogPost> getAllBlogPost() {
        return blogPosts.values().stream().toList();
    }

    @Override
    public boolean updateBlogPost(UUID id, BlogPost blogPostToUpdate) {
        BlogPost blogPostToFind = getBlogPostById(id);
        if (blogPostToFind != null){
            blogPostToFind.setTitle(blogPostToFind.getTitle());
            blogPostToFind.setPostContent(blogPostToUpdate.getPostContent());
            blogPostToFind.setImageUrl(blogPostToFind.getImageUrl());
            blogPostToFind.setAuthorName(blogPostToFind.getAuthorName());
            blogPostToFind.setPostDate(blogPostToUpdate.getPostDate());

            return true;
        }
        return false;
    }

    @Override
    public void deleteBlogPost(UUID id) {

        blogPosts.remove(id);

    }

    @Override
    public Commentary saveCommentary(Commentary newCommentary) {
        if (newCommentary.getId() == null){
            newCommentary.setId(UUID.randomUUID());
            return commentaries.put(newCommentary.getId(),newCommentary);
        }

        return null;
    }

    @Override
    public Commentary getCommentaryById(UUID id) {
        return commentaries.values().stream().filter(commentary -> commentary.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Commentary> getAllCommentary() {
        return  commentaries.values().stream().toList();
    }

    @Override
    public boolean updateCommentary(UUID id, Commentary commentaryToUpdate) {
        Commentary commentaryToFind = getCommentaryById(id);
        if (commentaryToFind != null){
            commentaryToFind.setUserName(commentaryToUpdate.getUserName());
            commentaryToFind.setEmail(commentaryToUpdate.getEmail());
            commentaryToFind.setContent(commentaryToUpdate.getContent());

            return true;
        }

        return false;
    }

    @Override
    public void deleteCommentary(UUID id) {
        commentaries.remove(id);
    }


    // SPECIFIC METHODS


}
