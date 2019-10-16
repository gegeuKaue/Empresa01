package br.com.contmatic.assembly;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.endereco.Endereco;

public class EmpresaResourceAssembly implements Assembly<Empresa, Document> {

	@Override
	public Empresa toResource(Document document) {
		if (document != null) {
			Empresa resource = new Empresa();
			resource.setNome(document.getString("nome"));
			resource.setEmail(document.getString("email"));
			resource.setUrl(document.getString("url"));
			resource.setCnpj(document.getString("_id"));
			resource.setEnderecos(toResourceEnderecos(document.getList("enderecos", Document.class)));
			resource.setFuncionarios(toResourceFuncionarios(document.getList("funcionarios", Document.class)));
			return resource;
		}
		return null;
	}

	@Override
	public Document toDocument(Empresa resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

	private Set<Endereco> toResourceEnderecos(List<Document> documents) {
		if (documents == null) {
			return null;
		}
		Set<Endereco> resources = new HashSet<Endereco>();
		EnderecoResourceAssembly resource = new EnderecoResourceAssembly();
		for (Document document : documents) {
			resources.add(resource.toResource(document));
		}
		return resources;
	}

	private List<Funcionario> toResourceFuncionarios(List<Document> documents) {
		if (documents == null) {
			return null;
		}
		List<Funcionario> resources = new ArrayList<Funcionario>();
		FuncionarioResourceAssembly resource = new FuncionarioResourceAssembly();
		for (Document document : documents) {
			resources.add(resource.toResource(document));
		}
		return resources;
	}
}
