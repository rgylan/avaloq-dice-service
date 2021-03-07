package com.rgy.dice.controller;

import com.rgy.dice.dto.DiceRequestDTO;
import com.rgy.dice.dto.ResponseWrapperDTO;
import com.rgy.dice.service.DiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "/simulation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSimulation() {
        return new ResponseEntity<String>("I'm OK", HttpStatus.OK);
    }
}
