package com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.interfaces.Adaptable;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Match;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Team;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.MatchRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamAdapter implements Adaptable<Team, TeamDTO> {
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    /**
     * TeamConverter class constructor with parameters
     * @param playerRepository the player repository
     * @param matchRepository the math repository
     */
    @Autowired
    public TeamAdapter(PlayerRepository playerRepository,
                       MatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    /**
     * Converts a TeamDTO object to a Team object
     * @param teamDTO the TeamDTO object
     * @return a Team object
     */
    @Override
    public Team toEntity(TeamDTO teamDTO) {
        if(teamDTO == null) {
            return null;
        }
        Team team = new Team();
        team.setId(teamDTO.getId());
        team.setName(teamDTO.getName());
        team.setManagerFullName(teamDTO.getManagerFullName());
        team.setTeamGroup(teamDTO.getGroup());
        List<Long> playersIds = teamDTO.getPlayersIds();
        List<Player> players = new ArrayList<>();
        if(playersIds != null) {
            for (Long playerId : playersIds) {
                players.add(playerRepository.findById(playerId).orElse(null));
            }
        }
        team.setPlayers(players);
        List<Long> matchesIds = teamDTO.getMatchesIds();
        List<Match> matches = new ArrayList<>();
        if(matchesIds != null) {
            for (Long matchId : matchesIds) {
                matches.add(matchRepository.findById(matchId).orElse(null));
            }
        }
        team.setMatches(matches);
        return team;
    }

    /**
     * Converts a Team object to a TeamDTO object
     * @param team the Team object
     * @return a TeamDTO object
     */
    @Override
    public TeamDTO toDTO(Team team) {
        if(team == null) {
            return null;
        }
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setManagerFullName(team.getManagerFullName());
        teamDTO.setGroup(team.getTeamGroup());
        List<Player> players = team.getPlayers();
        List<Long> playersIds = new ArrayList<>();
        for(Player player : players) {
            playersIds.add(player.getId());
        }
        teamDTO.setPlayersIds(playersIds);
        List<Match> matches = team.getMatches();
        List<Long> matchesIds = new ArrayList<>();
        for(Match match : matches) {
            matchesIds.add(match.getId());
        }
        teamDTO.setMatchesIds(matchesIds);
        return teamDTO;
    }
}