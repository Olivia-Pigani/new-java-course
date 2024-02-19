package com.example.blog_spring_mvc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;


    @NotNull(message = "must contain a title")
    @NotBlank
    private String title;

    @NotNull(message = "must contain a content")
    @NotBlank
    @Size(min = 10, max = 20_000)
    private String postContent;

    private String imageUrl;

    @Size(max = 100)
    private String authorName;


    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date postDate;


    private List<Commentary> commentaryList = new ArrayList<>();


}
