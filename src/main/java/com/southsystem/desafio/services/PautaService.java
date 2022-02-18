package com.southsystem.desafio.services;

import java.util.Optional;
import java.util.UUID;

import com.southsystem.desafio.models.PautaModel;
public interface PautaService {

    void save(PautaModel pautaModel);
    
    Optional<PautaModel> findById(UUID pautaId);

}
