package com.southsystem.desafio.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.southsystem.desafio.dtos.SessaoDto;
import com.southsystem.desafio.models.PautaModel;
import com.southsystem.desafio.repositories.SessaoRepository;

public class SessaoServiceImplTest {

	UUID id;

	@Mock
	SessaoRepository sessaoRepository;

	private SessaoServiceImpl service;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new SessaoServiceImpl(sessaoRepository);
		id = new UUID(0, 0);
	}

	@Test
	public void abrirSessaoTest() {
		SessaoDto sessaoDto = new SessaoDto();
		// deve retornar um optional empty
		Assert.assertEquals(service.abrirSessao(id, sessaoDto, Optional.empty()), Optional.empty());

		PautaModel pautaModel = new PautaModel();
		// não deve retornar null
		Assert.assertNotNull(service.abrirSessao(id, sessaoDto, Optional.of(pautaModel)));
	}

}