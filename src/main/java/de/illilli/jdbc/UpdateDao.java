package de.illilli.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class UpdateDao {

	private Connection conn;
	private InsertOrUpdate ior;

	public UpdateDao(InsertOrUpdate ior, Connection connection) {
		this.conn = connection;
		this.ior = ior;
	}

	public int execute() throws SQLException, IOException {
		QueryRunner run = new QueryRunner();
		return run.update(this.conn, ior.getSql(), ior.getParameter());
	}

}
