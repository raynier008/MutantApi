package com.mutantapi.mutantapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Mutant {
    @Id
    private String id;

    private String[] dna;

    public Mutant() {

    }

    public Mutant(String[] dna2) {
        this.dna = dna2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id2) {
        this.id = id2;
    }

    public String[] getDna() {
        return this.dna;
    }

    public void setDna(String[] dna2) {
        this.dna = dna2;
    }

}
