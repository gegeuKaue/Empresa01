package br.com.contmatic.util;

import java.io.IOException;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresa.Empresa;

public class Alterar {

	public void alterar(Empresa empresa) throws IOException {
		Conexao conexao = Conexao.getInstance();
		MongoDatabase database = conexao.getDatabase();

		Document document = Document.parse(empresa.toString());
		document.remove("cnpj");
		document.append("_id", empresa.getCnpj());

		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("_id", empresa.getCnpj());

		database.getCollection("empresa").updateOne(whereQuery, new Document("$set",document));
		conexao.close();
	}

	public static void main(String[] args) throws Exception {
		Selecionar selecionar = new Selecionar();
		Empresa empresa = new Empresa();
		empresa.setCnpj("61516268071277");
		List<Empresa> empresas = selecionar.selecionar(empresa);

		empresas.get(0).setNome("Kaue");
		empresas.get(0).setEmail("KaueSantos@gmail.com");
		Alterar alterar = new Alterar();
		alterar.alterar(empresas.get(0));
	}
}
