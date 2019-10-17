package br.com.contmatic.assembly;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.text.StringEscapeUtils;
import org.bson.Document;
import org.junit.Test;

import br.com.contmatic.easy.random.EnderecoEasyRandom;
import br.com.contmatic.endereco.Endereco;

public class EnderecoResourceAssemblyTest {

	@Test
	public void deve_transformar_uma_classe_endereco_em_document() {
		Endereco endereco = EnderecoEasyRandom.endereco();
		Document document = new EnderecoResourceAssembly().toDocument(endereco);
		String enderecoUTF8 = StringEscapeUtils.unescapeJava(endereco.toString()).replaceAll("\\s", "");
		assertThat(enderecoUTF8, equalTo(document.toJson().replaceAll("\\s", "")));
	}

	@Test
	public void deve_retornar_nulo_se_um_endereco_for_nulo_ao_transformar_em_documento() {
		Document document = new EnderecoResourceAssembly().toDocument(null);
		assertThat(document, equalTo(null));
	}

	@Test
	public void deve_transformar_uma_classe_document_em_endereco() {
		Document document = Document.parse(EnderecoEasyRandom.endereco().toString());
		Endereco endereco = new EnderecoResourceAssembly().toResource(document);
		String enderecoUTF8 = StringEscapeUtils.unescapeJava(endereco.toString()).replaceAll("\\s", "");
		assertThat(enderecoUTF8, equalTo(document.toJson().replaceAll("\\s", "")));
	}

	@Test
	public void deve_retornar_nulo_se_um_document_for_nulo_ao_transformar_em_endereco() {
		Endereco endereco = new EnderecoResourceAssembly().toResource(null);
		assertThat(endereco, equalTo(null));
	}

}
