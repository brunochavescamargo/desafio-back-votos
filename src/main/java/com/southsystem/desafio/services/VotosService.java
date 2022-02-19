package com.southsystem.desafio.services;

import com.southsystem.desafio.models.VotosModel;
public interface VotosService {

    void save(VotosModel votosModel);
    
    boolean existsByCpf(String cpf);

}
