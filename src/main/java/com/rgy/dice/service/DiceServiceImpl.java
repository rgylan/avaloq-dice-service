package com.rgy.dice.service;

import com.rgy.dice.dto.DiceRequestDTO;
import com.rgy.dice.exception.DiceRollException;
import com.rgy.dice.exception.DiceSideException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DiceServiceImpl implements DiceService {

    private final static Integer MIN = 1;

    @Override
    public Map<Integer, Integer> simulate(DiceRequestDTO diceRequestDTO) {
        validateInput(diceRequestDTO);
        return startSimulate(diceRequestDTO);
    }

    private void validateInput(DiceRequestDTO diceRequestDTO) {
        if (diceRequestDTO.getDice() < 1 || diceRequestDTO.getRoll() < 1) {
            throw new DiceRollException();
        }
        if(diceRequestDTO.getSide() < 4){
            throw new DiceSideException();
        }
    }

    private Map<Integer, Integer> startSimulate(DiceRequestDTO diceRequestDTO) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < diceRequestDTO.getRoll(); i++) {
            int sum = 0;
            for (int j = 0; j < diceRequestDTO.getDice(); j++) {
                sum = sum + ThreadLocalRandom.current().nextInt(MIN, diceRequestDTO.getSide() + MIN);
            }
            map.compute(sum, (key, value) -> value == null ? 1 : value + 1);
        }

        return map;
    }
}
