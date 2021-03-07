package com.rgy.dice.dto;

import java.util.Objects;

public class DiceRequestDTO {

    private Integer roll;
    private Integer dice;
    private Integer side;

    public Integer getRoll() {
        return roll;
    }

    public void setRoll(Integer roll) {
        this.roll = roll;
    }

    public Integer getDice() {
        return dice;
    }

    public void setDice(Integer dice) {
        this.dice = dice;
    }

    public Integer getSide() {
        return side;
    }

    public void setSide(Integer side) {
        this.side = side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiceRequestDTO that = (DiceRequestDTO) o;
        return Objects.equals(roll, that.roll) &&
                Objects.equals(dice, that.dice) &&
                Objects.equals(side, that.side);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roll, dice, side);
    }

    @Override
    public String toString() {
        return "DiceRequestDTO{" +
                "roll=" + roll +
                ", dice=" + dice +
                ", side=" + side +
                '}';
    }
}
