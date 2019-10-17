package br.com.contmatic.assembly;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.text.StringEscapeUtils;
import org.bson.Document;
import org.junit.Test;

import br.com.contmatic.easy.random.FuncionarioEasyRandom;
import br.com.contmatic.empresa.Funcionario;

public class FuncionarioResourceAssemblyTest {

	@Test
	public void deve_transformar_uma_classe_funcionario_em_document() {
		Funcionario funcionario = FuncionarioEasyRandom.funcionario();
		Document document = new FuncionarioResourceAssembly().toDocument(funcionario);
		String funcionarioUTF8 = StringEscapeUtils.unescapeJava(funcionario.toString()).replaceAll("\\s", "");
		assertThat(funcionarioUTF8, equalTo(document.toJson().replaceAll("\\s", "")));
	}

	@Test
	public void deve_retornar_nulo_se_um_funcionario_for_nulo_ao_transformar_em_documento() {
		Document document = new FuncionarioResourceAssembly().toDocument(null);
		assertThat(document, equalTo(null));
	}

	@Test
	public void deve_transformar_uma_classe_document_em_funcionario() {
		Document document = Document.parse(FuncionarioEasyRandom.funcionario().toString());
		Funcionario funcionario = new FuncionarioResourceAssembly().toResource(document);
		String funcionarioUTF8 = StringEscapeUtils.unescapeJava(funcionario.toString()).replaceAll("\\s", "");
		assertThat(funcionarioUTF8, equalTo(document.toJson().replaceAll("\\s", "")));
	}

	@Test
	public void deve_retornar_nulo_se_um_document_for_nulo_ao_transformar_em_funcionario() {
		Funcionario funcionario = new FuncionarioResourceAssembly().toResource(null);
		assertThat(funcionario, equalTo(null));
	}

}
