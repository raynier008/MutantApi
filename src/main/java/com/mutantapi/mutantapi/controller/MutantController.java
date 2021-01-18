package com.mutantapi.mutantapi.controller;

import com.mutantapi.mutantapi.repository.MutantRepository;
import com.mutantapi.mutantapi.model.Mutant;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    @Autowired
    private MutantRepository mutantRepository;
    private StatsController statsController;

    public MutantController(StatsController _statsController) {
        this.statsController = _statsController;
    }

    // #region Public

    @GetMapping("/")
    public Flux<Mutant> ListMutants() {
        return mutantRepository.findAll();
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<Mutant>> create(@RequestBody Mutant isMutant) {
        return mutantRepository.save(isMutant).map(savedMutant -> ResponseEntity.ok(savedMutant));
    }

    @PostMapping("/")
    public Mono<ResponseEntity<Mutant>> ValidateISMutant(@RequestBody Mutant isMutant) {
        // VALIDAMOS SI ES MUTANTE
        boolean mutant = isMutant(isMutant);
        if (mutant) {
            Mutant mut = mutantRepository.findByDna(isMutant.getDna()).block();
            if (mut == null) {
                mutantRepository.save(isMutant).map(savedMutant -> ResponseEntity.ok(savedMutant)).subscribe();
            }
        }
        // ACTUALIZAMOS
        statsController.saveStats(mutant);
        // DEVOLVEMOS!
        return Mono.just(new ResponseEntity<>(mutant ? HttpStatus.OK : HttpStatus.FORBIDDEN));
    }

    // #endregion

    // #region Private
    private boolean isMutant(Mutant mutant) {
        // OBTENEMOS EL DNA
        for (String secuencia : mutant.getDna()) {
            // CONVERTIMOS EN LISTA
            char[] chars = secuencia.toCharArray();
            // CREAMOS LISTA A VALIDAR SI SE REPITE
            Map<Character, Integer> map = new HashMap<>();
            // BUSCAMOS EN EL ARREGLO SI EXISTE SUMAMOS SINO AGREGAMOS
            for (char c : chars) {
                if (map.containsKey(c)) {
                    int counter = map.get(c);
                    map.put(c, ++counter);
                } else {
                    map.put(c, 1);
                }
            }

            // SI EXISTE UNA SECUENCIA DE 4 LETRAS ES MUTANTE (TRUE)
            for (char c : map.keySet()) {
                if (map.get(c) == 4) {
                    return true;
                }
            }
        }
        return false;
    }
    // #endregion
}