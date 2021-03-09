package com.rgy.dice.service;

import com.rgy.dice.dto.DiceRequestDTO;
import com.rgy.dice.dto.DiceResponseDTO;
import com.rgy.dice.dto.DistributionDTO;

import java.util.List;
import java.util.Map;

public interface DiceService {

    Map<Integer, Integer> simulate(DiceRequestDTO diceRequestDTO);

    List<DiceResponseDTO> findAllSimulations();

    List<DistributionDTO> findAllByDiceAndSide(Integer dice, Integer side);
}

