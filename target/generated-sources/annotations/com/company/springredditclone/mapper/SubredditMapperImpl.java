package com.company.springredditclone.mapper;

import com.company.springredditclone.dto.SubredditDTO;
import com.company.springredditclone.model.Subreddit;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-21T16:18:15+0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class SubredditMapperImpl implements SubredditMapper {

    @Override
    public SubredditDTO mapSubredditToDto(Subreddit subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubredditDTO subredditDTO = new SubredditDTO();

        subredditDTO.setId( subreddit.getId() );
        subredditDTO.setName( subreddit.getName() );
        subredditDTO.setDescription( subreddit.getDescription() );

        subredditDTO.setNumberOfPosts( mapPosts(subreddit.getPosts()) );

        return subredditDTO;
    }

    @Override
    public Subreddit mapDtoToSubreddit(SubredditDTO subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        Subreddit subreddit1 = new Subreddit();

        subreddit1.setId( subreddit.getId() );
        subreddit1.setName( subreddit.getName() );
        subreddit1.setDescription( subreddit.getDescription() );

        return subreddit1;
    }
}
