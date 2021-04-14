package com.company.springredditclone.service;

import com.company.springredditclone.dto.SubredditDTO;
import com.company.springredditclone.model.Subreddit;
import com.company.springredditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
    private final SubredditRepository subredditRepository;

    @Transactional
    public SubredditDTO save(SubredditDTO subredditDTO){
        Subreddit subreddit = mapSubredditDto(subredditDTO);
        Subreddit save = subredditRepository.save(subreddit);
        subredditDTO.setId(save.getId());
        return subredditDTO;
    }

    private Subreddit mapSubredditDto(SubredditDTO subredditDTO) {
       return Subreddit.builder().name(subredditDTO.getName())
                .description(subredditDTO.getDescription())
                .build();
    }
    @Transactional(readOnly = true)
    public List<SubredditDTO> getAll(){
    return subredditRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

    }

    private SubredditDTO mapToDto(Subreddit subreddit) {
        return SubredditDTO.builder()
                .name(subreddit.getName())
                .id(subreddit.getId())
                .numberOfPosts(subreddit.getPosts().size())
                .build();
    }
}
