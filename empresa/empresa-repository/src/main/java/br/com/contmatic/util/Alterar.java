package br.com.contmatic.util;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresa.Empresa;

public class Alterar {

	public Alterar() {

	}

	public void alterar(Empresa empresa) throws IOException {
		Conexao conexao = Conexao.getInstance();
		MongoDatabase database = conexao.getDatabase();

		Document document = Document.parse(empresa.toString());
		document.remove("cnpj");
		document.append("_id", empresa.getCnpj());
		database.getCollection("empresa").insertOne(document);
		conexao.close();
	}
}
