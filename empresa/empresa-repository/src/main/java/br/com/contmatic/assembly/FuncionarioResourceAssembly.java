package br.com.contmatic.assembly;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class FuncionarioResourceAssembly.
 */
public class FuncionarioResourceAssembly implements Assembly<Funcionario, Document> {

	/**
	 * To resource.
	 *
	 * @param document the document
	 * @return the funcionario
	 */
	@Override
	public Funcionario toResource(Document document) {
		if (document != null) {
			Funcionario resource = new Funcionario();
			resource.setNome(document.getString("nome"));
			resource.setEmail(document.getString("email"));
			resource.setCpf(document.getString("cpf"));
			resource.setIdade(document.getInteger("idade"));
			resource.setEntrada(localTime(document.getString("entrada")));
			resource.setSaida(localTime(document.getString("saida")));
			resource.setDataContratacao(LocalDate(document.getString("dataContratacao")));
			resource.setCargo(document.getString("cargo"));
			resource.setTelefones(toResourceFuncionarios(document.getList("telefones", Document.class)));
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
	public Document toDocument(Funcionario resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

	/**
	 * To resource funcionarios.
	 *
	 * @param documents the documents
	 * @return the sets the
	 */
	private Set<Telefone> toResourceFuncionarios(List<Document> documents) {
		if (documents == null) {
			return null;
		}
		Set<Telefone> telefones = new HashSet<Telefone>();
		for (Document document : documents) {
			telefones.add(new TelefoneResourceAssembly().toResource(document));
		}
		return telefones;
	}

	/**
	 * Local time.
	 *
	 * @param time the time
	 * @return the local time
	 */
	private LocalTime localTime(String time) {
		return time == null ? null : LocalTime.parse(time);
	}

	/**
	 * Local date.
	 *
	 * @param data the data
	 * @return the local date
	 */
	private LocalDate LocalDate(String data) {
		return data == null ? null : LocalDate.parse(data);
	}
}
