package com.mutantapi.mutantapi.repository;

import com.mutantapi.mutantapi.model.Mutant;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MutantRepository extends ReactiveMongoRepository<Mutant, String> {
    Mutant findByDna(String[] Dna);
}