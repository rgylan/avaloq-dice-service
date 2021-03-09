package com.rgy.dice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiceResponseDTO {

    private Long totalSimulation;
    private Long totalRoll;
    private Integer numOfDice;
    private Integer side;
    private Long roll;

    private Map<Integer, Integer> distribution;

    public DiceResponseDTO(Long roll, Map<Integer, Integer> distribution) {
        this.roll = roll;
        this.distribution = distribution;
    }

    public DiceResponseDTO(Long totalSimulation, Long totalRoll, Integer numOfDevice,
                           Integer side) {
        this.totalSimulation = totalSimulation;
        this.totalRoll = totalRoll;
        this.numOfDice = numOfDevice;
        this.side = side;
    }

    public Long getTotalSimulation() {
        return totalSimulation;
    }

    public void setTotalSimulation(Long totalSimulation) {
        this.totalSimulation = totalSimulation;
    }

    public Long getTotalRoll() {
        return totalRoll;
    }

    public void setTotalRoll(Long totalRoll) {
        this.totalRoll = totalRoll;
    }

    public Integer getNumOfDice() {
        return numOfDice;
    }

    public void setNumOfDice(Integer numOfDice) {
        this.numOfDice = numOfDice;
    }

    public Integer getSide() {
        return side;
    }

    public void setSide(Integer side) {
        this.side = side;
    }

    public Map<Integer, Integer> getDistribution() {
        return distribution;
    }

    public Long getRoll() {
        return roll;
    }

    public void setRoll(Long roll) {
        this.roll = roll;
    }

    public void setDistribution(Map<Integer, Integer> distribution) {
        this.distribution = distribution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiceResponseDTO that = (DiceResponseDTO) o;
        return Objects.equals(totalSimulation, that.totalSimulation) &&
                Objects.equals(totalRoll, that.totalRoll) &&
                Objects.equals(numOfDice, that.numOfDice) &&
                Objects.equals(side, that.side) &&
                Objects.equals(roll, that.roll) &&
                Objects.equals(distribution, that.distribution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalSimulation, totalRoll, numOfDice, side, roll, distribution);
    }

    @Override
    public String toString() {
        return "DiceResponseDTO{" +
                "totalSimulation=" + totalSimulation +
                ", totalRoll=" + totalRoll +
                ", numOfDice=" + numOfDice +
                ", side=" + side +
                ", roll=" + roll +
                ", distribution=" + distribution +
                '}';
    }
}
