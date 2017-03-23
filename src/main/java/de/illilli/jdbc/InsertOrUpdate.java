package de.illilli.jdbc;

import java.io.IOException;

public interface InsertOrUpdate {

	Object[] getParameter();

	String getSql() throws IOException;

}
