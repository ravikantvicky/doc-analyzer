package com.infosys.stg.doc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	Environment env;

	public String fetchImage(long imageId) {
		try {
			String sql = env.getProperty("sql.image.fetch");
			String resp = jdbcTemplate.query(sql, new Object[] { imageId }, new ResultSetExtractor<String>() {

				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next())
						return rs.getString("image");
					else
						return null;
				}
			});
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
