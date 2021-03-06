package com.southsystem.desafio.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.desafio.models.PautaModel;
import com.southsystem.desafio.repositories.PautaRepository;
import com.southsystem.desafio.services.PautaService;

@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    PautaRepository pautaRepository;


    @Override
    public void save(PautaModel pautaModel) {
    	pautaRepository.save(pautaModel);
    }


	@Override
	public Optional<PautaModel> findById(UUID pautaId) {
		return pautaRepository.findById(pautaId);
	}

}
