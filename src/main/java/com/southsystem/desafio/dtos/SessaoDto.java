package com.southsystem.desafio.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.southsystem.desafio.models.PautaModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessaoDto {	
	
    private UUID sessaoID;
    @NotBlank
    @Id
    private PautaModel pautaID;
    private LocalDateTime iniciosessao;
    private Long temposessao;
	
}
