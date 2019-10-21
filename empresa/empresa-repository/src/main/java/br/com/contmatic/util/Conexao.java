package br.com.contmatic.util;

import java.io.Closeable;
import java.io.IOException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * The Class Conexao.
 */
public final class Conexao implements Closeable {

	/** The Constant HOST. */
	private static final String HOST = "localhost";

	/** The Constant PORT. */
	private static final int PORT = 27017;

	/** The Constant DB_NAME. */
	private static final String DB_NAME = "empresa";

	/** The mongo client. */
	private static MongoClient mongoClient;

	/** The database. */
	private static MongoDatabase database;

	/** The conexao. */
	private static Conexao conexao;

	/**
	 * Instantiates a new conexao.
	 */
	private Conexao() {
	}

	/**
	 * Gets the single instance of Conexao.
	 *
	 * @return single instance of Conexao
	 */
	public static Conexao getInstance() {
		if (mongoClient == null) {
			conexao = new Conexao();
			conexao.getMongoClient();
			conexao.getDatabase();
		}
		return conexao;
	}

	/**
	 * Gets the database.
	 *
	 * @return the database
	 */
	public MongoDatabase getDatabase() {
		if (database == null) {
			mongoClient = getMongoClient();
			database = mongoClient.getDatabase(DB_NAME);
		}
		return database;
	}

	/**
	 * Gets the mongo client.
	 *
	 * @return the mongo client
	 */
	public MongoClient getMongoClient() {
		if (mongoClient == null) {
			mongoClient = new MongoClient(HOST, PORT);

		}
		return mongoClient;
	}

	/**
	 * Close.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void close() throws IOException {
		mongoClient.close();
		mongoClient = null;
		database = null;
	}

}
