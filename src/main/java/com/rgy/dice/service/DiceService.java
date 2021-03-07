package com.rgy.dice.service;

import com.rgy.dice.dto.DiceRequestDTO;

import java.util.Map;

public interface DiceService {

    Map<Integer, Integer> simulate(DiceRequestDTO diceRequestDTO);
}

