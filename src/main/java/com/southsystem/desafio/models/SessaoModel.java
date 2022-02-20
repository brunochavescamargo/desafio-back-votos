package com.southsystem.desafio.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_SESSAO")
public class SessaoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID sessaoID;
    
    @OneToOne
    @JsonIgnore
    private PautaModel pautaID;
    
    @OneToMany
    @JoinColumn(name="sessaoID")
    @JsonIgnore
    private List<VotosModel> votos ;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime iniciosessao;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long temposessao;
}
