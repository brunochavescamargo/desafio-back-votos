package com.southsystem.desafio.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.desafio.dtos.PautaDto;
import com.southsystem.desafio.models.PautaModel;
import com.southsystem.desafio.services.PautaService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/desafio")

public class DesafioController {
	
	@Autowired
	PautaService pautaService;
    
    @PostMapping("/pauta")
    public ResponseEntity<Object> salvarPauta(@RequestBody @Valid PautaDto pautaDto){
        log.debug("POST salvar pauta {} ", pautaDto.toString());
        var pautaModel = new PautaModel();
        BeanUtils.copyProperties(pautaDto, pautaModel);
        pautaService.save(pautaModel);
        log.debug("POST salvo PautaID saved {} ", pautaModel.getPautaID());
        log.info("POST salvo PautaID saved {} ", pautaModel.getPautaID());
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaModel);
    }

}
