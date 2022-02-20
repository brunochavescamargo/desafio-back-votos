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
    
    @Query(value = "select descricao from tb_pauta a join tb_sessao b on a.pautaid = b.pautaid_pautaid where b.sessaoid = ?", nativeQuery = true)
	public String findIdPauta(@Param("sessaoId") UUID sessaoId);
    
    @Query(value = "select iniciosessao from tb_sessao a where pautaid_pautaid = ? ", nativeQuery = true)
    public List<?> findPautaNaSessao(@Param("pautaid") UUID pautaId);

}