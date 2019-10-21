package br.com.contmatic.assembly;

import org.bson.Document;

// TODO: Auto-generated Javadoc
/**
 * The Interface Assembly.
 *
 * @param <T> the generic type
 * @param <D> the generic type
 */
public interface Assembly<T, D extends Document> {
	
	/**
	 * To resource.
	 *
	 * @param document the document
	 * @return the t
	 */
	T toResource(D document);

	/**
	 * To document.
	 *
	 * @param resource the resource
	 * @return the d
	 */
	D toDocument(T resource);
}
