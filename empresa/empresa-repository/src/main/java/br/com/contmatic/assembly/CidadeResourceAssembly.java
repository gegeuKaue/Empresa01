package br.com.contmatic.assembly;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import br.com.contmatic.endereco.Bairro;
import br.com.contmatic.endereco.Cidade;
import br.com.contmatic.endereco.Estado;

/**
 * The Class CidadeResourceAssembly.
 */
public class CidadeResourceAssembly implements Assembly<Cidade, Document> {

	/**
	 * To resource.
	 *
	 * @param document the document
	 * @return the cidade
	 */
	@Override
	public Cidade toResource(Document document) {
		if (document != null) {
			Cidade resource = new Cidade();
			resource.setNome(document.getString("nome"));
			resource.setEstado(Estado.valueOf(((Document) document.get("estado")).getString("name")));
			resource.setBairro(toResourceBairro(document.getList("bairro", Document.class)));
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
	public Document toDocument(Cidade resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

	/**
	 * To resource bairro.
	 *
	 * @param documents the documents
	 * @return the list
	 */
	private List<Bairro> toResourceBairro(List<Document> documents) {
		if (documents == null) {
			return null;
		}
		List<Bairro> bairros = new ArrayList<Bairro>();
		BairroResourceAssembly resourceAssembly = new BairroResourceAssembly();
		for (Document bairro : documents) {
			bairros.add(resourceAssembly.toResource(bairro));
		}
		return bairros;
	}
}
