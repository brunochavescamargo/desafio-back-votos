package com.southsystem.desafio.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VotosServiceImplTest {

	private final String ANY_STRING = "25081de1-1df2-4767-8207-b6bb29ffde03";

	private final Integer ZERO = 0;

	private final Integer ONE = 1;

	private final Integer TWO = 1;

	UUID id;

	private VotosServiceImpl service;

	@BeforeEach
	public void setUp() {
		service = new VotosServiceImpl();
		id = new UUID(0, 0);
	}

	@Test
	public void contagemVotosTest() {

		// cenários onde existem votos a serem contados

		// dado um resultado esperado
		Optional<?> retornoEsperado = Optional
				.of("Resultado da Votação para a Pauta -> " + ANY_STRING + ":\nSIM: " + ONE + " Não: " + TWO);

		// deve retornar o resuldado esperdo
		Assert.assertEquals(service.contagemVotos(ONE, TWO, ANY_STRING, id), retornoEsperado);

		// cenários onde não existem votos a ser contados

		// deve retornar o um optional vazio
		Assert.assertEquals(service.contagemVotos(ZERO, ZERO, ANY_STRING, id), Optional.empty());
	}
}

