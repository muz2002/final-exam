package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name="post_likes")
public class Like {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public Post getPost() { return post; }
    public User getUser() { return user; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setPost(Post post) { this.post = post; }
    public void setUser(User user) { this.user = user; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
