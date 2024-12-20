package com.example.final_exam.dto;

public class PostRequest {
    private String title;
    private String content;
    private Long categoryId; // optional
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}
