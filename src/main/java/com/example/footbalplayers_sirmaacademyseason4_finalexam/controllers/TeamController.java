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
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
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
     * Executes a GET request that returns a Model object with an empty
     * TeamDTO object in order to be filled and stored in the database
     * @param model the Model object to which an empty TeamDTO object will be attached
     * @return create-team.html view that contains empty TeamDTO object
     */
    @GetMapping("/all-teams/create")
    public String getAddTeamForm(Model model) {

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
     * @return If everything is fine and the team is successfully inserted in the
     * database, the method redirects to /all-teams in order to show all teams.
     * If there is any problem with the insertion of the team in the database or the
     * TeamDTO object is invalid, the method redirects to /all-teams/create.
     */
    @PostMapping("/all-teams/create")
    public String addTeam(@Valid @ModelAttribute("newTeamDTO") TeamDTO teamDTO,
                          @NonNull BindingResult binding,
                          @NonNull Model model) {
        if(binding.hasErrors()) {
            log.error("Error creating new team: {}", binding.getAllErrors());
            model.addAttribute("newTeamDTO", teamDTO);
            List<String> groups = Arrays.asList("A", "B", "C", "D", "E", "F");
            model.addAttribute("groups", groups);
            return "create-team";
        }
        try {
            TeamDTO insertedTeamDTO = teamService.create(teamDTO);
            model.addAttribute("insertedTeamDTO", insertedTeamDTO);

            return "redirect:/all-teams";
        } catch (Exception ex) {
            log.error("Error creating new team: {}", ex.getMessage());
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
    public String getUpdateTeamForm(@PathVariable("id") @NonNull Long id,
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
     * @return If everything is fine and the team is successfully updated in the
     * database, the method redirects to /all-teams in order to show all teams.
     * If there is any problem with the update of the team in the database or the
     * TeamDTO object is invalid, the method redirects to /all-teams/update/{id}.
     */
    @PutMapping("/all-teams/update/{id}")
    public String updateTeam(@PathVariable("id") @NonNull Long id,
                             @Valid @ModelAttribute("teamDTO") TeamDTO teamDTO,
                             @NonNull BindingResult binding,
                             @NonNull Model model) {
        if(binding.hasErrors() || id <= 0) {
            log.error("Error updating a team: {}", binding.getAllErrors());
            model.addAttribute("teamDTO", teamDTO);
            List<String> groups = Arrays.asList("A", "B", "C", "D", "E", "F");
            model.addAttribute("groups", groups);
            model.addAttribute("selectedGroup", teamDTO.getGroup());
            return "team-update";
        }

        try {
            teamService.update(id, teamDTO);
            model.addAttribute("updatedTeamDTO", teamDTO);

            return "redirect:/all-teams";
        } catch (Exception ex) {
            log.error("Error updating a team: {}", ex.getMessage());
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
    public String deleteTeam(@PathVariable("id") @NonNull Long id) {
        teamService.deleteById(id);
        return "redirect:/all-teams";
    }
}