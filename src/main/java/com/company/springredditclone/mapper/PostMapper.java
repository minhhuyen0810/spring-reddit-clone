package com.company.springredditclone.mapper;

import com.company.springredditclone.dto.PostRequest;
import com.company.springredditclone.dto.PostResponse;
import com.company.springredditclone.model.Post;
import com.company.springredditclone.model.Subreddit;
import com.company.springredditclone.repository.VoteRepository;
import com.company.springredditclone.service.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.company.springredditclone.repository.CommentRepository;
import com.company.springredditclone.model.User;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;
    @Mapping(target = "createdDate",expression = "java(java.time.Instant.now())")
    @Mapping(target = "subreddit",source = "subreddit")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "description",source = "postRequest.description")
public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "postName", source = "postName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "subredditName",source = "subreddit.name")
    @Mapping(target = "userName",source = "user.username")
public abstract     PostResponse mapToDto(Post post);

    Integer commentCount(Post post){
        return commentRepository.findByPost(post).size();
    }
    String getDuration(Post post){
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }
}
