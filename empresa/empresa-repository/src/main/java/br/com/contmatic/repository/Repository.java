package br.com.contmatic.repository;

import static com.mongodb.client.model.Projections.include;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.assembly.EmpresaResourceAssembly;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.util.Conexao;

public class Repository {
	private MongoDatabase database;

	public Repository(MongoDatabase database) {
		this.database = database;
	}

	public void salvar(Empresa empresa) throws IOException {

		Document document = Document.parse(empresa.toString());
		document.remove("cnpj");
		document.append("_id", empresa.getCnpj());
		database.getCollection("empresa").insertOne(document);

	}

	public void alterar(Empresa empresa) {

		Document document = Document.parse(empresa.toString());
		document.remove("cnpj");
		document.append("_id", empresa.getCnpj());

		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("_id", empresa.getCnpj());

		database.getCollection("empresa").updateOne(whereQuery, new Document("$set", document));
	}

	public void deletar(Empresa empresa) throws IOException {

		Document document = new Document("_id", empresa.getCnpj());
		document.remove("cnpj");
		document.append("_id", empresa.getCnpj());
		database.getCollection("empresa").deleteOne(new Document("_id", empresa.getCnpj()));
	}

	public Empresa selecionar(String cnpj) throws IOException {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("_id", cnpj);
		FindIterable<Document> find = database.getCollection("empresa").find(whereQuery);
		return new EmpresaResourceAssembly().toResource(find.first());

	}

	public List<Empresa> selecionar(List<String> campos) throws IOException {
		if (campos.isEmpty()) {
			return null;
		}

		FindIterable<Document> find = database.getCollection("empresa").find().projection(include(campos)).limit(2);
		List<Empresa> empresas = new ArrayList<Empresa>();
		EmpresaResourceAssembly empresaResourceAssembly = new EmpresaResourceAssembly();
		for (Document document : find) {
			empresas.add(empresaResourceAssembly.toResource(document));
		}
		return empresas;
	}

	public static void main(String[] args) throws IOException {

		Conexao conexao = Conexao.getInstance();
		Repository repository = new Repository(conexao.getDatabase());

		Empresa empresas = repository.selecionar("88673767029538");
		System.out.println(empresas);
		conexao.close();
	}
}
