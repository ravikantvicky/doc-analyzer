package com.infosys.stg.doc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.infosys.stg.doc.exception.DocAnalyzeException;

@Repository
public class TemplateRepository {
	@Autowired
	private Environment env;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public long saveTemplate(String templateName, String barcode, String description) throws DocAnalyzeException {
		try {
			String sql = env.getProperty("sql.saveTemplate");
			GeneratedKeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, templateName);
					ps.setString(2, barcode);
					ps.setString(3, description);
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DocAnalyzeException("Error in saving template details.");
		}
	}

	public long saveTemplatePages(long templateId, int pageNo, String imageData, double width, double height)
			throws DocAnalyzeException {
		try {
			String sql = env.getProperty("sql.saveTemplatePages");
			GeneratedKeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, templateId);
					ps.setInt(2, pageNo);
					ps.setDouble(3, width);
					ps.setDouble(4, height);
					ps.setString(5, imageData);
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DocAnalyzeException("Error in saving template images details.");
		}
	}
}
