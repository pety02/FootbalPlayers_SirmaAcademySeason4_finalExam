package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.SupportingTableDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Record;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.SupportingTableRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.SupportingTableRepositoryImpl;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.SupportingService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SupportingTableService implements SupportingService {
    private final SupportingTableRepositoryImpl supportingTableRepository;

    @Autowired
    public SupportingTableService(@NonNull SupportingTableRepositoryImpl supportingTableRepository) {
        this.supportingTableRepository = supportingTableRepository;
    }

    /**
     * Inserts new records in some of the supporting tables: players_records, teams_matches or teams_players.
     * The method decides which helper methods to execute as checking of firstCl and secondCl arguments values.
     * If the firstCl or secondCl has not supported values the methods throws an exception.
     * @param firstCl the first class
     * @param secondCl the second class
     * @param supportingTableDTO a SupportingTableDTO object that will be given as an argument of the helper
     *                           methods execution when its needed
     * @param <M> a template parameter that defines the value of the firstCl
     * @param <N> a template parameter that defines the value of the secondCl
     * @throws RuntimeException this exception is thrown when any of the template argument defines not
     * supported type for firstCl or secondCl parameters. The supported types are: Player.class, Record.class,
     * Team.class or Match.class.
     */
    @Override
    public <M, N> void create(Class<M> firstCl, Class<N> secondCl,
                                            SupportingTableDTO supportingTableDTO) throws RuntimeException {
        if((firstCl == Player.class && secondCl == Record.class)
                || (firstCl == Record.class && secondCl == Player.class)) {
            supportingTableRepository.addPlayerRecord(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if ((firstCl == Team.class && secondCl == Match.class)
                || (firstCl == Match.class && secondCl == Team.class)) {
            supportingTableRepository.addTeamMatch(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if ((firstCl == Team.class && secondCl == Player.class)
                || (firstCl == Player.class && secondCl == Team.class)) {
            supportingTableRepository.addTeamPlayer(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else {
            throw new RuntimeException("Not supported class types!");
        }
    }

    /**
     * Updates a definite record in some of the supporting tables: players_records, teams_matches or teams_players.
     * The method decides which helper methods to execute as checking of firstCl and secondCl arguments values.
     * If the firstCl or secondCl has not supported values the methods throws an exception.
     * @param firstCl the first class
     * @param secondCl the second class
     * @param id the id for the updated record
     * @param supportingTableDTO a SupportingTableDTO object that will be given as an argument of the helper
     *                           methods execution when its needed
     * @param <M> a template parameter that defines the value of the firstCl
     * @param <N> a template parameter that defines the value of the secondCl
     * @throws RuntimeException this exception is thrown when any of the template argument defines not
     * supported type for firstCl or secondCl parameters. The supported types are: Player.class, Record.class,
     * Team.class or Match.class.
     */
    @Override
    public <M, N> void update(Class<M> firstCl, Class<N> secondCl, Long id,
                              SupportingTableDTO supportingTableDTO) throws RuntimeException {
        if(firstCl == Player.class && secondCl == Record.class) {
            supportingTableRepository.updatePlayerRecord(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if (firstCl == Record.class && secondCl == Player.class) {
            supportingTableRepository.updateRecordPlayer(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if (firstCl == Team.class && secondCl == Match.class) {
            supportingTableRepository.updateTeamMatch(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if (firstCl == Match.class && secondCl == Team.class) {
            supportingTableRepository.updateMatchTeam(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if (firstCl == Team.class && secondCl == Player.class) {
            supportingTableRepository.updateTeamPlayer(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else if (firstCl == Player.class && secondCl == Team.class) {
            supportingTableRepository.updatePlayerTeam(supportingTableDTO.getFirstId(), supportingTableDTO.getSecondId());
        } else {
            throw new RuntimeException("Not supported class types!");
        }
    }

    /**
     * Deletes from the supporting tables: players_records, teams_matches or teams_players.
     * The method decides which helper methods to execute as checking of firstCl and secondCl arguments values.
     * If the firstCl or secondCl has not supported values the methods throws an exception.
     * @param firstCl the first class
     * @param secondCl the second class
     * @param firstId the first id, passed as an argument to the helpers methods
     * @param secondId the second id, passed as an argument to the helpers methods
     * @param <M> a template parameter that defines the value of the firstCl
     * @param <N> a template parameter that defines the value of the secondCl
     * @throws RuntimeException this exception is thrown when any of the template argument defines not
     * supported type for firstCl or secondCl parameters. The supported types are: Player.class, Record.class,
     * Team.class or Match.class.
     */
    @Override
    public <M, N> void deleteById(Class<M> firstCl, Class<N> secondCl, Long firstId, Long secondId) throws RuntimeException {
        if((firstCl == Player.class && secondCl == Record.class)
                || (firstCl == Record.class && secondCl == Player.class)) {
            supportingTableRepository.deletePlayerRecord(firstId, secondId);
        } else if ((firstCl == Team.class && secondCl == Match.class)
                || (firstCl == Match.class && secondCl == Team.class)) {
            supportingTableRepository.deleteTeamMatch(firstId, secondId);
        } else if ((firstCl == Team.class && secondCl == Player.class)
                || (firstCl == Player.class && secondCl == Team.class)) {
            supportingTableRepository.deleteTeamPlayer(firstId, secondId);
        } else {
            throw new RuntimeException("Not supported class types!");
        }
    }
}