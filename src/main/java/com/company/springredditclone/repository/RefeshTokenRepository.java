package com.company.springredditclone.repository;

import com.company.springredditclone.model.RefeshToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefeshTokenRepository extends JpaRepository<RefeshToken,Long> {

    Optional<RefeshToken> findByToken(String token);

    void deleteByToken(String token);
}
