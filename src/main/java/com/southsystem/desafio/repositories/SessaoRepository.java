package com.southsystem.desafio.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.southsystem.desafio.models.SessaoModel;

public interface SessaoRepository extends JpaRepository<SessaoModel, UUID> {


}
