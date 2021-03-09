package com.rgy.dice.dto;

import java.util.Objects;

public class DistributionDTO {

    private Integer sumOf;
    private String freq;

    public DistributionDTO(Integer sum, String freq) {
        this.sumOf = sum;
        this.freq = freq;
    }

    public Integer getSumOf() {
        return sumOf;
    }

    public void setSumOf(Integer sumOf) {
        this.sumOf = sumOf;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistributionDTO that = (DistributionDTO) o;
        return Objects.equals(sumOf, that.sumOf) &&
                Objects.equals(freq, that.freq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sumOf, freq);
    }

    @Override
    public String toString() {
        return "DistributionDTO{" +
                "sumOf=" + sumOf +
                ", freq='" + freq + '\'' +
                '}';
    }
}
