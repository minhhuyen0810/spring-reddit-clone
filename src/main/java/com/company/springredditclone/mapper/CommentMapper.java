package com.company.springredditclone.mapper;

import com.company.springredditclone.dto.CommentsDto;
import com.company.springredditclone.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.company.springredditclone.model.Comment;
import com.company.springredditclone.model.User;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    public abstract Comment map(CommentsDto commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    public abstract CommentsDto mapToDto(Comment comment);
}
