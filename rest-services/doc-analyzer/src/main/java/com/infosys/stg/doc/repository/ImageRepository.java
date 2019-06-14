package com.infosys.stg.doc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.infosys.stg.doc.exception.DocAnalyzeException;

@Repository
public class ImageRepository {
	private Logger log = LoggerFactory.getLogger(this.getClass());
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
