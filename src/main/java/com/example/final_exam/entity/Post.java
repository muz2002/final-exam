package com.example.final_exam.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    private User author;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    private int views;
    private int likesCount;

    @OneToMany(mappedBy = "post", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Comment> comments = new ArrayList<>();

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public User getAuthor() { return author; }
    public Category getCategory() { return category; }
    public int getViews() { return views; }
    public int getLikesCount() { return likesCount; }
    public List<Comment> getComments() { return comments; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setAuthor(User author) { this.author = author; }
    public void setCategory(Category category) { this.category = category; }
    public void setViews(int views) { this.views = views; }
    public void setLikesCount(int likesCount) { this.likesCount = likesCount; }
}
