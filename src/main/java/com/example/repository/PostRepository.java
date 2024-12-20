package com.example.repository;
import com.example.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p ORDER BY (p.views + p.likesCount) DESC")
    List<Post> findPopularPosts(Pageable pageable);
}

