package de.illilli.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class SelectListDao<T> {

	Connection conn;
	Integer id;
	Select<T> select;

	public SelectListDao(Select<T> select, Connection connection) {
		this.conn = connection;
		this.select = select;
	}

	public List<T> execute() throws SQLException, IOException {
		QueryRunner query = new QueryRunner();
		BeanListHandler<T> handler = new BeanListHandler<T>(select.getDtoClazz());
		return query.query(conn, select.getSql(), handler, select.getParameter());
	}

}
