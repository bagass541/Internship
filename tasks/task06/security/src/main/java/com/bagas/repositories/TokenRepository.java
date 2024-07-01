package com.bagas.repositories;

import com.bagas.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("SELECT t FROM Token t join User u on t.user.id = u.id where t.user.id = :userId and t.loggedOut = false")
    List<Token> findAllAccessTokensByUser(long userId);

    Optional<Token> findByAccessToken(String token);
}
