package com.rgy.dice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgy.dice.dto.DiceRequestDTO;
import com.rgy.dice.dto.DiceResponseDTO;
import com.rgy.dice.dto.DistributionDTO;
import com.rgy.dice.entity.SimulationEntity;
import com.rgy.dice.exception.DiceRollException;
import com.rgy.dice.exception.DiceSideException;
import com.rgy.dice.exception.MissingParamException;
import com.rgy.dice.repository.SimulationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class DiceServiceImpl implements DiceService {

    private final static Integer MIN = 1;

    private final ModelMapper modelMapper;
    private final SimulationRepository repository;
    private final ObjectMapper objectMapper;

    @Autowired
    public DiceServiceImpl(ModelMapper modelMapper, SimulationRepository repository, ObjectMapper objectMapper) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Map<Integer, Integer> simulate(DiceRequestDTO diceRequestDTO) {
        validateInput(diceRequestDTO);
        Map<Integer, Integer> map = startSimulate(diceRequestDTO);
        repository.save(mapToEntity(map, diceRequestDTO));

        return map;
    }

    @Override
    public List<DiceResponseDTO> findAllSimulations() {
        return repository.findAllSimulations();
    }

    @Override
    public List<DistributionDTO> findAllByDiceAndSide(Integer dice, Integer side) {
        Map<Integer, Integer> result = new HashMap<>();
        List<DiceResponseDTO> staging = repository.findAllByDiceAndSide(dice, side);
        staging.forEach(dto -> {
            dto.getDistribution().forEach((key, value) -> result.merge(key, value, (v1, v2) -> v1 + v2));
        });

        double rolls = staging.stream().map(i -> i.getRoll()).reduce(0l, Long::sum);

        return result.entrySet().stream().map(entry ->
                new DistributionDTO(entry.getKey(), toNumberFormat((entry.getValue() / rolls)))).collect(Collectors.toList());
    }

    private void validateInput(DiceRequestDTO diceRequestDTO) {
        Optional.ofNullable(diceRequestDTO).orElseThrow(MissingParamException::new);

        if (diceRequestDTO.getRoll() == null || diceRequestDTO.getDice() == null || diceRequestDTO.getSide() == null) {
            throw new MissingParamException();
        }

        if (diceRequestDTO.getDice() < 1 || diceRequestDTO.getRoll() < 1) {
            throw new DiceRollException();
        }
        if (diceRequestDTO.getSide() < 4) {
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

    private SimulationEntity mapToEntity(Map<Integer, Integer> map, DiceRequestDTO diceRequestDTO) {
        SimulationEntity entity = modelMapper.map(diceRequestDTO, SimulationEntity.class);
        entity.setCreated(LocalDateTime.now());
        entity.setUpdated(LocalDateTime.now());
        entity.setDistribution(map);

        return entity;
    }

    private String toNumberFormat(Double d) {
        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(2);
        return defaultFormat.format(d);
    }
}
