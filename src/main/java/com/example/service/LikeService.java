package com.example.service;
import com.example.entity.Like;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.repository.LikeRepository;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public void likePost(Long postId) {
        Post p = postRepository.findById(postId).orElseThrow();
        Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findByUsername(auth.getName()).orElseThrow();

        // Check if already liked
        var existing = likeRepository.findByPostIdAndUserId(postId, u.getId());
        if (existing.isEmpty()) {
            Like l = new Like();
            l.setPost(p);
            l.setUser(u);
            likeRepository.save(l);

            p.setLikesCount(p.getLikesCount() + 1);
            postRepository.save(p);
        }
    }
}

