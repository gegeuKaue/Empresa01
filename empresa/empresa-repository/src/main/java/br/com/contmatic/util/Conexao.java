package br.com.contmatic.util;

import java.io.Closeable;
import java.io.IOException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Conexao implements Closeable {
	private static final String HOST = "localhost";
	private static final int PORT = 27017;
	private static final String DB_NAME = "empresa";
	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static Conexao conexao;

	private Conexao() {
	}

	public static Conexao getInstance() {
		if (mongoClient == null) {
			conexao = new Conexao();
		}
		return conexao;
	}

	public MongoDatabase getDatabase() {
		if (mongoClient == null) {
			mongoClient = new MongoClient(HOST, PORT);
			database = mongoClient.getDatabase(DB_NAME);
		}
		return database;
	}

	@Override
	public void close() throws IOException {
		mongoClient.close();
		mongoClient = null;
		database = null;
	}

}
