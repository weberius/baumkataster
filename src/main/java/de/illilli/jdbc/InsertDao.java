package de.illilli.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class InsertDao {

	private Connection conn;
	private InsertOrUpdate ior;

	public InsertDao(InsertOrUpdate ior, Connection connection) {
		this.conn = connection;
		this.ior = ior;
	}

	public int execute() throws SQLException, IOException {
		QueryRunner run = new QueryRunner();
		ResultSetHandler<Integer> rsh = new ScalarHandler<Integer>();
		return run.insert(this.conn, ior.getSql(), rsh, ior.getParameter());
	}

}
