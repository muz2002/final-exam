package com.example.service;

import com.example.dto.PostRequest;
import com.example.entity.Category;
import com.example.entity.Post;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.repository.CategoryRepository;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Post> findAllPosts(String categoryName) {
        if (categoryName == null) {
            return postRepository.findAll();
        } else {
            // Simple filter in memory (could do a query)
            return postRepository.findAll().stream()
                    .filter(p -> p.getCategory() != null && p.getCategory().getName().equalsIgnoreCase(categoryName))
                    .toList();
        }
    }

    public Post getPostByIdAndIncrementViews(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setViews(post.getViews() + 1);
        return postRepository.save(post);
    }

    public Post createPost(PostRequest request) {
        Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        User author = userRepository.findByUsername(auth.getName()).orElseThrow();
        Post p = new Post();
        p.setTitle(request.getTitle());
        p.setContent(request.getContent());
        p.setAuthor(author);
        if (request.getCategoryId() != null) {
            Category cat = categoryRepository.findById(request.getCategoryId()).orElse(null);
            p.setCategory(cat);
        }
        return postRepository.save(p);
    }

    public Post updatePost(Long id, PostRequest request) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        if (request.getCategoryId() != null) {
            Category cat = categoryRepository.findById(request.getCategoryId()).orElse(null);
            post.setCategory(cat);
        }
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> getPopularPosts(Pageable pageable) {
        return postRepository.findPopularPosts(pageable);
    }
}
