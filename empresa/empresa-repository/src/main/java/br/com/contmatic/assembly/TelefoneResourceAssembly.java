package br.com.contmatic.assembly;

import org.bson.Document;

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDD;

public class TelefoneResourceAssembly implements Assembly<Telefone, Document> {

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

	@Override
	public Document toDocument(Telefone resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

}
