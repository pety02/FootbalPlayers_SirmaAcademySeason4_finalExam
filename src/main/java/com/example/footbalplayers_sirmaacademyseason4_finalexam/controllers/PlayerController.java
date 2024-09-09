package com.example.footbalplayers_sirmaacademyseason4_finalexam.controllers;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.PlayerDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.PlayerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

@Controller
@Slf4j
public class PlayerController {
    private final PlayerService playerService;

    /**
     * PlayerController class constructor with a parameter
     * @param playerService the player service
     */
    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Executes a GET request for all PlayerDTO objects from the database
     * @param model the Model object to which the list of PlayerDTO object will be attached
     * @return all-players.html view with all players from the database or empty list if there
     * is no players
     */
    @GetMapping("/all-players")
    public String getAllPlayers(Model model) {
        List<PlayerDTO> playerDTOs = playerService.loadAll();
        model.addAttribute("playerDTOs", playerDTOs);

        return "all-players";
    }

    /**
     * Executes a GET request for a single PlayerDTO object
     * @param id the wanted player's id
     * @param model the Model object to which the PlayerDTO object will be attached
     * @return player.html view with the wanted player if it exists in the database
     */
    @GetMapping("/all-players/{id}")
    public String getPlayer(@PathVariable Long id,
                            Model model) {
        PlayerDTO playerDTO = playerService.loadByID(id);
        model.addAttribute("playerDTO", playerDTO);

        return "player";
    }

    /**
     * Executes a GET request that returns a Model object with an empty
     * PlayerDTO object in order to be filled and stored in the database
     * @param model the Model object to which an empty PlayerDTO object will be attached
     * @return player.html view that contains empty PlayerDTO object
     */
    @GetMapping("/all-players/create")
    public String getAddPlayerForm(Model model) {
        model.addAttribute("newPlayerDTO", new PlayerDTO());
        return "player";
    }

    /**
     * Executes a POST request that validates the PlayerDTO object and if
     * everything is fine passes it deeper in the application to be stored in
     * the database
     * @param playerDTO the player's data transfer object
     * @param binding the validation binding result object
     * @param model the Model object to which a created in the database PlayerDTO
     *              object will be attached
     * @param redirectAttributes the redirect attributes object
     * @return If everything is fine and the player is successfully inserted in the
     * database, the method redirects to /all-players in order to show all players.
     * If there is any problem with the insertion of the player in the database or the
     * PlayerDTO object is invalid, the method redirects to /all-players/create.
     */
    @PostMapping("/all-players/create")
    public String addPlayer(@Valid @ModelAttribute PlayerDTO playerDTO,
                            BindingResult binding,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if(binding.hasErrors()) {
            log.error("Error creating new player: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("playerDTO", playerDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "playerDTO", binding);
            return "redirect:/all-players/create";
        }

        try {
            PlayerDTO insertedPlayerDTO = playerService.create(playerDTO);
            model.addAttribute("insertedPlayerDTO", insertedPlayerDTO);

            return "redirect:/all-players";
        } catch (IllegalArgumentException ex) {
            log.error("Error creating new player: {}", ex.getMessage());
            redirectAttributes.addFlashAttribute("playerDTO", playerDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "playerDTO", binding);

            return "redirect:/all-players/create";
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
    public String getUpdatePlayerForm(@PathVariable Long id,
                                      Model model) {
        PlayerDTO playerDTO = playerService.loadByID(id);
        model.addAttribute("playerDTO", playerDTO);
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
    public String updatePlayer(@PathVariable Long id,
                               @Valid @ModelAttribute PlayerDTO playerDTO,
                               BindingResult binding,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if(binding.hasErrors()) {
            log.error("Error updating a player: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("playerDTO", playerDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "playerDTO", binding);
            return "redirect:/all-players/update/{id}";
        }

        try {
            playerService.update(id, playerDTO);
            model.addAttribute("updatedPlayerDTO", playerDTO);

            return "redirect:/all-players";
        } catch (RuntimeException ex) {
            log.error("Error updating a player: {}", ex.getMessage());
            redirectAttributes.addFlashAttribute("playerDTO", playerDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "playerDTO", binding);
            return "redirect:/all-players/update/{id}";
        }
    }

    /**
     * Executes a GET request that deletes a definite player from the database in dependence
     * of its id
     * @param id the definite player's id
     * @param binding the validation binding result object
     * @param redirectAttributes the redirect attributes object
     * @return no matters the request is successful or not, the method redirects
     * to /all-players
     */
    @GetMapping("/all-players/delete/{id}")
    public String deletePlayer(@PathVariable Long id,
                               BindingResult binding,
                               RedirectAttributes redirectAttributes) {
        try {
            playerService.deleteById(id);
            return "redirect:/all-players";
        } catch (RuntimeException ex) {
            log.error("Error deleting a player: {}", ex.getMessage());
            redirectAttributes.addFlashAttribute("playerID", id);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "playerID", binding);
            return "redirect:/all-players";
        }
    }
}