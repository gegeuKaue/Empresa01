package br.com.contmatic.assembly;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.text.StringEscapeUtils;
import org.bson.Document;
import org.junit.Test;

import br.com.contmatic.easy.random.CidadeEasyRandom;
import br.com.contmatic.endereco.Cidade;

public class CidadeResourceAssemblyTest {

	@Test
	public void deve_transformar_uma_classe_cidade_em_document() {
		Cidade cidade = CidadeEasyRandom.cidade();
		Document document = new CidadeResourceAssembly().toDocument(cidade);
		String cidadeUTF8 = StringEscapeUtils.unescapeJava(cidade.toString()).replaceAll("\\s", "");
		assertThat(cidadeUTF8, equalTo(document.toJson().replaceAll("\\s", "")));
	}

	@Test
	public void deve_retornar_nulo_se_um_cidade_for_nulo_ao_transformar_em_documento() {
		Document document = new CidadeResourceAssembly().toDocument(null);
		assertThat(document, equalTo(null));
	}

	@Test
	public void deve_transformar_uma_classe_document_em_cidade() {
		Document document = Document.parse(CidadeEasyRandom.cidade().toString());
		Cidade cidade = new CidadeResourceAssembly().toResource(document);
		String cidadeUTF8 = StringEscapeUtils.unescapeJava(cidade.toString()).replaceAll("\\s", "");
		assertThat(cidadeUTF8, equalTo(document.toJson().replaceAll("\\s", "")));
	}

	@Test
	public void deve_retornar_nulo_se_um_document_for_nulo_ao_transformar_em_cidade() {
		Cidade cidade = new CidadeResourceAssembly().toResource(null);
		assertThat(cidade, equalTo(null));
	}

}
