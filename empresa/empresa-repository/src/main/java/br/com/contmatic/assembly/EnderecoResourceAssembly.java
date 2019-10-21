package br.com.contmatic.assembly;

import org.bson.Document;

import br.com.contmatic.endereco.Cidade;
import br.com.contmatic.endereco.Endereco;

/**
 * The Class EnderecoResourceAssembly.
 */
public class EnderecoResourceAssembly implements Assembly<Endereco, Document> {

	/**
	 * To resource.
	 *
	 * @param document the document
	 * @return the endereco
	 */
	@Override
	public Endereco toResource(Document document) {
		if (document != null) {
			Endereco resource = new Endereco();
			resource.setCep(document.getString("cep"));
			resource.setNumero(document.getInteger("numero"));
			resource.setCidade(toResourceCidade((Document) document.get("cidade")));
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
	public Document toDocument(Endereco resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

	/**
	 * To resource cidade.
	 *
	 * @param document the document
	 * @return the cidade
	 */
	private Cidade toResourceCidade(Document document) {
		if (document == null) {
			return null;
		}
		CidadeResourceAssembly cidadeResource = new CidadeResourceAssembly();
		return cidadeResource.toResource(document);
	}
}
