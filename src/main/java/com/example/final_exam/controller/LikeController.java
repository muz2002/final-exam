package com.example.final_exam.controller;

import com.example.final_exam.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/post/{postId}")
    public String likePost(@PathVariable Long postId) {
        likeService.likePost(postId);
        return "Post liked";
    }
}

