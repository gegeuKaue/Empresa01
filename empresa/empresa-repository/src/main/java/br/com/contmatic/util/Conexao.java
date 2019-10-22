package br.com.contmatic.util;

import java.io.Closeable;
import java.io.IOException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * The Class Conexao.
 */
public final class Conexao implements Closeable {

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
			database = mongoClient.getDatabase(MongoConf.DB_NAME);
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
			mongoClient = new MongoClient(MongoConf.HOST, MongoConf.PORT);

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
