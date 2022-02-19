package com.southsystem.desafio.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.southsystem.desafio.models.SessaoModel;

public interface SessaoRepository extends JpaRepository<SessaoModel, UUID> {
    @Query(value = "select * from tb_sessao where sessaoId = ?", nativeQuery = true)
	public List<SessaoModel> findTempoSessao(@Param("sessaoId") UUID sessaoId);
}
