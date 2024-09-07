package com.example.footbalplayers_sirmaacademyseason4_finalexam.commandLineRunners;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.commandLineRunners.interfaces.Controller;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.models.Player;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

@Component
@Slf4j
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
        if (args.length == 0 || 9 < args.length) {
            return;
        }

        if (args[0].equals("player")) {
            if (args[1].equals("load")) {
                try {
                    long id = Integer.parseInt(args[2]);
                    PlayerDTO playerDTO = loadById(id);
                    System.out.println(playerDTO);
                } catch (InputMismatchException ex) {
                    log.error(ex.getMessage());
                }
            } else if (args[1].equals("loadAll")) {
                List<PlayerDTO> playerDTOs = loadAll();
                for (PlayerDTO playerDTO : playerDTOs) {
                    System.out.println(playerDTO);
                }
            } else if (args[1].equals("create")) {
                PlayerDTO dto = new PlayerDTO();
                dto.setTeamNumber(Integer.parseInt(args[2]));
                dto.setPosition(args[3]);
                dto.setFullName(args[4]);
                dto.setTeamId(Long.parseLong(args[5]));

                PlayerDTO created = create(dto);
                System.out.println(created);
            } else if (args[1].equals("update")) {
                try {
                    Long id = Long.parseLong(args[2]);

                    PlayerDTO dto = new PlayerDTO();
                    dto.setId(id);
                    dto.setTeamNumber(Integer.parseInt(args[2]));
                    dto.setPosition(args[3]);
                    dto.setFullName(args[4]);
                    dto.setTeamId(Long.parseLong(args[5]));
                    String[] ids = args[6].split(",");
                    List<Long> recordsIds = new ArrayList<>();
                    for (String val : ids) {
                        val = val.replaceAll("\\s", "");
                        recordsIds.add(Long.parseLong(val));
                    }
                    dto.setRecordsIds(recordsIds);

                    update(id, dto);
                } catch (InputMismatchException ex) {
                    log.error(ex.getMessage());
                }
            } else if (args[1].equals("delete")) {
                try {
                    Long id = Long.parseLong(args[2]);
                    delete(id);
                } catch (InputMismatchException ex) {
                    log.error(ex.getMessage());
                }
            }
        }
    }
}