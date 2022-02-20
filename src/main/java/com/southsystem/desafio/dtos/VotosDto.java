package com.southsystem.desafio.dtos;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.southsystem.desafio.enums.StatusVotos;
import com.southsystem.desafio.models.SessaoModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotosDto {	
	
	@Id
	private UUID votosID;
	@JoinColumn(name="sessaoID")
	private SessaoModel sessao;
	private String cpf;
	private StatusVotos statusVotos;
}
