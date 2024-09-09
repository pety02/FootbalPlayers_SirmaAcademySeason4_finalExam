package com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces;

import java.util.List;

public interface Service<Entity, DTO> {
    DTO loadByID(Long id);
    List<DTO> loadAll();
    DTO create(DTO dto) throws IllegalArgumentException;
    void update(Long id, DTO dto) throws RuntimeException;
    void deleteById(Long id) throws RuntimeException;
}