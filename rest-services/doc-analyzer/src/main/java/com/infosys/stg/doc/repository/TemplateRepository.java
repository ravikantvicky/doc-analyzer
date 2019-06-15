package com.infosys.stg.doc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.infosys.stg.doc.Utils.AppUtils;
import com.infosys.stg.doc.exception.DocAnalyzeException;
import com.infosys.stg.doc.model.SaveTemplateRequest;
import com.infosys.stg.doc.model.TemplateData;

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

	public void saveTemplateData(SaveTemplateRequest request) throws DocAnalyzeException {
		try {
			Set<String> keys = new HashSet<>();
			jdbcTemplate.batchUpdate(env.getProperty("sql.saveTemplateData"), new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					TemplateData req = request.getData().get(i);
					String key = AppUtils.convertToKey(req.getLabel(), keys);
					ps.setLong(1, request.getTemplateId());
					ps.setString(2, req.getLabel());
					ps.setString(3, key);
					ps.setLong(4, request.getTemplateId());
					ps.setInt(5, req.getPageNo());
					ps.setDouble(6, req.getStartX());
					ps.setDouble(7, req.getStartY());
					ps.setDouble(8, req.getEndX());
					ps.setDouble(9, req.getEndY());
				}

				@Override
				public int getBatchSize() {
					return request.getData().size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DocAnalyzeException("Error in saving template data..");
		}
	}

	public void updateBarCode(String barcode, long templateId) throws DocAnalyzeException {
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(env.getProperty("sql.updateBarCode"));
					ps.setString(1, barcode);
					ps.setLong(2, templateId);
					return ps;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DocAnalyzeException("Error in updating barcode. ");
		}
	}

	public String getTemplateName(long templateId) {
		try {
			return jdbcTemplate.query(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(env.getProperty("sql.fetchTemplateName"));
					ps.setLong(1, templateId);
					return ps;
				}
			}, new ResultSetExtractor<String>() {

				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					while (rs.next())
						return rs.getString(1);
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
