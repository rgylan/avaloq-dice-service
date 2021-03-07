package com.rgy.dice.dto;

import java.util.Objects;

public class DiceSumDTO {

    private Integer rollSum;
    private Integer countOfRollSum;

    public Integer getRollSum() {
        return rollSum;
    }

    public void setRollSum(Integer rollSum) {
        this.rollSum = rollSum;
    }

    public Integer getCountOfRollSum() {
        return countOfRollSum;
    }

    public void setCountOfRollSum(Integer countOfRollSum) {
        this.countOfRollSum = countOfRollSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiceSumDTO that = (DiceSumDTO) o;
        return Objects.equals(rollSum, that.rollSum) &&
                Objects.equals(countOfRollSum, that.countOfRollSum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollSum, countOfRollSum);
    }

    @Override
    public String toString() {
        return "DiceSumDTO{" +
                "rollSum=" + rollSum +
                ", countOfRollSum=" + countOfRollSum +
                '}';
    }
}
