package com.company.springredditclone.mapper;

import com.company.springredditclone.dto.SubredditDTO;
import com.company.springredditclone.model.Post;
import com.company.springredditclone.model.Subreddit;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDTO mapSubredditToDto(Subreddit subreddit);
    default Integer mapPosts(List<Post> numberOfPosts){
        return numberOfPosts.size();
    }
    @InheritConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDTO subreddit);

}
