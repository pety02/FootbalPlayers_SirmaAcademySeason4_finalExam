package com.example.footbalplayers_sirmaacademyseason4_finalexam.commandLineRunners.interfaces;

import java.util.List;

public interface Controller<DTO> {
    DTO loadById(Long id);
    List<DTO> loadAll();
    DTO create(DTO dto);
    void update(Long id, DTO dto);
    void delete(Long id);
}