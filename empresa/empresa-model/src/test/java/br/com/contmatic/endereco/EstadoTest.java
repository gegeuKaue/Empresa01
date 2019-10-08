package br.com.contmatic.endereco;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EstadoTest {
	@Test
	public void deve_retornar_um_estado_pela_sigla() {
		Estado estado = Estado.valueOf("SP");
		assertTrue(estado.name().equalsIgnoreCase("sp"));
	}

	@Test
	public void deve_retornar_um_estado_com_nome_por_extenso() {
		Estado estado = Estado.valueOf("SP");
		assertTrue(estado.getNomeEstado().equalsIgnoreCase("São Paulo"));
	}

	@Test
	public void deve_conter_o_valor_nome_no_toString() {
		assertThat(Estado.SP.toString(), containsString("nomeEstado"));
	}

	@Test
	public void deve_conter_o_valor_sigla_no_toString() {
		assertThat(Estado.SP.toString(), containsString("name"));
	}

}
