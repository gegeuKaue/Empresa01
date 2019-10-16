package br.com.contmatic.assembly;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.telefone.Telefone;

public class FuncionarioResourceAssembly implements Assembly<Funcionario, Document> {

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

	@Override
	public Document toDocument(Funcionario resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

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

	private LocalTime localTime(String time) {
		return time == null ? null : LocalTime.parse(time);
	}

	private LocalDate LocalDate(String data) {
		return data == null ? null : LocalDate.parse(data);
	}
}
