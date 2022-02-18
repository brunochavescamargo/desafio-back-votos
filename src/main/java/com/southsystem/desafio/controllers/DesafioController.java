package com.southsystem.desafio.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.desafio.dtos.PautaDto;
import com.southsystem.desafio.dtos.SessaoDto;
import com.southsystem.desafio.dtos.VotosDto;
import com.southsystem.desafio.models.PautaModel;
import com.southsystem.desafio.models.SessaoModel;
import com.southsystem.desafio.models.VotosModel;
import com.southsystem.desafio.services.PautaService;
import com.southsystem.desafio.services.SessaoService;
import com.southsystem.desafio.services.VotosService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/desafio")

public class DesafioController {
	
	@Autowired
	PautaService pautaService;
	
	@Autowired
	SessaoService sessaoService;
	
	@Autowired
	VotosService votosService;
    
	@Transactional
    @PostMapping("/pauta")
    public ResponseEntity<Object> salvarPauta(@RequestBody @Valid PautaDto pautaDto){
        log.debug("Método salvarPauta {} ", pautaDto.toString());
        var pautaModel = new PautaModel();
        BeanUtils.copyProperties(pautaDto, pautaModel);
        pautaService.save(pautaModel);
        log.debug("Pauta salva {} ", pautaModel);
        log.info("Pauta salva {} ", pautaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaModel);
    }
    

	@Transactional
    @PostMapping("/aberturaSessao/pauta/{pautaID}")
    public ResponseEntity<Object> abrirSessao(@PathVariable(value="pautaID") UUID pautaID, @RequestBody  SessaoDto sessaoDto){
        log.debug("abrirSessao {} ", sessaoDto.toString());
        
        Optional<PautaModel> pautaModelOptional = pautaService.findById(pautaID);
        if(!pautaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pauta não encontrada.");
        }
		else {

			var sessaoModel = new SessaoModel();
			BeanUtils.copyProperties(sessaoDto, sessaoModel);
			if (sessaoDto.getTemposessao() == null) {
				sessaoModel.setTemposessao(1L);
			} else {
				sessaoModel.setTemposessao(sessaoDto.getTemposessao());
			}
			sessaoModel.setPautaID(pautaID);
			sessaoService.save(sessaoModel);
			log.debug("Abertura Sessão salva {} ", sessaoModel);
			log.info("Abertura Sessão salva {} ",  sessaoModel);
			return ResponseEntity.status(HttpStatus.CREATED).body(sessaoModel);
		}
    }
    
	@Transactional
    @PostMapping("/votos/pauta/{pautaID}/sessao/{sessaoID}")
    public ResponseEntity<Object> salvarVotos(@PathVariable(value="pautaID") UUID pautaID, @PathVariable(value="sessaoID") UUID sessaoID,  @RequestBody  VotosDto votosDto){
        log.debug("Votaçao {} ", votosDto.toString());
        var votosModel = new VotosModel();
        BeanUtils.copyProperties(votosDto, votosModel);
        votosModel.setPautaID(pautaID);
        votosModel.setSessaoID(sessaoID);
        votosService.save(votosModel);
        log.debug("Votos salvos {} ", votosModel);
        log.info("Votos salvos {} ", votosModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(votosModel);
    }

}
