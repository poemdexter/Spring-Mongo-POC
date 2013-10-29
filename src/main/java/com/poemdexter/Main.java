package com.poemdexter;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories  // Spring Data will implement repos automatically
public class Main {

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	PlayerRepository playerRepository;

	@Bean  // links application to mongoDB server
	Mongo mongo() throws UnknownHostException {
		return new Mongo("localhost");
	}

	@Bean  // executes queries
	MongoTemplate mongoTemplate(Mongo mongo) {
		return new MongoTemplate(mongo, "spring-mongo-poc");
	}

	public static void main(String[] args) {

		// get repo from application context
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		PlayerRepository repository = context.getBean(PlayerRepository.class);

		// delete entities managed by repo
		repository.deleteAll();

		// create new players
		repository.save(new Player("poemdexter", "password123"));
		repository.save(new Player("syntaxrigger", "monkey123"));

		// find all players
		for (Player player : repository.findAll()) {
			System.out.println("FIND ALL PLAYERS: " + player.toString());
		}

		// find specific player
		Player p = repository.findByUsername("poemdexter");
		System.out.println("FIND SPECIFIC PLAYER: " + p.toString());

		// close application context
		context.close();
	}
}
