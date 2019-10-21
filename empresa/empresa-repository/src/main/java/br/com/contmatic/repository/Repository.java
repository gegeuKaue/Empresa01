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

/**
 * The Class Repository.
 */
public class Repository {

	/** The database. */
	private MongoDatabase database;

	/**
	 * Instantiates a new repository.
	 *
	 * @param database the database
	 */
	public Repository(MongoDatabase database) {
		this.database = database;
	}

	/**
	 * Salvar.
	 *
	 * @param empresa the empresa
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void salvar(Empresa empresa) throws IOException {
		Document document = Document.parse(empresa.toString());
		document.remove("cnpj");
		document.append("_id", empresa.getCnpj());
		database.getCollection("empresa").insertOne(document);

	}

	/**
	 * Alterar.
	 *
	 * @param empresa the empresa
	 */
	public void alterar(Empresa empresa) {
		Document document = Document.parse(empresa.toString());
		document.remove("cnpj");
		document.append("_id", empresa.getCnpj());

		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("_id", empresa.getCnpj());

		database.getCollection("empresa").updateOne(whereQuery, new Document("$set", document));
	}

	/**
	 * Deletar.
	 *
	 * @param empresa the empresa
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void deletar(Empresa empresa) throws IOException {
		Document document = new Document("_id", empresa.getCnpj());
		document.remove("cnpj");
		document.append("_id", empresa.getCnpj());
		database.getCollection("empresa").deleteOne(new Document("_id", empresa.getCnpj()));
	}

	/**
	 * Selecionar.
	 *
	 * @param cnpj the cnpj
	 * @return the empresa
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Empresa selecionar(String cnpj) throws IOException {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("_id", cnpj);
		FindIterable<Document> find = database.getCollection("empresa").find(whereQuery);
		return new EmpresaResourceAssembly().toResource(find.first());
	}

	/**
	 * Selecionar.
	 *
	 * @param campos the campos
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<Empresa> selecionar(List<String> campos) throws IOException {
		if (campos == null) {
			return null;
		}
		if (campos.isEmpty()) {
			return null;
		}
		FindIterable<Document> find = database.getCollection("empresa").find().projection(include(campos)).limit(50);
		List<Empresa> empresas = new ArrayList<Empresa>();
		EmpresaResourceAssembly empresaResourceAssembly = new EmpresaResourceAssembly();
		for (Document document : find) {
			empresas.add(empresaResourceAssembly.toResource(document));
		}
		return empresas;
	}

	/**
	 * Deletar.
	 *
	 * @param document the document
	 */
	public void deletar(Document document) {
		database.getCollection("empresa").deleteMany(document);
	}

}
