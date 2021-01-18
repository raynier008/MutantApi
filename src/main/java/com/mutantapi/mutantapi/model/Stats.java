package com.mutantapi.mutantapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Stats {
    @Id
    private String id;
    private String name;
    private int count_mutant_dna;
    private int count_human_dna;
    private float ratio;

    public Stats(String name3, int count_mutant_dna2, int count_human_dna2, float ratio2) {
        this.name = name3;
        this.count_mutant_dna = count_mutant_dna2;
        this.count_human_dna = count_human_dna2;
        this.ratio = ratio2;
    }

    public Stats() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id2) {
        this.id = id2;
    }

    public String getname() {
        return name;
    }

    public void setname(String name2) {
        this.name = name2;
    }

    public int getcount_mutant_dna() {
        return count_mutant_dna;
    }

    public void setcount_mutant_dna(int count_mutant_dna2) {
        this.count_mutant_dna = count_mutant_dna2;
    }

    public int getcount_human_dna() {
        return count_human_dna;
    }

    public void setcount_human_dna(int count_human_dna2) {
        this.count_human_dna = count_human_dna2;
    }

    public float getratio() {
        return ratio;
    }

    public void setratio(float ratio2) {
        this.ratio = ratio2;
    }

    public void IncreaseCount(boolean mutant) {
        if (mutant) {
            this.count_mutant_dna++;
        } else {
            this.count_human_dna++;
        }

        // CALCULATE RATIO
        this.ratio = CalculateRatio(this.count_mutant_dna, this.count_human_dna);
    }

    private float CalculateRatio(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        return (float) min / max;
    }
}
