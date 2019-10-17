package br.com.contmatic.assembly;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.text.StringEscapeUtils;
import org.bson.Document;
import org.junit.Test;

import br.com.contmatic.easy.random.TelefoneEasyRandom;
import br.com.contmatic.telefone.Telefone;

public class TelefoneResourceAssemblyTest {

	@Test
	public void deve_transformar_uma_classe_telefone_em_document() {
		Telefone telefone = TelefoneEasyRandom.telefone();
		Document document = new TelefoneResourceAssembly().toDocument(telefone);
		String telefoneUTF8 = StringEscapeUtils.unescapeJava(telefone.toString()).replaceAll("\\s", "");

		assertThat(telefoneUTF8, equalTo(document.toJson().replaceAll("\\s", "")));
	}

	@Test
	public void deve_retornar_nulo_se_um_telefone_for_nulo_ao_transformar_em_documento() {
		Document document = new TelefoneResourceAssembly().toDocument(null);
		assertThat(document, equalTo(null));
	}

	@Test
	public void deve_transformar_uma_classe_document_em_telefone() {
		Document document = Document.parse(TelefoneEasyRandom.telefone().toString());
		Telefone telefone = new TelefoneResourceAssembly().toResource(document);
		String telefoneUTF8 = StringEscapeUtils.unescapeJava(telefone.toString()).replaceAll("\\s", "");
		assertThat(telefoneUTF8, equalTo(document.toJson().replaceAll("\\s", "")));
	}

	@Test
	public void deve_retornar_nulo_se_um_document_for_nulo_ao_transformar_em_telefone() {
		Telefone telefone = new TelefoneResourceAssembly().toResource(null);
		assertThat(telefone, equalTo(null));
	}
}
