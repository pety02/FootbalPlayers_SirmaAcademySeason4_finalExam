package com.example.footbalplayers_sirmaacademyseason4_finalexam.converters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.converters.interfaces.Convertable;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.TeamRepository;
import org.springframework.stereotype.Component;

@Component
public class PlayerConverter implements Convertable<Player, PlayerDTO> {
    private final TeamRepository teamRepository;

    /**
     *
     * @param teamRepository
     */
    public PlayerConverter(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     *
     * @param playerDTO
     * @return
     */
    @Override
    public Player toEntity(PlayerDTO playerDTO) {
        if(playerDTO == null) {
            return new Player();
        }
        Player player = new Player();
        player.setId(player.getId());
        player.setTeamNumber(playerDTO.getTeamNumber());
        player.setPosition(player.getPosition());
        player.setFullName(player.getFullName());
        Team team = teamRepository.findById(playerDTO.getTeamId()).orElse(null);
        if(team == null) {
            throw new IllegalArgumentException("Every player should play at a team!");
        }
        player.setTeam(team);
        return player;
    }

    /**
     *
     * @param player
     * @return
     */
    @Override
    public PlayerDTO toDTO(Player player) {
        if(player == null) {
            return new PlayerDTO();
        }
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setTeamNumber(playerDTO.getTeamNumber());
        playerDTO.setPosition(player.getPosition());
        playerDTO.setFullName(player.getFullName());
        playerDTO.setTeamId(playerDTO.getTeamId());
        return playerDTO;
    }
}