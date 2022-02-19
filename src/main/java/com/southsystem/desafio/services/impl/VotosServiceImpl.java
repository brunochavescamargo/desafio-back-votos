package com.southsystem.desafio.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.desafio.models.VotosModel;
import com.southsystem.desafio.repositories.SessaoRepository;
import com.southsystem.desafio.repositories.VotosRepository;
import com.southsystem.desafio.services.VotosService;

@Service
public class VotosServiceImpl implements VotosService {

    @Autowired
    VotosRepository votosRepository;
    
    @Autowired
    SessaoRepository sessaoRepository;


    @Override
    public void save(VotosModel votosModel) {
    	votosRepository.save(votosModel);
    }


    @Override
    public boolean existsByCpf(String cpf) {
        return votosRepository.existsByCpf(cpf);
    }

}
