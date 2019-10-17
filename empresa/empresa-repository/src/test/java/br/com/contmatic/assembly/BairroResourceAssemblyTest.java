package br.com.contmatic.assembly;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.bson.Document;
import org.jeasy.random.EasyRandom;
import org.junit.Test;

import br.com.contmatic.endereco.Bairro;

public class BairroResourceAssemblyTest {

	@Test
	public void deve_transformar_uma_classe_bairro_em_document() {
		Bairro bairro = new EasyRandom().nextObject(Bairro.class);
		Document document = new BairroResourceAssembly().toDocument(bairro);
		assertThat(document.toJson().toString().replaceAll("\\s+", ""), equalTo(bairro.toString()));
	}

	@Test
	public void deve_retornar_nulo_ao_tranformar_bairro_nulo_em_documento() {
		Document document = new BairroResourceAssembly().toDocument(null);
		assertThat(document, equalTo(null));
	}

	@Test
	public void deve_transformar_uma_classe_document_em_bairro() {
		Document document = Document.parse(new EasyRandom().nextObject(Bairro.class).toString());
		Bairro bairro = new BairroResourceAssembly().toResource(document);
		assertThat(bairro.toString(), equalTo(document.toJson().toString().replaceAll("\\s+", "")));
	}

	@Test
	public void deve_retornar_nulo_ao_tranformar_ducument_nulo_em_bairro() {
		Bairro bairro = new BairroResourceAssembly().toResource(null);
		assertThat(bairro, equalTo(null));
	}
}
