package com.mutantapi.mutantapi.repository;

import com.mutantapi.mutantapi.model.Stats;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends ReactiveMongoRepository<Stats, String> {
}