package com.example.final_exam.controller;

import com.example.final_exam.dto.CommentRequest;
import com.example.final_exam.entity.Comment;
import com.example.final_exam.service.CommentService;
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
