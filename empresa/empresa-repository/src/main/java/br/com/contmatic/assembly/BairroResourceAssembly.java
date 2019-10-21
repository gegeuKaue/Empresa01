package br.com.contmatic.assembly;

import org.bson.Document;

import br.com.contmatic.endereco.Bairro;

/**
 * The Class BairroResourceAssembly.
 */
public class BairroResourceAssembly implements Assembly<Bairro, Document> {

	/**
	 * To resource.
	 *
	 * @param document the document
	 * @return the bairro
	 */
	@Override
	public Bairro toResource(Document document) {
		if (document != null) {
			Bairro resource = new Bairro();
			resource.setBairro(document.getString("bairro"));
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
	public Document toDocument(Bairro resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

}
