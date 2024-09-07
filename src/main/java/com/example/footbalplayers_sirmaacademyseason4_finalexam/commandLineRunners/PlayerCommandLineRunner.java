package com.example.footbalplayers_sirmaacademyseason4_finalexam.commandLineRunners;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.commandLineRunners.interfaces.Controller;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerCommandLineRunner implements Controller<PlayerDTO>, CommandLineRunner {
    private final PlayerService playerService;

    /**
     *
     * @param playerService
     */
    @Autowired
    public PlayerCommandLineRunner(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public PlayerDTO loadById(Long id) {
        return playerService.loadByID(id);
    }

    /**
     *
     * @return
     */
    @Override
    public List<PlayerDTO> loadAll() {
        return playerService.loadAll();
    }

    /**
     *
     * @param dto
     * @return
     */
    @Override
    public PlayerDTO create(PlayerDTO dto) {
        return playerService.create(dto);
    }

    /**
     *
     * @param id
     * @param dto
     */
    @Override
    public void update(Long id, PlayerDTO dto) {
        playerService.update(id, dto);
    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        playerService.deleteById(id);
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

    }
}