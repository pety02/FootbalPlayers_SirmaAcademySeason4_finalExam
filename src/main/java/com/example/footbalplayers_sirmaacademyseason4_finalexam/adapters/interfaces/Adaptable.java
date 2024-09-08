package com.example.footbalplayers_sirmaacademyseason4_finalexam.adapters.interfaces;

public interface Adaptable<Entity, DTO> {
    Entity toEntity(DTO dto);
    DTO toDTO(Entity entity);
}