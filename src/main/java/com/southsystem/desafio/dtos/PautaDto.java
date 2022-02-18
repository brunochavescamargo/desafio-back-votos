package com.southsystem.desafio.dtos;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class PautaDto {	
	
    private UUID pautaID;
	@NotBlank
    private String descricao;

}
