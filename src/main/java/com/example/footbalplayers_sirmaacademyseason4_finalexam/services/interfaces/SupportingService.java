package com.example.footbalplayers_sirmaacademyseason4_finalexam.services.interfaces;

import com.example.footbalplayers_sirmaacademyseason4_finalexam.dtos.SupportingTableDTO;

public interface SupportingService {
    <M, N> void create(Class<M> firstCl, Class<N> secondCl, SupportingTableDTO supportingTableDTO) throws RuntimeException;
    <M, N> void update(Class<M> firstCl, Class<N> secondCl, Long id, SupportingTableDTO supportingTableDTO) throws RuntimeException;
    <M, N> void deleteById(Class<M> firstCl, Class<N> secondCl, Long firstId, Long secondId) throws RuntimeException;
}