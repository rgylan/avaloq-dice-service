package com.rgy.dice.controller;

import com.rgy.dice.dto.DiceRequestDTO;
import com.rgy.dice.dto.DiceResponseDTO;
import com.rgy.dice.dto.DistributionDTO;
import com.rgy.dice.dto.ResponseWrapperDTO;
import com.rgy.dice.service.DiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/dice")
public class DiceController {

    @Autowired
    private DiceService diceService;

    @PostMapping(path = "/simulation", produces = MediaType.APPLICATION_JSON_VALUE)
    @SuppressWarnings("unchecked")
    public ResponseWrapperDTO<Map<Integer, Integer>> simulate(DiceRequestDTO diceRequestDTO) {
        return ResponseWrapperDTO.build(diceService.simulate(diceRequestDTO)).toInstance();
    }

    @GetMapping(path = "/simulations", produces = MediaType.APPLICATION_JSON_VALUE)
    @SuppressWarnings("unchecked")
    public ResponseWrapperDTO<List<DiceResponseDTO>> getSimulation() {
        return ResponseWrapperDTO.build(diceService.findAllSimulations()).toInstance();
    }

    @GetMapping(path = "/simulations/{dice}/{side}", produces = MediaType.APPLICATION_JSON_VALUE)
    @SuppressWarnings("unchecked")
    public ResponseWrapperDTO<List<DistributionDTO>> getSimulationByDiceAndSide(@PathVariable Integer dice, @PathVariable Integer side) {
        return ResponseWrapperDTO.build(diceService.findAllByDiceAndSide(dice, side)).toInstance();
    }
}
