package com.bank.itau.controller;

import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bank.itau.controller.dto.TransacaoRequest;
import com.bank.itau.usecase.NotificarBacenUseCase;

@SpringBootTest
@AutoConfigureMockMvc
public class ContaRestControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private ContaRestController controller;

	@MockBean
	private NotificarBacenUseCase notificarBacenUseCase;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testEfetuarTransferencia() throws Exception {
		String requestBody = "{\"agenciaOrigem\": 1, \"contaOrigem\": 1, \"agenciaDestino\": 1, \"contaDestino\": 2, \"valor\": 200.0}";

        TransacaoRequest transacao = TransacaoRequest.builder()
        		.agenciaOrigem(1)
        		.contaOrigem(1)
        		.agenciaDestino(1)
        		.contaDestino(2)
        		.valor(200)
        		.build();

		doNothing().when(notificarBacenUseCase).notificarTransacao(transacao);

		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/contas/transferencia").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

}
