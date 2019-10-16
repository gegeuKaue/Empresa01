package br.com.contmatic.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;


public class ConexaoTest {

	@Before
	public void beforeEach() throws Exception {
		
	}

	@After
	public void afterEach() throws Exception {

	}

	@Test
	public void deve_conectar_com_banco() throws IOException {
		try {
			MongoDatabase db = Conexao.getInstance().getDatabase();
			FindIterable<Document> find = db.getCollection("empresa").find().limit(1);
			for (Document document : find) {
				document.hashCode();
			}

			assertTrue(true);
		} catch (Exception e) {
			fail("Banco não encontrado");
		}
	}

	@Test
	public void deve_retornar_a_conexao_database() throws IOException {
		Conexao.getInstance().getMongoClient();
		Conexao.getInstance().close();
		MongoDatabase database = Conexao.getInstance().getDatabase();
		database.getName();
	}

	@Test
	public void deve_retornar_o_mongo_client() throws IOException {
		Conexao.getInstance().close();
		Conexao.getInstance().getMongoClient();
		
	}
}
