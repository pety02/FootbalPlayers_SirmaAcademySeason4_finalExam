package com.example.footbalplayers_sirmaacademyseason4_finalexam.controllers;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.TeamDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.TeamService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

@Controller
@Slf4j
@Validated
public class TeamController {
    private final TeamService teamService;

    /**
     * TeamController class constructor with a parameter
     * @param teamService the team service
     */
    @Autowired
    public TeamController(@NonNull TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * Executes a GET request for all TeamDTO objects from the database
     * @param model the Model object to which the list of TeamDTO object will be attached
     * @return all-all-teams.html view with all teams from the database or empty list if there
     * is no teams
     */
    @GetMapping("/all-teams")
    public String getAllTeams(@NonNull Model model) {
        List<TeamDTO> teamDTOs = teamService.loadAll();
        model.addAttribute("teamDTOs", teamDTOs);

        return "all-teams";
    }

    /**
     * Executes a GET request for a single TeamDTO object
     * @param id the wanted team's id
     * @param model the Model object to which the TeamDTO object will be attached
     * @return team.html view with the wanted team if it exists in the database
     */
    @GetMapping("/all-teams/{id}")
    public String getTeam(@PathVariable @NonNull Long id,
                          @NonNull Model model) {
        if(id <= 0) {
            return "redirect:/all-teams";
        }
        TeamDTO teamDTO = teamService.loadByID(id);
        model.addAttribute("teamDTO", teamDTO);

        return "team";
    }

    /**
     * Executes a GET request that returns a Model object with an empty
     * TeamDTO object in order to be filled and stored in the database
     * @param model the Model object to which an empty TeamDTO object will be attached
     * @return create-team.html view that contains empty TeamDTO object
     */
    @GetMapping("/all-teams/create")
    public String getAddTeamForm(@NonNull Model model) {
        model.addAttribute("newTeamDTO", new TeamDTO());
        List<String> groups = Arrays.asList("A", "B", "C", "D", "E", "F");
        model.addAttribute("groups", groups);
        return "create-team";
    }

    /**
     * Executes a POST request that validates the TeamDTO object and if
     * everything is fine passes it deeper in the application to be stored in
     * the database
     * @param teamDTO the team's data transfer object
     * @param binding the validation binding result object
     * @param model the Model object to which a created in the database TeamDTO
     *              object will be attached
     * @param redirectAttributes the redirect attributes object
     * @return If everything is fine and the team is successfully inserted in the
     * database, the method redirects to /all-teams in order to show all teams.
     * If there is any problem with the insertion of the team in the database or the
     * TeamDTO object is invalid, the method redirects to /all-teams/create.
     */
    @PostMapping("/all-teams/create")
    public String addTeam(@Valid TeamDTO teamDTO,
                          @NonNull BindingResult binding,
                          @NonNull Model model,
                          @NonNull RedirectAttributes redirectAttributes) {
        if(binding.hasErrors()) {
            log.error("Error creating new team: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("teamDTO", teamDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "teamDTO", binding);
            return "redirect:/all-teams/create";
        }

        try {
            TeamDTO insertedTeamDTO = teamService.create(teamDTO);
            model.addAttribute("insertedTeamDTO", insertedTeamDTO);

            return "redirect:/all-teams";
        } catch (Exception ex) {
            log.error("Error creating new team: {}", ex.getMessage());
            redirectAttributes.addFlashAttribute("newTeamDTO", teamDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "newTeamDTO", binding);
            return "redirect:/all-teams/create";
        }
    }

    /**
     * Executes a GET request that returns a definite TeamDTO object in dependence of
     * its id in team-update.html view that allows the user to change any of its data
     * and updates it.
     * @param id the id of the definite team
     * @param model the Model object to which an updated in the database TeamDTO
     *              object will be attached
     * @return team-update.html view with editable fields for the TeamDTO object
     */
    @GetMapping("/all-teams/update/{id}")
    public String getUpdateTeamForm(@PathVariable @NonNull Long id,
                                    @NonNull Model model) {
        if(id <= 0) {
            return "redirect:/all-players";
        }
        TeamDTO teamDTO = teamService.loadByID(id);
        model.addAttribute("teamDTO", teamDTO);
        List<String> groups = Arrays.asList("A", "B", "C", "D", "E", "F");
        model.addAttribute("groups", groups);
        model.addAttribute("selectedGroup", teamDTO.getGroup());
        return "team-update";
    }

    /**
     * Executes a POST request that validates the TeamDTO object and if everything
     * is fine it passes the object deeper in the application in order to update a
     * definite team in the database
     * @param id the id of the team that will be updated
     * @param teamDTO the team's data transfer object
     * @param binding the validation binding result object
     * @param model the Model object to which an updated in the database TeamDTO
     *              object will be attached
     * @param redirectAttributes the redirect attributes object
     * @return If everything is fine and the team is successfully updated in the
     * database, the method redirects to /all-teams in order to show all teams.
     * If there is any problem with the update of the team in the database or the
     * TeamDTO object is invalid, the method redirects to /all-teams/update/{id}.
     */
    @PutMapping("/all-teams/update/{id}")
    public String updateTeam(@PathVariable @NonNull Long id,
                             @Valid TeamDTO teamDTO,
                             @NonNull BindingResult binding,
                             @NonNull Model model,
                             @NonNull RedirectAttributes redirectAttributes) {
        if(binding.hasErrors() || id <= 0) {
            log.error("Error updating a team: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("teamDTO", teamDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "teamDTO", binding);
            return "redirect:/all-teams/update/{id}";
        }

        try {
            teamService.update(id, teamDTO);
            model.addAttribute("updatedTeamDTO", teamDTO);

            return "redirect:/all-teams";
        } catch (Exception ex) {
            log.error("Error updating a team: {}", ex.getMessage());
            redirectAttributes.addFlashAttribute("teamDTO", teamDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "teamDTO", binding);
            return "redirect:/all-teams/update/{id}";
        }
    }

    /**
     * Executes a GET request that deletes a definite team from the database in dependence
     * of its id
     * @param id the definite team's id
     * @return no matters the request is successful or not, the method redirects
     * to /all-teams
     */
    @GetMapping("/all-teams/delete/{id}")
    public String deleteTeam(@PathVariable @NonNull Long id) {

        teamService.deleteById(id);
        return "redirect:/all-teams";
    }
}