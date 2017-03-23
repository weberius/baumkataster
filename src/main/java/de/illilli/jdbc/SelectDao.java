package de.illilli.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class SelectDao<T> {

	Connection conn;
	Integer id;
	Select<T> select;

	public SelectDao(Select<T> select, Connection connection) {
		this.conn = connection;
		this.select = select;
	}

	public T execute() throws SQLException, IOException {
		QueryRunner query = new QueryRunner();
		ResultSetHandler<T> handler = new BeanHandler<T>(select.getDtoClazz());
		return query.query(conn, select.getSql(), handler, select.getParameter());
	}

}
