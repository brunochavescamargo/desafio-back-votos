package com.southsystem.desafio.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.southsystem.desafio.enums.StatusVotos;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_VOTOS")
public class VotosModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID votosID;
	
    @ManyToOne
    @JoinColumn(name="sessaoID")
    private SessaoModel sessao;
	

	@Column(nullable = false, length = 20)
	private String cpf;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusVotos statusVotos;

}
