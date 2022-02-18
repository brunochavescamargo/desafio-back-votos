package com.southsystem.desafio.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.desafio.models.SessaoModel;
import com.southsystem.desafio.repositories.SessaoRepository;
import com.southsystem.desafio.services.SessaoService;

@Service
public class SessaoServiceImpl implements SessaoService {

    @Autowired
    SessaoRepository sessaoRepository;


    @Override
    public void save(SessaoModel sessaoModel) {
    	sessaoRepository.save(sessaoModel);
    }

   
}
