package com.mutantapi.mutantapi.controller;

import com.mutantapi.mutantapi.model.Stats;
import com.mutantapi.mutantapi.repository.StatsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/stats")
public class StatsController {
    @Autowired
    private StatsRepository statsRepository;

    public StatsController() {

    }

    @GetMapping("/")
    public Flux<Stats> ListMutants() {
        return statsRepository.findAll();
    }

    public void saveStats(boolean mutant) {
        Stats getStats = statsRepository.findAll().blockFirst();
        getStats.IncreaseCount(mutant);
        statsRepository.save(getStats).subscribe();
    }
}
