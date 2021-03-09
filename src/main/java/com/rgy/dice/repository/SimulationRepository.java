package com.rgy.dice.repository;

import com.rgy.dice.dto.DiceResponseDTO;
import com.rgy.dice.entity.SimulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SimulationRepository extends JpaRepository<SimulationEntity, Long> {

    @Query("select new com.rgy.dice.dto.DiceResponseDTO(count(s.roll), sum(s.roll), " +
            "s.dice, s.side) " +
            "from SimulationEntity s group by s.dice, s.side")
    List<DiceResponseDTO> findAllSimulations();

    @Query("select new com.rgy.dice.dto.DiceResponseDTO(s.roll, s.distribution) from SimulationEntity s " +
            "where s.dice = :dice and s.side = :side")
    List<DiceResponseDTO> findAllByDiceAndSide(
            @Param("dice") Integer dice,
            @Param("side") Integer side);
}
