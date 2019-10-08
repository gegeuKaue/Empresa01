package br.com.contmatic.util;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresa.Empresa;

public class Conexao {
	private static final String HOST = "localhost";
	private static final int PORT = 27017;
	private static final String DB_NAME = "empresa";
	private MongoClient mongoClient;
	private MongoDatabase database;

	public Conexao() {
		mongoClient = new MongoClient(HOST, PORT);
		database = mongoClient.getDatabase(DB_NAME);
	}

	public static void main(String[] args) {
		Conexao conexao = new Conexao();
		MongoCollection<Document> document = conexao.database.getCollection("empresa");
		for (Document empresa : document.find()) {

			System.out.println(empresa.get("_id"));
		}
	}

	public void salvar(Empresa empresa) {
		Document document = Document.parse(empresa.toString());
		document.remove("cnpj");
		document.append("_id", empresa.getCnpj());
		database.getCollection("empresa").insertOne(document);
	}
}
