package com.javabydeveloper.demo.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SqlProvider;

public class UserPreparedStatementCreator implements PreparedStatementCreator, SqlProvider {

	private String param;
	private String sql;

	public UserPreparedStatementCreator(String sql, String param) {
		super();
		this.sql = sql;
		this.param = param;
	}

	@Override
	public String getSql() {

		return sql;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement(getSql(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ps.setString(1, param);
		return ps;
	}
}
