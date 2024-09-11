package com.example.footbalplayers_sirmaacademyseason4_finalexam.controllers;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.PlayerService;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.TeamService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class PlayerController {
    private final PlayerService playerService;
    private final TeamService teamService;

    /**
     * PlayerController class constructor with parameters
     *
     * @param playerService the player service
     * @param teamService the team service
     */
    @Autowired
    public PlayerController(@NonNull PlayerService playerService,
                            @NonNull TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    /**
     * Executes a GET request for all PlayerDTO objects from the database
     * @param model the Model object to which the list of PlayerDTO object will be attached
     * @return all-players.html view with all players from the database or empty list if there
     * is no players
     */
    @GetMapping("/all-players")
    public String getAllPlayers(@NonNull Model model) {
        List<PlayerDTO> playerDTOs = playerService.loadAll();
        model.addAttribute("playerDTOs", playerDTOs);
        List<TeamDTO> teamDTOs = teamService.loadAll();
        List<String> teamsNames = new ArrayList<>();
        for(PlayerDTO playerDTO : playerDTOs) {
            for (TeamDTO teamDTO : teamDTOs) {
                if (teamDTO.getId().equals(playerDTO.getTeamId())) {
                    teamsNames.add(teamDTO.getName());
                }
            }
        }
        model.addAttribute("teamsNames", teamsNames);
        return "all-players";
    }

    /**
     * Executes a GET request that returns a Model object with an empty
     * PlayerDTO object in order to be filled and stored in the database
     * @param model the Model object to which an empty PlayerDTO object will be attached
     * @return create-player.html view that contains empty PlayerDTO object
     */
    @GetMapping("/all-players/create")
    public String getAddPlayerForm(@NonNull Model model) {
        model.addAttribute("newPlayerDTO", new PlayerDTO());
        List<String> positions = Arrays.asList("GK", "DF", "MF", "FW");
        List<TeamDTO> teamDTOs = teamService.loadAll();
        model.addAttribute("positions", positions);
        model.addAttribute("teamDTOs", teamDTOs);
        return "create-player";
    }

    /**
     * Executes a POST request that validates the PlayerDTO object and if
     * everything is fine passes it deeper in the application to be stored in
     * the database
     * @param playerDTO the player's data transfer object
     * @param binding the validation binding result object
     * @param model the Model object to which a created in the database PlayerDTO
     *              object will be attached
     * @return If everything is fine and the player is successfully inserted in the
     * database, the method redirects to /all-players in order to show all players.
     * If there is any problem with the insertion of the player in the database or the
     * PlayerDTO object is invalid, the method redirects to /all-players/create.
     */
    @PostMapping("/all-players/create")
    public String addPlayer(@Valid @ModelAttribute("newPlayerDTO") PlayerDTO playerDTO,
                            @NonNull BindingResult binding,
                            @NonNull Model model) {
        if(binding.hasErrors()) {
            log.error("Error creating new player: {}", binding.getAllErrors());
            model.addAttribute("newPlayerDTO", playerDTO);
            List<String> positions = Arrays.asList("GK", "DF", "MF", "FW");
            List<TeamDTO> teamDTOs = teamService.loadAll();
            model.addAttribute("positions", positions);
            model.addAttribute("teamDTOs", teamDTOs);
            return "create-player";
        }

        try {
                PlayerDTO insertedPlayerDTO = playerService.create(playerDTO);
                model.addAttribute("insertedPlayerDTO", insertedPlayerDTO);

                return "redirect:/all-players";
        }  catch (Exception ex) {
            log.error("Error creating new player: {}", ex.getMessage());
            model.addAttribute("newPlayerDTO", playerDTO);
            List<String> positions = Arrays.asList("GK", "DF", "MF", "FW");
            List<TeamDTO> teamDTOs = teamService.loadAll();
            model.addAttribute("positions", positions);
            model.addAttribute("teamDTOs", teamDTOs);
            return "create-player";
        }
    }

    /**
     * Executes a GET request that returns a definite PlayerDTO object in dependence of
     * its id in player-update.html view that allows the user to change any of its data
     * and updates it.
     * @param id the id of the definite player
     * @param model the Model object to which an updated in the database PlayerDTO
     *              object will be attached
     * @return player-update.html view with editable fields for the PlayerDTO object
     */
    @GetMapping("/all-players/update/{id}")
    public String getUpdatePlayerForm(@PathVariable("id") @NonNull Long id,
                                      @NonNull Model model) {
        if(id <= 0) {
            return "redirect:/all-players";
        }
        PlayerDTO playerDTO = playerService.loadByID(id);
        model.addAttribute("playerDTO", playerDTO);
        List<String> positions = Arrays.asList("GK", "DF", "MF", "FW");
        List<TeamDTO> teamDTOs = teamService.loadAll();
        model.addAttribute("positions", positions);
        model.addAttribute("teamDTOs", teamDTOs);
        return "player-update";
    }

    /**
     * Executes a POST request that validates the PlayerDTO object and if everything
     * is fine it passes the object deeper in the application in order to update a
     * definite player in the database
     * @param id the id of the player that will be updated
     * @param playerDTO the player's data transfer object
     * @param binding the validation binding result object
     * @param model the Model object to which an updated in the database PlayerDTO
     *              object will be attached
     * @param redirectAttributes the redirect attributes object
     * @return If everything is fine and the player is successfully updated in the
     * database, the method redirects to /all-players in order to show all players.
     * If there is any problem with the update of the player in the database or the
     * PlayerDTO object is invalid, the method redirects to /all-players/update/{id}.
     */
    @PutMapping("/all-players/update/{id}")
    public String updatePlayer(@PathVariable("id") @NonNull Long id,
                               @Valid @ModelAttribute("playerDTO") PlayerDTO playerDTO,
                               @NonNull BindingResult binding,
                               @NonNull Model model,
                               @NonNull RedirectAttributes redirectAttributes) {
        if(binding.hasErrors() || id <= 0) {
            log.error("Error updating a player: {}", binding.getAllErrors());
            model.addAttribute("playerDTO", playerDTO);
            List<String> positions = Arrays.asList("GK", "DF", "MF", "FW");
            List<TeamDTO> teamDTOs = teamService.loadAll();
            model.addAttribute("positions", positions);
            model.addAttribute("teamDTOs", teamDTOs);
            return "player-update";
        }

        try {
            playerService.update(id, playerDTO);
            model.addAttribute("updatedPlayerDTO", playerDTO);

            return "redirect:/all-players";
        } catch (Exception ex) {
            log.error("Error updating a player: {}", ex.getMessage());
            model.addAttribute("playerDTO", playerDTO);
            List<String> positions = Arrays.asList("GK", "DF", "MF", "FW");
            List<TeamDTO> teamDTOs = teamService.loadAll();
            model.addAttribute("positions", positions);
            model.addAttribute("teamDTOs", teamDTOs);
            return "player-update";
        }
    }

    /**
     * Executes a GET request that deletes a definite player from the database in dependence
     * of its id
     * @param id the definite player's id
     * @return no matters the request is successful or not, the method redirects
     * to /all-players
     */
    @GetMapping("/all-players/delete/{id}")
    public String deletePlayer(@PathVariable("id") @NonNull Long id) {

        playerService.deleteById(id);
        return "redirect:/all-players";
    }
}