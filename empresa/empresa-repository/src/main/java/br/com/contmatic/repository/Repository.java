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
	private static final String NAME_COLLECTION = "empresa";
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
		database.getCollection(NAME_COLLECTION).insertOne(document);

	}

	public void alterar(Document query, Document where) {
		database.getCollection(NAME_COLLECTION).updateMany(where, new Document("$set", query));
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

		database.getCollection(NAME_COLLECTION).updateOne(whereQuery, new Document("$set", document));
	}

	/**
	 * Deletar.
	 *
	 * @param document the document
	 */
	public void deletar(Document document) {
		database.getCollection(NAME_COLLECTION).deleteMany(document);
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
		database.getCollection(NAME_COLLECTION).deleteOne(new Document("_id", empresa.getCnpj()));
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
		FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find(whereQuery);
		return new EmpresaResourceAssembly().toResource(find.first());
	}

	/**
	 * Selecionar.
	 *
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<Empresa> selecionar() throws IOException {
		FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find();
		List<Empresa> empresas = new ArrayList<Empresa>();
		EmpresaResourceAssembly empresaResourceAssembly = new EmpresaResourceAssembly();
		for (Document document : find) {
			empresas.add(empresaResourceAssembly.toResource(document));
		}
		return empresas;
	}

	/**
	 * Selecionar.
	 *
	 * @param campos the campos
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<Empresa> selecionar(List<String> campos) throws IOException {
		List<Empresa> empresas = null;
		if (campos == null) {
			return empresas;
		}
		if (campos.isEmpty()) {
			return empresas;
		}
		empresas = new ArrayList<Empresa>();
		FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find().projection(include(campos))
				.limit(50);

		EmpresaResourceAssembly empresaResourceAssembly = new EmpresaResourceAssembly();
		for (Document document : find) {
			empresas.add(empresaResourceAssembly.toResource(document));
		}
		return empresas;
	}

}
