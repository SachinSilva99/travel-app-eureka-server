package com.sachin.userservice.repo;

import com.sachin.userservice.entity.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepo extends MongoRepository<Token,String> {
    List<Token> findAllValidTokenByUserId(String userId);
    @Query("{'token': ?0, 'expired': false, 'revoked': false}")
    Token isTokenValid(String token);

    Token findByToken(String token);
}
