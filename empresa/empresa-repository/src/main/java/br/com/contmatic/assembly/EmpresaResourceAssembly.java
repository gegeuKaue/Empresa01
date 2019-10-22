package br.com.contmatic.assembly;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class EmpresaResourceAssembly.
 */
public class EmpresaResourceAssembly implements Assembly<Empresa, Document> {

	/**
	 * To resource.
	 *
	 * @param document the document
	 * @return the empresa
	 */
	@Override
	public Empresa toResource(Document document) {
		if (document != null) {
			Empresa resource = new Empresa();
			resource.setCnpj(document.getString("_id"));
			resource.setNome(document.getString("nome"));
			resource.setEmail(document.getString("email"));
			resource.setUrl(document.getString("url"));
			resource.setFuncionarios(toResourceFuncionarios(document.getList("funcionarios", Document.class)));
			resource.setEnderecos(toResourceEnderecos(document.getList("enderecos", Document.class)));
			resource.setTelefones(toResourceTelefones(document.getList("telefones", Document.class)));
			return resource;
		}
		return null;
	}

	/**
	 * To document.
	 *
	 * @param resource the resource
	 * @return the document
	 */
	@Override
	public Document toDocument(Empresa resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

	/**
	 * To resource enderecos.
	 *
	 * @param documents the documents
	 * @return the sets the
	 */
	private Set<Endereco> toResourceEnderecos(List<Document> documents) {
		Set<Endereco> resources = null;
		if (documents == null) {
			return resources;
		}
		resources = new HashSet<Endereco>();
		EnderecoResourceAssembly resource = new EnderecoResourceAssembly();
		for (Document document : documents) {
			resources.add(resource.toResource(document));
		}
		return resources;
	}

	/**
	 * To resource funcionarios.
	 *
	 * @param documents the documents
	 * @return the list
	 */
	private List<Funcionario> toResourceFuncionarios(List<Document> documents) {
		List<Funcionario> resources = null;
		if (documents == null) {
			return resources;
		}
		resources = new ArrayList<Funcionario>();
		FuncionarioResourceAssembly resource = new FuncionarioResourceAssembly();
		for (Document document : documents) {
			resources.add(resource.toResource(document));
		}
		return resources;
	}

	/**
	 * To resource telefones.
	 *
	 * @param documents the documents
	 * @return the sets the
	 */
	private Set<Telefone> toResourceTelefones(List<Document> documents) {
		Set<Telefone> telefones = null;
		if (documents == null) {
			return telefones;
		}
		TelefoneResourceAssembly assembly = new TelefoneResourceAssembly();
		telefones = new HashSet<Telefone>();
		for (Document document : documents) {
			telefones.add(assembly.toResource(document));
		}

		return telefones;
	}

}
