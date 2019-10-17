package br.com.contmatic.assembly;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.text.StringEscapeUtils;
import org.bson.Document;
import org.junit.Test;

import br.com.contmatic.easy.random.EmpresaEasyRandom;
import br.com.contmatic.empresa.Empresa;

public class EmpresaResourceAssemblyTest {
	@Test
	public void deve_transformar_uma_classe_empresa_em_document() {
		Empresa empresa = EmpresaEasyRandom.empresa();
		Document document = new EmpresaResourceAssembly().toDocument(empresa);
		String empresaUTF8 = StringEscapeUtils.unescapeJava(empresa.toString()).replaceAll("\\s", "");
		assertThat(empresaUTF8, equalTo(document.toJson().replaceAll("\\s", "").replaceAll("\\\\\\\\", "\\\\")));
	}

	@Test
	public void deve_retornar_nulo_se_um_empresa_for_nulo_ao_transformar_em_documento() {
		Document document = new EmpresaResourceAssembly().toDocument(null);
		assertThat(document, equalTo(null));
	}

	@Test
	public void deve_transformar_uma_classe_document_em_empresa() {
		Empresa empresaTemp = EmpresaEasyRandom.empresa();
		Document document = Document.parse(empresaTemp.toString()).append("_id", empresaTemp.getCnpj());
		Empresa empresa = new EmpresaResourceAssembly().toResource(document);
		document.remove("_id");
		String empresaUTF8 = StringEscapeUtils.unescapeJava(empresa.toString()).replaceAll("\\s", "");
		assertThat(empresaUTF8, equalTo(document.toJson().replaceAll("\\s", "").replaceAll("\\\\\\\\", "\\\\")));
	}

	@Test
	public void deve_retornar_nulo_se_um_document_for_nulo_ao_transformar_em_empresa() {
		Empresa empresa = new EmpresaResourceAssembly().toResource(null);
		assertThat(empresa, equalTo(null));
	}

}
