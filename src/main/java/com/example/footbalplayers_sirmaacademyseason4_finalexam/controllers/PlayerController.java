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

// TODO: to annotate the class
// TODO: to make correct data validation
// TODO: to implement the class
// TODO: to document the class
@Controller
@Slf4j
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping("/all-players")
    public String getAllPlayers(Model model) {
        List<PlayerDTO> playerDTOs = playerService.loadAll();
        model.addAttribute("playerDTOs", playerDTOs);

        return "players";
    }

    @GetMapping("/all-players/{id}")
    public String getPlayer(Model model,
                            @PathVariable Long id) {
        PlayerDTO playerDTO = playerService.loadByID(id);
        model.addAttribute("playerDTO", playerDTO);

        return "player";
    }

    @GetMapping("/all-players/create")
    public String getAddPlayerForm() {
        return "add-player";
    }

    @PostMapping("/all-players/create")
    public String addPlayer(@Valid @ModelAttribute PlayerDTO playerDTO,
                            BindingResult binding,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if(binding.hasErrors()) {
            log.error("Error creating new task: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("playerDTO", playerDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "playerDTO", binding);
            return "redirect:/players/create";
        }

        PlayerDTO insertedPlayerDTO = playerService.create(playerDTO);
        model.addAttribute("insertedPlayerDTO", insertedPlayerDTO);

        return "redirect:/all-players";
    }

    @GetMapping("/all-players/update/{id}")
    public String getUpdatePlayerForm(@PathVariable Long id,
                                      Model model) {
        PlayerDTO playerDTO = playerService.loadByID(id);
        model.addAttribute("playerDTO", playerDTO);
        return "update-player";
    }

    @PutMapping("/all-players/update/{id}")
    public String updatePlayer(@PathVariable Long id,
                               @Valid @ModelAttribute PlayerDTO playerDTO,
                               BindingResult binding,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if(binding.hasErrors()) {
            log.error("Error creating new task: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("playerDTO", playerDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "playerDTO", binding);
            return "redirect:/players/update/{id}";
        }

        playerService.update(id, playerDTO);
        model.addAttribute("updatedPlayerDTO", playerDTO);

        return "redirect:/all-players";
    }

    @GetMapping("/all-players/delete/{id}")
    public String deletePlayer(@PathVariable Long id) {
        playerService.deleteById(id);
        return "redirect:/all-players";
    }
}