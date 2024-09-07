package com.example.footbalplayers_sirmaacademyseason4_finalexam.services;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.converters.PlayerConverter;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.repositories.PlayerRepository;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class PlayerService implements Service<Player, PlayerDTO> {
    private final PlayerConverter playerConverter;
    private final PlayerRepository playerRepository;

    /**
     *
     * @param playerConverter
     * @param playerRepository
     */
    @Autowired
    public PlayerService(PlayerConverter playerConverter,
                         PlayerRepository playerRepository) {
        this.playerConverter = playerConverter;
        this.playerRepository = playerRepository;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public PlayerDTO loadByID(Long id) {
        return playerConverter.toDTO(playerRepository
                .findById(id)
                .orElse(null));
    }

    /**
     *
     * @return
     */
    @Override
    public List<PlayerDTO> loadAll() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDTO> playersDTOs = new ArrayList<>();
        for (Player player : players) {
            PlayerDTO playerDTO = playerConverter.toDTO(player);
            playersDTOs.add(playerDTO);
        }
        return playersDTOs;
    }

    /**
     *
     * @param playerDTO
     * @return
     */
    @Override
    public PlayerDTO create(PlayerDTO playerDTO) {
        return playerConverter.toDTO(playerRepository.save(playerConverter.toEntity(playerDTO)));
    }

    /**
     *
     * @param id
     * @param playerDTO
     */
    @Override
    public void update(Long id, PlayerDTO playerDTO) {
        if(playerRepository.findById(id).isPresent() && id.equals(playerDTO.getId())) {
            playerRepository.save(playerConverter.toEntity(playerDTO));
        }
    }

    /**
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
}