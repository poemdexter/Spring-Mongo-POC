package com.poemdexter;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {

	// automatically implemented by Spring Data Mongo
	public Player findByUsername(String username);
}
