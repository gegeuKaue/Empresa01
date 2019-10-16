package br.com.contmatic.assembly;

import org.bson.Document;

import br.com.contmatic.endereco.Cidade;
import br.com.contmatic.endereco.Estado;

public class CidadeResourceAssembly implements Assembly<Cidade, Document> {

	@Override
	public Cidade toResource(Document document) {
		if (document != null) {
			Cidade resource = new Cidade();
			resource.setNome(document.getString("nome"));
			resource.setEstado(Estado.valueOf(((Document) document.get("estado")).getString("name")));
		}
		return null;
	}

	@Override
	public Document toDocument(Cidade resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

}
