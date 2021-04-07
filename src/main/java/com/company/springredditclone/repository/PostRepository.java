package com.company.springredditclone.repository;

import com.company.springredditclone.model.Post;
import com.company.springredditclone.model.Subreddit;
import com.company.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);


}
