package com.example.service;

import com.example.dto.CommentRequest;
import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Comment> getCommentsForPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment addComment(Long postId, CommentRequest request) {
        Post p = postRepository.findById(postId).orElseThrow();
        Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findByUsername(auth.getName()).orElseThrow();
        Comment c = new Comment();
        c.setPost(p);
        c.setUser(u);
        c.setContent(request.getContent());
        return commentRepository.save(c);
    }
}


