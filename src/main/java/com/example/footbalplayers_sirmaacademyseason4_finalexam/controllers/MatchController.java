package com.example.footbalplayers_sirmaacademyseason4_finalexam.controllers;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.MatchDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.MatchService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

@Controller
@Slf4j
public class MatchController {
    private final MatchService matchService;

    /**
     * MatchController class constructor with a parameter
     * @param matchService the match service
     */
    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    /**
     * Executes a GET request for all MatchDTO objects from the database
     * @param model the Model object to which the list of MatchDTO object will be attached
     * @return all-all-matches.html view with all matches from the database or empty list if there
     * is no matches
     */
    @GetMapping("/all-matches")
    public String getAllMatches(@NonNull Model model) {
        List<MatchDTO> allMatchDTOs = matchService.loadAll();
        model.addAttribute("allMatchDTOs", allMatchDTOs);

        return "all-matches";
    }

    /**
     * Executes a GET request for a single MatchDTO object
     * @param id the wanted match's id
     * @param model the Model object to which the MatchDTO object will be attached
     * @return match.html view with the wanted match if it exists in the database
     */
    @GetMapping("/all-matches/{id}")
    public String getMatch(@PathVariable @NonNull Long id,
                           @NonNull Model model) {
        if(id <= 0) {
            return "redirect:/all-matches";
        }
        MatchDTO matchDTO = matchService.loadByID(id);
        model.addAttribute("matchDTO", matchDTO);

        return "match";
    }

    /**
     * Executes a GET request that returns a Model object with an empty
     * MatchDTO object in order to be filled and stored in the database
     * @param model the Model object to which an empty MatchDTO object will be attached
     * @return create-match.html view that contains empty MatchDTO object
     */
    @GetMapping("/all-matches/create")
    public String getAddMatchForm(@NonNull Model model) {
        model.addAttribute("newMatchDTO", new MatchDTO());
        return "create-match";
    }

    /**
     * Executes a POST request that validates the MatchDTO object and if
     * everything is fine passes it deeper in the application to be stored in
     * the database
     * @param matchDTO the match's data transfer object
     * @param binding the validation binding result object
     * @param model the Model object to which a created in the database MatchDTO
     *              object will be attached
     * @param redirectAttributes the redirect attributes object
     * @return If everything is fine and the match is successfully inserted in the
     * database, the method redirects to /all-matches in order to show all matches.
     * If there is any problem with the insertion of the match in the database or the
     * MatchDTO object is invalid, the method redirects to /all-matches/create.
     */
    @PostMapping("/all-matches/create")
    public String addMatch(@Valid MatchDTO matchDTO,
                           @NonNull BindingResult binding,
                           @NonNull Model model,
                           @NonNull RedirectAttributes redirectAttributes) {
        if(binding.hasErrors()) {
            log.error("Error creating new match: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("matchDTO", matchDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "matchDTO", binding);
            return "redirect:/all-matches/create";
        }

        try {
            MatchDTO insertedMatchDTO = matchService.create(matchDTO);
            model.addAttribute("insertedMatchDTO", insertedMatchDTO);

            return "redirect:/all-matches";
        } catch (IllegalArgumentException ex) {
            log.error("Error creating new match: {}", ex.getMessage());
            redirectAttributes.addFlashAttribute("matchDTO", matchDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "matchDTO", binding);

            return "redirect:/all-matches/create";
        }
    }

    /**
     * Executes a GET request that returns a definite MatchDTO object in dependence of
     * its id in match-update.html view that allows the user to change any of its data
     * and updates it.
     * @param id the id of the definite match
     * @param model the Model object to which an updated in the database MatchDTO
     *              object will be attached
     * @return match-update.html view with editable fields for the MatchDTO object
     */
    @GetMapping("/all-matches/update/{id}")
    public String getUpdateMatchForm(@PathVariable @NonNull Long id,
                                     @NonNull Model model) {
        if(id <= 0) {
            return "redirect:/all-matches";
        }
        MatchDTO matchDTO = matchService.loadByID(id);
        model.addAttribute("matchDTO", matchDTO);
        return "match-update";
    }

    /**
     * Executes a POST request that validates the MatchDTO object and if everything
     * is fine it passes the object deeper in the application in order to update a
     * definite match in the database
     * @param id the id of the match that will be updated
     * @param matchDTO the match's data transfer object
     * @param binding the validation binding result object
     * @param model the Model object to which an updated in the database MatchDTO
     *              object will be attached
     * @param redirectAttributes the redirect attributes object
     * @return If everything is fine and the player is successfully updated in the
     * database, the method redirects to /all-matches in order to show all matches.
     * If there is any problem with the update of the match in the database or the
     * MatchDTO object is invalid, the method redirects to /all-matches/update/{id}.
     */
    @PutMapping("/all-matches/update/{id}")
    public String updateMatch(@PathVariable @NonNull Long id,
                              @Valid MatchDTO matchDTO,
                              @NonNull BindingResult binding,
                              @NonNull Model model,
                              @NonNull RedirectAttributes redirectAttributes) {
        if(binding.hasErrors() || id <= 0) {
            log.error("Error updating a match: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("matchDTO", matchDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "matchDTO", binding);
            return "redirect:/all-matches/update/{id}";
        }

        try {
            matchService.update(id, matchDTO);
            model.addAttribute("updatedMatchDTO", matchDTO);

            return "redirect:/all-matches";
        } catch (RuntimeException ex) {
            log.error("Error updating a match: {}", ex.getMessage());
            redirectAttributes.addFlashAttribute("matchDTO", matchDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "matchDTO", binding);
            return "redirect:/all-matches/update/{id}";
        }
    }

    /**
     * Executes a GET request that deletes a definite match from the database in dependence
     * of its id
     * @param id the definite match's id
     * @param binding the validation binding result object
     * @param redirectAttributes the redirect attributes object
     * @return no matters the request is successful or not, the method redirects
     * to /all-matches
     */
    @GetMapping("/all-matches/delete/{id}")
    public String deleteMatch(@PathVariable @NonNull Long id,
                              @NonNull BindingResult binding,
                              @NonNull RedirectAttributes redirectAttributes) {
        try {
            matchService.deleteById(id);
        } catch (RuntimeException ex) {
            log.error("Error deleting a match: {}", ex.getMessage());
            redirectAttributes.addFlashAttribute("matchID", id);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "matchID", binding);
        }

        return "redirect:/all-matches";
    }
}