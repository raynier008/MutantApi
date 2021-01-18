package com.mutantapi.mutantapi.repository;

import com.mutantapi.mutantapi.model.Mutant;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface MutantRepository extends ReactiveMongoRepository<Mutant, String> {
    Mono<Mutant> findByDna(String[] Dna);
}