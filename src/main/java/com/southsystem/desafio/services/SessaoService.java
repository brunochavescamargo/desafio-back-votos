package com.southsystem.desafio.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.query.Param;

import com.southsystem.desafio.models.SessaoModel;
public interface SessaoService {

    void save(SessaoModel sessaoModel);
    Optional<SessaoModel> findById(UUID sessaoId);
    
    public List<SessaoModel> findTempoSessao(@Param("sessaoId") UUID sessaoId);
    
    public List<?> findDescricaoPauta(@Param("sessaoId") UUID sessaoId);
    
    public List<?> findPautaNaSessao(UUID pautaID);
}
