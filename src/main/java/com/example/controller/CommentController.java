package com.example.controller;

import com.example.dto.CommentRequest;
import com.example.entity.Comment;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{postId}")
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.getCommentsForPost(postId);
    }

    @PostMapping("/post/{postId}")
    public Comment addComment(@PathVariable Long postId, @RequestBody CommentRequest request) {
        return commentService.addComment(postId, request);
    }
}
