package com.southsystem.desafio.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.desafio.dtos.PautaDto;
import com.southsystem.desafio.dtos.SessaoDto;
import com.southsystem.desafio.dtos.VotosDto;
import com.southsystem.desafio.models.PautaModel;
import com.southsystem.desafio.models.SessaoModel;
import com.southsystem.desafio.models.VotosModel;
import com.southsystem.desafio.services.impl.PautaServiceImpl;
import com.southsystem.desafio.services.impl.SessaoServiceImpl;
import com.southsystem.desafio.services.impl.VotosServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/desafio/v1")
@Api(value = "desafio-controller")
public class DesafioController {
	
	@Autowired
	PautaServiceImpl pautaServiceImpl;
	
	@Autowired
	SessaoServiceImpl sessaoServiceImpl;
	
	@Autowired
	VotosServiceImpl votosServiceImpl;
    
	@Transactional
    @PostMapping("/pauta")
	@ApiOperation(value = "Criar Pauta")
    public ResponseEntity<Object> salvarPauta(@RequestBody @Valid PautaDto pautaDto){
        log.debug("Método salvarPauta {} ", pautaDto.toString());
        var pautaModel = new PautaModel();
        BeanUtils.copyProperties(pautaDto, pautaModel);
        pautaServiceImpl.save(pautaModel);
        log.debug("Pauta salva {} ", pautaModel);
        log.info("Pauta salva {} ", pautaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaModel);
    }
    
	@Transactional
    @PostMapping("/aberturaSessao")
	@ApiOperation(value = "Abertura de Sessão")
    public ResponseEntity<?> abrirSessao(@Valid @NotNull @RequestParam(value="pautaID") UUID pautaID, @RequestBody  SessaoDto sessaoDto){
        log.debug("abrirSessao {} ", sessaoDto.toString());        
        Optional<PautaModel> pautaModelOptional = pautaServiceImpl.findById(pautaID);
        Optional<SessaoModel> sessaoModel = sessaoServiceImpl.abrirSessao(pautaID, sessaoDto, pautaModelOptional);
        boolean pautaExiste = sessaoModel.isPresent();
        return ResponseEntity.status(pautaExiste? HttpStatus.CREATED : HttpStatus.NOT_FOUND).
        		body(pautaExiste? sessaoModel.get(): "Verifique se a pauta já está cadastrada para a sessão.");
    }
    
	@Transactional
    @PostMapping("/votos")
	@ApiOperation(value = "Votar")
    public ResponseEntity<?> salvarVotos(@Valid @NotNull @RequestParam(value="sessaoID") UUID sessaoID,  @RequestBody  VotosDto votosDto){
        log.debug("Votaçao {} ", votosDto.toString());  
		List<SessaoModel> listaTempoSessao = sessaoServiceImpl.findTempoSessao(sessaoID);
		Optional<?> salvarVotosOptional = votosServiceImpl.salvarVotos(listaTempoSessao, sessaoID, votosDto);
		return ResponseEntity.status(salvarVotosOptional.get() instanceof VotosModel ? HttpStatus.CREATED :  HttpStatus.NOT_FOUND).
				body(salvarVotosOptional.get());
	}
	
    @GetMapping("/votos")
    @ApiOperation(value = "Contar Votos")
    public ResponseEntity<?> getContagemVotosSessao(@Valid @NotNull @RequestParam(value="sessaoID") UUID sessaoID){
    	log.debug("Contagem Votos"); 
    	Integer contagemVotosSim =  votosServiceImpl.findContagemVotacaoSessaoSim(sessaoID);
    	Integer contagemVotosNao =  votosServiceImpl.findContagemVotacaoSessaoNao(sessaoID);
    	String descricaoPauta   =  sessaoServiceImpl.findDescricaoPauta(sessaoID);

    	Optional<?> contagemVotosOptional = votosServiceImpl.contagemVotos(contagemVotosSim, contagemVotosNao, descricaoPauta, sessaoID);

    	boolean sucesso = contagemVotosOptional.isPresent();

    	return ResponseEntity.status(sucesso ? HttpStatus.CREATED :  HttpStatus.NOT_FOUND).
				body(sucesso ? contagemVotosOptional.get() : "Nenhum voto disponível para essa pauta");
    }
}