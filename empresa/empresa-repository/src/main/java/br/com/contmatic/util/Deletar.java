package br.com.contmatic.util;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresa.Empresa;

public class Deletar {

	public void deletar(Empresa empresa) throws IOException {
		Conexao conexao = Conexao.getInstance();
		MongoDatabase database = conexao.getDatabase();

		Document document = new Document("_id", empresa.getCnpj());
		document.remove("cnpj");
		document.append("_id", empresa.getCnpj());
		database.getCollection("empresa").deleteOne(new Document("_id", empresa.getCnpj()));

		conexao.close();

	}

	public static void main(String[] args) throws Exception {
		Empresa empresa = new Empresa();
		empresa.setCnpj("61516268071277");
		Deletar deletar = new Deletar();
		deletar.deletar(empresa);
	}
}
