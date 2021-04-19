package com.company.springredditclone.mapper;

import com.company.springredditclone.dto.SubredditDTO;
import com.company.springredditclone.dto.SubredditDTO.SubredditDTOBuilder;
import com.company.springredditclone.model.Subreddit;
import com.company.springredditclone.model.Subreddit.SubredditBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-16T11:46:23+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class SubredditMapperImpl implements SubredditMapper {

    @Override
    public SubredditDTO mapSubredditToDto(Subreddit subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubredditDTOBuilder subredditDTO = SubredditDTO.builder();

        subredditDTO.id( subreddit.getId() );
        subredditDTO.name( subreddit.getName() );
        subredditDTO.description( subreddit.getDescription() );

        subredditDTO.numberOfPosts( mapPosts(subreddit.getPosts()) );

        return subredditDTO.build();
    }

    @Override
    public Subreddit mapDtoToSubreddit(SubredditDTO subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubredditBuilder subreddit1 = Subreddit.builder();

        subreddit1.id( subreddit.getId() );
        subreddit1.name( subreddit.getName() );
        subreddit1.description( subreddit.getDescription() );

        return subreddit1.build();
    }
}
