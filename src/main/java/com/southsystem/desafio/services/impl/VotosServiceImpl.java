package com.southsystem.desafio.services.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.southsystem.desafio.dtos.VotosDto;
import com.southsystem.desafio.models.SessaoModel;
import com.southsystem.desafio.models.VotosModel;
import com.southsystem.desafio.repositories.VotosRepository;
import com.southsystem.desafio.services.VotosService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class VotosServiceImpl implements VotosService {

	@Autowired
	VotosRepository votosRepository;

	@Autowired
	SessaoServiceImpl sessaoServiceImpl;
	
    @Autowired
    RestTemplate restTemplate;

	@Override
	public void save(VotosModel votosModel) {
		votosRepository.save(votosModel);
	}

	@Override
	public boolean existsByCpf(String cpf) {
		return votosRepository.existsByCpf(cpf);
	}

	@Override
	public Integer findContagemVotacaoSessaoSim(UUID sessaoId) {
		return votosRepository.findContagemVotacaoSessaoSim(sessaoId);
	}
	
	@Override
	public Integer findContagemVotacaoSessaoNao(UUID sessaoId) {
		return votosRepository.findContagemVotacaoSessaoNao(sessaoId);
	}

	public Optional<?> salvarVotos(List<SessaoModel> listaTempoSessao, UUID sessaoID, VotosDto votosDto) {
		
		final String baseUrl = "https://user-info.herokuapp.com/users/" + votosDto.getCpf();
		URI uri = null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			log.info(e.getMessage());
		}
		 
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		if (result.getStatusCodeValue() == 404) {
			log.warn("Usuário não possui permissão para votar");
			return Optional.of("Usuário não possui permissão para votar");
		}
		
		if (!listaTempoSessao.isEmpty()) {
			var dataHoraAtual = LocalDateTime.now(ZoneId.of("UTC"));
			var dataHoraInicioSessao = listaTempoSessao.get(0).getIniciosessao();
			var minutosParaEncerrarSessao = listaTempoSessao.get(0).getTemposessao();
			var dataHoraEncerramentoVotacao = dataHoraInicioSessao.plusMinutes(minutosParaEncerrarSessao);
			if (dataHoraAtual.isAfter(dataHoraEncerramentoVotacao)) {
				log.warn("Tempo de votação expirado {} ", dataHoraAtual);
				return Optional.of("Tempo de votação expirado.");
			}
		} else {
			log.warn("Verifique se a sessao está cadastrada corretamente, favor verificar. {} ", votosDto.getCpf());
			return Optional.of("Verifique se a sessão está cadastrada corretamente, favor verificar..");
		}

		Optional<SessaoModel> sessaoModelOptional = sessaoServiceImpl.findById(sessaoID);
		if (!sessaoModelOptional.isPresent()) {
			log.warn("Sessão não encontrada {} ", votosDto.getSessao());
			return Optional.of("Sessão não encontrada.");
		}
		boolean votosModelOptional = this.existsByCpf(votosDto.getCpf());
		if (votosModelOptional) {
			log.warn("CPF já cadastrado para essa votação {} ", votosDto.getCpf());
			return Optional.of("CPF já cadastrado para essa votação.");
		}
		var votosModel = new VotosModel();
		BeanUtils.copyProperties(votosDto, votosModel);
		SessaoModel sessaoModel = new SessaoModel();
		sessaoModel.setSessaoID(sessaoID);
		votosModel.setSessao(sessaoModel);
		this.save(votosModel);
		log.debug("Votos salvos {} ", votosModel);
		log.info("Votos salvos {} ", votosModel);
		return Optional.of(votosModel);
	}
	
	public Optional<?> contagemVotos(Integer contagemVotosSim, Integer contagemVotosNao, String idPauta,
			UUID sessaoID) {
		if (contagemVotosSim == 0 && contagemVotosNao == 0) {

			log.warn("Nenhum voto disponível para essa pauta {} ");
			return Optional.empty();
		} else {
			return Optional.of("Resultado da Votação para a Pauta -> " + idPauta+ ":\nSIM: "
					+ contagemVotosSim + " Não: " + contagemVotosNao);
		}
	}

}