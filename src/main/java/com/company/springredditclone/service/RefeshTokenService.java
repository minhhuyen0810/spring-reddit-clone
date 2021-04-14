package com.company.springredditclone.service;

import com.company.springredditclone.exception.SpringRedditException;
import com.company.springredditclone.model.RefeshToken;
import com.company.springredditclone.repository.RefeshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefeshTokenService {

    private final RefeshTokenRepository refeshTokenRepository;

    public RefeshToken generateRefreshToken() {
        RefeshToken refreshToken = new RefeshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refeshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token) throws SpringRedditException {
        refeshTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpringRedditException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
        refeshTokenRepository.deleteByToken(token);
    }




}
