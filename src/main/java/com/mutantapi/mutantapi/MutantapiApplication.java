package com.mutantapi.mutantapi;

import com.mutantapi.mutantapi.model.Mutant;
import com.mutantapi.mutantapi.model.Stats;
import com.mutantapi.mutantapi.repository.MutantRepository;
import com.mutantapi.mutantapi.repository.StatsRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class MutantapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantapiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(MutantRepository mutantRepository, StatsRepository statsRepository) {
		return args -> {
			mutantRepository.deleteAll().thenMany(Flux
					.just(new Mutant(new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" }),
							new Mutant(new String[] { "ATGDDA", "CAGTGC", "TAATGT", "AGAAGG", "CDDCTA", "TCACTG" }))
					.flatMap(mutantRepository::save)).thenMany(mutantRepository.findAll())
					.subscribe(System.out::println);

			statsRepository.deleteAll()
					.thenMany(Flux.just(new Stats("TestApiStats", 2, 1, 0.5f)).flatMap(statsRepository::save))
					.thenMany(statsRepository.findAll()).subscribe(System.out::println);
		};
	}

}
