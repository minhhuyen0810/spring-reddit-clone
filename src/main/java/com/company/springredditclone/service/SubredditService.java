package com.company.springredditclone.service;

import com.company.springredditclone.dto.SubredditDTO;
import com.company.springredditclone.exception.SpringRedditException;
import com.company.springredditclone.mapper.SubredditMapper;
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
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDTO save(SubredditDTO subredditDTO) {

        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDTO));
        subredditDTO.setId(save.getId());
        return subredditDTO;
    }


    @Transactional(readOnly = true)
    public List<SubredditDTO> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());

    }

    public SubredditDTO getSubreddit(Long id) throws SpringRedditException {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}


