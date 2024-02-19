package com.example.blog_spring_mvc.service;

import com.example.blog_spring_mvc.model.Admin;
import com.example.blog_spring_mvc.model.BlogPost;
import com.example.blog_spring_mvc.model.Commentary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

@Service
public class BlogServiceImpl implements IBlogService {

    private final Map<UUID, BlogPost> blogPosts = new LinkedHashMap<>();
    private final Map<UUID, Commentary> commentaries = new LinkedHashMap<>();
    private Admin admin;
    private final ResourceLoader resourceLoader;

    @Autowired
    public BlogServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;

        //Fake the database

        this.admin = Admin.getAdmin();
        System.out.println(admin.toString());


        BlogPost applePie = BlogPost.builder()
                .id(UUID.randomUUID())
                .title("Simple apple pie recipe")
                .postContent(loadFakeContent("fakecontent.txt"))
                .postDate(Date.from(Instant.now()))
                .authorName("Phillipe Etchebest")
                .imageUrl("/image/apple-pie.jpg")
                .build();

        BlogPost mochi = BlogPost.builder()
                .id(UUID.randomUUID())
                .title("Tea mochi")
                .postContent(loadFakeContent("fakecontent.txt"))
                .postDate(Date.from(Instant.now()))
                .authorName("Kim Oh")
                .imageUrl("/image/tea-mochi.jpg")
                .build();

        BlogPost schnecKenKuchen = BlogPost.builder()
                .id(UUID.randomUUID())
                .title("Alsacian Schneckenkuchen")
                .postContent(loadFakeContent("fakecontent.txt"))
                .postDate(Date.from(Instant.now()))
                .authorName("Greta Krotz")
                .imageUrl("/image/german-cake.jpg")
                .build();


        Commentary com1ApplePie = Commentary.builder()
                .userName("andré")
                .email("andre@gmail.com")
                .content("very cool recipe !")
                .blogPost(applePie)
                .build();


        blogPosts.put(applePie.getId(), applePie);
        blogPosts.put(mochi.getId(), mochi);
        blogPosts.put(schnecKenKuchen.getId(), schnecKenKuchen);
        commentaries.put(com1ApplePie.getId(), com1ApplePie);


    }

    // CRUD
    @Override
    public BlogPost saveBlogPost(BlogPost newBlogPost) {
        if (newBlogPost.getId() == null) {
            newBlogPost.setId(UUID.randomUUID());
            newBlogPost.setPostDate(Date.from(Instant.now()));
            return blogPosts.put(newBlogPost.getId(), newBlogPost);
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
        if (blogPostToFind != null) {
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
        if (newCommentary.getId() == null) {
            newCommentary.setId(UUID.randomUUID());
            return commentaries.put(newCommentary.getId(), newCommentary);
        }

        return null;
    }

    @Override
    public Commentary getCommentaryById(UUID id) {
        return commentaries.values().stream().filter(commentary -> commentary.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Commentary> getAllCommentary() {
        return commentaries.values().stream().toList();
    }

    @Override
    public boolean updateCommentary(UUID id, Commentary commentaryToUpdate) {
        Commentary commentaryToFind = getCommentaryById(id);
        if (commentaryToFind != null) {
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

    public String loadFakeContent(String path) {
        try {
            Resource resource = resourceLoader.getResource("classpath:/post-content/" + path);
            try (InputStream inputStream = resource.getInputStream();
                 Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
                return scanner.useDelimiter("\\A").next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error while reading the file";
    }


    public List<Commentary> getAllCommentariesByPostId(UUID postId) {
        List<Commentary> commentaryList = getAllCommentary();

        return commentaryList.stream().filter(commentary -> commentary.getBlogPost() != null && commentary.getBlogPost().getId().equals(postId))
                .toList();


    }


    public boolean signInByPasswordAndEmail(String password,String email){
        if (admin.getPassword().equals(password) && admin.getAdminMail().equals(email)){
            return true;
        } else {
            return false;
        }
    }


}
