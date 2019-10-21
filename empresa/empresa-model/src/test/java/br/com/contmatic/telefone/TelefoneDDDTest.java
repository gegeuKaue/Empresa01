package br.com.contmatic.telefone;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TelefoneDDDTest {
	@Test
	public void deve_retornar_um_TelefoneDDD_pela_sigla() {
		TelefoneDDD ddd = TelefoneDDD.valueOf("DDD11");
		assertTrue(ddd.name().equalsIgnoreCase("ddd11"));
	}

	@Test
	public void deve_retornar_um_TelefoneDDD_com_nome_por_extenso() {
		TelefoneDDD ddd = TelefoneDDD.valueOf("DDD11");
		assertThat(ddd.getRegiao(), is("são paulo | região metropolitana de são paulo."));
	}

	@Test
	public void deve_retornar_um_TelefoneDDD_com_ddd() {
		TelefoneDDD ddd = TelefoneDDD.valueOf("DDD15");
		assertThat(ddd.getDdd(), is(15));
	}

	@Test
	public void deve_conter_o_valor_regiao_no_toString() {
		assertThat(TelefoneDDD.DDD14.toString(), containsString("regiao"));
	}

	@Test
	public void deve_conter_o_valor_name_no_toString() {
		assertThat(TelefoneDDD.DDD14.toString(), containsString("name"));
	}

	@Test
	public void deve_conter_o_valor_do_ddd_no_toString() {
		assertThat(TelefoneDDD.DDD14.toString(), containsString("ddd"));
	}

}
