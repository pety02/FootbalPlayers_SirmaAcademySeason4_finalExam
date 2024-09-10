package com.example.footbalplayers_sirmaacademyseason4_finalexam.controllers;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.RecordDTO;
import com.example.footbalplayers_sirmaacademyseason4_finalexam.services.RecordService;
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
import java.util.List;

import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

@Controller
@Slf4j
@Validated
public class RecordController {
    private final RecordService recordService;

    /**
     * RecordController class constructor with a parameter
     * @param recordService the record service
     */
    @Autowired
    public RecordController(@NonNull RecordService recordService) {

        this.recordService = recordService;
    }

    /**
     * Executes a GET request for all RecordDTO objects from the database
     * @param model the Model object to which the list of RecordDTO object will be attached
     * @return all-records.html view with all records from the database or empty list if there
     * is no records
     */
    @GetMapping("/all-records")
    public String getAllRecords(@NonNull Model model) {
        List<RecordDTO> allRecordDTOs = recordService.loadAll();
        model.addAttribute("allRecordDTOs", allRecordDTOs);

        return "all-records";
    }

    /**
     * Executes a GET request for a single RecordDTO object
     * @param id the wanted record's id
     * @param model the Model object to which the RecordDTO object will be attached
     * @return record.html view with the wanted record if it exists in the database
     */
    @GetMapping("/all-records/{id}")
    public String getRecord(@PathVariable @NonNull Long id,
                            @NonNull Model model) {
        if(id <= 0) {
            return "redirect:/all-records";
        }

        RecordDTO recordDTO = recordService.loadByID(id);
        model.addAttribute("recordDTO", recordDTO);
        return "record";
    }

    /**
     * Executes a GET request that returns a Model object with an empty
     * RecordDTO object in order to be filled and stored in the database
     * @param model the Model object to which an empty RecordDTO object will be attached
     * @return create-record.html view that contains empty RecordDTO object
     */
    @GetMapping("/all-records/create")
    public String getAddRecordForm(@NonNull Model model) {
        model.addAttribute("newRecordDTO", new RecordDTO());
        return "add-record";
    }

    /**
     * Executes a POST request that validates the RecordDTO object and if
     * everything is fine passes it deeper in the application to be stored in
     * the database
     * @param recordDTO the record's data transfer object
     * @param binding the validation binding result object
     * @param model the Model object to which a created in the database RecordDTO
     *              object will be attached
     * @param redirectAttributes the redirect attributes object
     * @return If everything is fine and the record is successfully inserted in the
     * database, the method redirects to /all-records in order to show all records.
     * If there is any problem with the insertion of the record in the database or the
     * RecordDTO object is invalid, the method redirects to /all-records/create.
     */
    @PostMapping("/all-records/create")
    public String addRecord(@Valid RecordDTO recordDTO,
                            @NonNull BindingResult binding,
                            @NonNull Model model,
                            @NonNull RedirectAttributes redirectAttributes) {
        if(binding.hasErrors()) {
            log.error("Error creating new record: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("recordDTO", recordDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + recordDTO, binding);
            return "redirect:/all-records/create";
        }

        try {
            RecordDTO createdDTO = recordService.create(recordDTO);
            model.addAttribute("createdDTO", createdDTO);

            return "redirect:/all-records";
        } catch (IllegalArgumentException ex) {
            log.error("Error creating new record: {}", ex.getMessage());
            redirectAttributes.addFlashAttribute("recordDTO", recordDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + recordDTO, binding);
            return "redirect:/all-records/create";
        }
    }

    /**
     * Executes a GET request that returns a definite RecordDTO object in dependence of
     * its id in record-update.html view that allows the user to change any of its data
     * and updates it.
     * @param id the id of the definite player
     * @param model the Model object to which an updated in the database RecordDTO
     *              object will be attached
     * @return record-update.html view with editable fields for the RecordDTO object
     */
    @GetMapping("/all-records/update/{id}")
    public String getUpdateRecordForm(@PathVariable @NonNull Long id,
                                      @NonNull Model model) {
        if(id <= 0) {
            return "redirect:/all-records";
        }

        RecordDTO recordDTO = recordService.loadByID(id);
        model.addAttribute("recordDTO", recordDTO);
        return "record-update";
    }

    /**
     * Executes a POST request that validates the RecordDTO object and if everything
     * is fine it passes the object deeper in the application in order to update a
     * definite record in the database
     * @param id the id of the record that will be updated
     * @param recordDTO the record's data transfer object
     * @param binding the validation binding result object
     * @param model the Model object to which an updated in the database RecordDTO
     *              object will be attached
     * @param redirectAttributes the redirect attributes object
     * @return If everything is fine and the record is successfully updated in the
     * database, the method redirects to /all-records in order to show all records.
     * If there is any problem with the update of the record in the database or the
     * RecordDTO object is invalid, the method redirects to /all-records/update/{id}.
     */
    @PostMapping("/all-records/update/{id}")
    public String updateRecord(@PathVariable @NonNull Long id,
                               @Valid RecordDTO recordDTO,
                               @NonNull BindingResult binding,
                               @NonNull Model model,
                               @NonNull RedirectAttributes redirectAttributes) {
        if(binding.hasErrors() || id <= 0) {
            log.error("Error updating a record: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("recordDTO", recordDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "recordDTO", binding);
            return "redirect:/all-records/update/{id}";
        }

        try {
            recordService.update(id, recordDTO);
            model.addAttribute("updatedRecordDTO", recordDTO);

            return "redirect:/all-records";
        } catch (RuntimeException ex) {
            log.error("Error updating a record: {}", ex.getMessage());
            redirectAttributes.addFlashAttribute("recordDTO", recordDTO);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "recordDTO", binding);
            return "redirect:/all-records/update/{id}";
        }
    }

    /**
     * Executes a GET request that deletes a definite record from the database in dependence
     * of its id
     * @param id the definite record's id
     * @param bindings the validation binding result object
     * @param redirectAttributes the redirect attributes object
     * @return no matters the request is successful or not, the method redirects
     * to /all-records
     */
    @GetMapping("/all-records/delete/{id}")
    public String deleteRecord(@PathVariable Long id,
                               @NonNull RedirectAttributes redirectAttributes,
                               @NonNull BindingResult bindings) {
        try {
            recordService.deleteById(id);
        } catch (RuntimeException ex) {
            log.error("Error deleting a record: {}", ex.getMessage());
            redirectAttributes.addFlashAttribute("recordID", id);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "recordID", bindings);
        }

        return "redirect:/all-players";
    }
}