package com.southsystem.desafio.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.southsystem.desafio.models.PautaModel;

import lombok.Data;
@Data
public class SessaoDto {	
	
    private UUID sessaoID;
    private PautaModel pautaID;
    @NotBlank
    private LocalDateTime iniciosessao;
    private Long temposessao;
	
}
