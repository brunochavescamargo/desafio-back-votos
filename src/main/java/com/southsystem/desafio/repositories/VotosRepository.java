package com.southsystem.desafio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.southsystem.desafio.models.VotosModel;

public interface VotosRepository extends JpaRepository<VotosModel, UUID> {
	
	boolean existsByCpf(String cpf);
	
    @Query(value = "select count(votosid) from tb_votos where status_votos = 'SIM' and sessaoId = ?", nativeQuery = true)
    public Integer findContagemVotacaoSessaoSim(@Param("sessaoId") UUID sessaoId);
    
    @Query(value = "select count(votosid) from tb_votos where status_votos = 'NÃO' and sessaoId = ?", nativeQuery = true)
    public Integer findContagemVotacaoSessaoNao(@Param("sessaoId") UUID sessaoId);


}