package com.example.final_exam.controller;

import com.example.final_exam.dto.PostRequest;
import com.example.final_exam.entity.Post;
import com.example.final_exam.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts(@RequestParam(required=false) String category) {
        return postService.findAllPosts(category);
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostByIdAndIncrementViews(id);
    }

    @PostMapping
    public Post createPost(@RequestBody PostRequest request) {
        return postService.createPost(request);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
        return postService.updatePost(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @GetMapping("/popular")
    public List<Post> getPopularPosts(@RequestParam(defaultValue = "10") int size) {
        return postService.getPopularPosts(PageRequest.of(0,size));
    }
}
