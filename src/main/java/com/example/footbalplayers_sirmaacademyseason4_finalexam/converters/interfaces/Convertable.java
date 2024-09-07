package com.example.footbalplayers_sirmaacademyseason4_finalexam.converters.interfaces;

public interface Convertable<Entity, DTO> {
    Entity toEntity(DTO dto);
    DTO toDTO(Entity entity);
}