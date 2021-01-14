package com.mutantapi.mutantapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Mutant {
    @Id
    private String id;

    private String[] Dna;

    public Mutant() {

    }

    public Mutant(String[] Dna) {
        this.Dna = Dna;
    }

    public String getId() {
        return id;
    }

    public void setId(String id2) {
        this.id = id2;
    }

    public String[] getDna() {
        return this.Dna;
    }

    public void setDna(String[] Dna2) {
        this.Dna = Dna2;
    }

}
