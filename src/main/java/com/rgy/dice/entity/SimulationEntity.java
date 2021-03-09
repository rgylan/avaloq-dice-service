package com.rgy.dice.entity;

import com.rgy.dice.util.MapConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(schema = "dice", name = "simulation_txn")
public class SimulationEntity extends BaseEntity implements Serializable {

    @Column(name = "roll")
    private Long roll;

    @Column(name = "num_of_dice")
    private Integer dice;

    @Column(name = "side")
    private Integer side;

    @Convert(converter = MapConverter.class)
    @Column(name = "distribution", columnDefinition = "json")
    private Map<Integer, Integer> distribution;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoll() {
        return roll;
    }

    public void setRoll(Long roll) {
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

    public Map<Integer, Integer> getDistribution() {
        return distribution;
    }

    public void setDistribution(Map<Integer, Integer> distribution) {
        this.distribution = distribution;
    }
}
