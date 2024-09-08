package com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeamDTO {
    private Long id;
    @NotNull(message = "Name should not be null!")
    @NotBlank(message = "Name should not be blank@")
    @Size(min = 1, max = 150, message = "The length of the mane should be between 1 and 150 letters!")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{1,149}$", message = "Name should starts with a capital and continues with lowercase letters!")
    private String name;
    @NotNull(message = "ManagerFullName should not be null!")
    @NotBlank(message = "ManagerFullName should not be blank!")
    @Size(min = 1, max = 150, message = "ManagerFullName length should be between 1 and 150 letters!")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{1,} [A-Z]{1}[a-z]{1,}$",
            message = "Manager first name and manager surname should start with a capital letter and continue with lowercase letters!")
    private String managerFullName;
    @NotNull(message = "Group should not be null!")
    @NotBlank(message = "Group should not be blank")
    @Size(min = 1, max = 1, message = "Group length should be one letter!")
    @Pattern(regexp = "^([ABCDEF]){1}$", message = "Groups possible values are A,B,C,D,E or F!")
    private String group;
    @NotNull(message = "PlayersIds should not be null!")
    private List<Long> playersIds;
    @NotNull(message = "MatchesIds should not be null!")
    private List<Long> matchesIds;
}