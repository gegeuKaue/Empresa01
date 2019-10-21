package br.com.contmatic.assembly;

import org.bson.Document;

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDD;

/**
 * The Class TelefoneResourceAssembly.
 */
public class TelefoneResourceAssembly implements Assembly<Telefone, Document> {

	/**
	 * To resource.
	 *
	 * @param document the document
	 * @return the telefone
	 */
	@Override
	public Telefone toResource(Document document) {
		if (document != null) {
			Telefone resource = new Telefone();
			resource.setNumero(document.getString("numero"));
			resource.setDdd(TelefoneDDD.valueOf(((Document) document.get("ddd")).getString("name")));
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
	public Document toDocument(Telefone resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

}
