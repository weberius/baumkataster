package de.illilli.jdbc;

import java.io.IOException;

/**
 * Diese Schnittstelle beschreibt die Eigenschaft der Objekte, die Abgefragt
 * werden.
 *
 * @param <T>
 */
public interface Select<T> {

	String getSql() throws IOException;

	Object[] getParameter();

	Class<T> getDtoClazz();

}
