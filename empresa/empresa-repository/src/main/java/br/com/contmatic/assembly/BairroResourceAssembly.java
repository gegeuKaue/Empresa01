package br.com.contmatic.assembly;

import org.bson.Document;

import br.com.contmatic.endereco.Bairro;

public class BairroResourceAssembly implements Assembly<Bairro, Document> {

	@Override
	public Bairro toResource(Document document) {
		if (document != null) {
			Bairro resource = new Bairro();
			resource.setBairro(document.getString("bairro"));
			return resource;
		}
		return null;
	}

	@Override
	public Document toDocument(Bairro resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

}
