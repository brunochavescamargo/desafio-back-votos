package com.southsystem.desafio.services;

import java.util.UUID;

import org.springframework.data.repository.query.Param;

import com.southsystem.desafio.models.VotosModel;
public interface VotosService {

    void save(VotosModel votosModel);
    
    boolean existsByCpf(String cpf);
    
    public Integer findContagemVotacaoSessaoSim(@Param("sessaoId") UUID sessaoId);
    public Integer findContagemVotacaoSessaoNao(@Param("sessaoId") UUID sessaoId);
    
}
