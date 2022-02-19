package com.southsystem.desafio.dtos;

import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;

import com.southsystem.desafio.enums.StatusVotos;
import com.southsystem.desafio.models.SessaoModel;
import com.sun.istack.NotNull;

import lombok.Data;
@Data
public class VotosDto {	
	
	private UUID votosID;
    private SessaoModel sessaoID;
    @CPF
    private String cpf;
    @NotNull
    private StatusVotos statusVotos;
	
}
