package com.southsystem.desafio.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.desafio.dtos.SessaoDto;
import com.southsystem.desafio.models.PautaModel;
import com.southsystem.desafio.models.SessaoModel;
import com.southsystem.desafio.repositories.SessaoRepository;
import com.southsystem.desafio.services.SessaoService;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class SessaoServiceImpl implements SessaoService {

    @Autowired
    SessaoRepository sessaoRepository;


    @Override
    public void save(SessaoModel sessaoModel) {
    	sessaoRepository.save(sessaoModel);
    }
    
	@Override
	public Optional<SessaoModel> findById(UUID sessaoId) {
		return sessaoRepository.findById(sessaoId);
	}

	@Override
	public List<SessaoModel> findTempoSessao(UUID sessaoId) {
		return sessaoRepository.findTempoSessao(sessaoId);
	}

	@Override
	public String findDescricaoPauta(UUID sessaoId) {
		return sessaoRepository.findIdPauta(sessaoId);
	}
	
	@Override
	public List<?> findPautaNaSessao(UUID pautaID) {
		return sessaoRepository.findPautaNaSessao(pautaID);
	}
	
	public Optional<SessaoModel> abrirSessao(UUID pautaID, SessaoDto sessaoDto, Optional<PautaModel> pautaModelOptional) {
		List<?> existeSalvoSessaoPauta = this.findPautaNaSessao(pautaID);
		if (existeSalvoSessaoPauta.size() > 0) {
			log.warn("Pauta já cadastrada para essa Sessão {} ", pautaID);
			return Optional.empty();
		}
		
        if(!pautaModelOptional.isPresent()) 
        {
        	log.warn("Pauta não cadastrada");
            return Optional.empty();
        }
		else {
			var sessaoModel = new SessaoModel();
			BeanUtils.copyProperties(sessaoDto, sessaoModel);
			if (sessaoDto.getTemposessao() == null) {
				sessaoModel.setTemposessao(1L);
			} else {
				sessaoModel.setTemposessao(sessaoDto.getTemposessao());
			}
	        PautaModel pautaModel = new PautaModel();
	        pautaModel.setPautaID(pautaID);	        
			sessaoModel.setPautaID(pautaModel);
			this.save(sessaoModel);
			log.debug("Abertura Sessão salva {} ", sessaoModel);
			log.info("Abertura Sessão salva {} ",  sessaoModel);
			return Optional.of(sessaoModel);
		}
	}
   
}