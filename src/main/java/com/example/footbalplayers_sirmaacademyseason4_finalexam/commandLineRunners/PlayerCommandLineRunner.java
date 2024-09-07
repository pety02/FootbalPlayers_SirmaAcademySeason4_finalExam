package com.example.footbalplayers_sirmaacademyseason4_finalexam.commandLineRunners;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.commandLineRunners.interfaces.Controller;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
        if (args.length == 0 || 5 < args.length) {
            return;
        }

        if (args[0].equals("player")) {
            if (args[1].equals("load")) {
                try {
                    long id = Integer.parseInt(args[2]);
                    PlayerDTO playerDTO = loadById(id);
                    System.out.println(playerDTO);
                } catch (InputMismatchException ex) {
                    return;
                }
            } else if (args[1].equals("loadAll")) {
                List<PlayerDTO> playerDTOs = loadAll();
                for (PlayerDTO playerDTO : playerDTOs) {
                    System.out.println(playerDTO);
                }
            } else if (args[1].equals("update")) {
                try {
                    Long id = Long.parseLong(args[2]);

                    List<String> updateArgs = new ArrayList<>();
                    PlayerDTO dto = new PlayerDTO();
                    dto.setId(id);
                    dto.setTeamNumber(Integer.parseInt(args[2]));
                    dto.setPosition(args[3]);
                    dto.setFullName(args[4]);

                    update(id, dto);
                } catch (InputMismatchException ex) {
                    return;
                }
            } else if (args[1].equals("delete")) {
                try {
                    Long id = Long.parseLong(args[2]);
                    delete(id);
                } catch (InputMismatchException ex) {
                    return;
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }
}