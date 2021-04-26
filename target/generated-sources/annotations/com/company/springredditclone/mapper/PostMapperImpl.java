package com.company.springredditclone.mapper;

import com.company.springredditclone.dto.PostRequest;
import com.company.springredditclone.dto.PostResponse;
import com.company.springredditclone.model.Post;
import com.company.springredditclone.model.Subreddit;
import com.company.springredditclone.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-21T16:18:15+0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class PostMapperImpl extends PostMapper {

    @Override
    public Post map(PostRequest postRequest, Subreddit subreddit, User user) {
        if ( postRequest == null && subreddit == null && user == null ) {
            return null;
        }

        Post post = new Post();

        if ( postRequest != null ) {
            post.setDescription( postRequest.getDescription() );
            post.setPostId( postRequest.getPostId() );
            post.setPostName( postRequest.getPostName() );
            post.setUrl( postRequest.getUrl() );
        }
        if ( subreddit != null ) {
            post.setSubreddit( subreddit );
        }
        if ( user != null ) {
            post.setUser( user );
        }
        post.setCreatedDate( java.time.Instant.now() );

        return post;
    }

    @Override
    public PostResponse mapToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setId( post.getPostId() );
        postResponse.setPostName( post.getPostName() );
        postResponse.setDescription( post.getDescription() );
        postResponse.setUrl( post.getUrl() );
        postResponse.setSubredditName( postSubredditName( post ) );
        postResponse.setUserName( postUserUsername( post ) );
        postResponse.setVoteCount( post.getVoteCount() );

        return postResponse;
    }

    private String postSubredditName(Post post) {
        if ( post == null ) {
            return null;
        }
        Subreddit subreddit = post.getSubreddit();
        if ( subreddit == null ) {
            return null;
        }
        String name = subreddit.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String postUserUsername(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
