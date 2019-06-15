package com.infosys.stg.doc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.infosys.stg.doc.model.FormCoordinates;
import com.infosys.stg.doc.model.FormField;
import com.infosys.stg.doc.model.TemplatePages;

@Repository
public class FormRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;

	public List<TemplatePages> fetchImages(String barcode) {
		String imageBaseUrl = env.getProperty("url.imageBaseUrl");
		return jdbcTemplate.query(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(env.getProperty("sql.fetchTemplatePages"));
				ps.setString(1, barcode);
				return ps;
			}
		}, new RowMapper<TemplatePages>() {

			@Override
			public TemplatePages mapRow(ResultSet rs, int rowNum) throws SQLException {
				TemplatePages tp = new TemplatePages();
				tp.setImageUrl(imageBaseUrl + rs.getString("image_id"));
				tp.setPageNo(rs.getInt("page_no"));
				tp.setHeight(rs.getDouble("height"));
				tp.setWidth(rs.getDouble("width"));
				tp.setImageData(rs.getString("image"));
				return tp;
			}
		});
	}

	public List<FormField> fetchCoordinates(String barcode) {
		return jdbcTemplate.query(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(env.getProperty("sql.fetchCoordinates"));
				ps.setString(1, barcode);
				return ps;
			}
		}, new RowMapper<FormField>() {

			@Override
			public FormField mapRow(ResultSet rs, int rowNum) throws SQLException {
				FormField resp = new FormField();
				resp.setDataLabel(rs.getString("label"));
				resp.setPageNo(rs.getInt("page_no"));
				FormCoordinates res = new FormCoordinates();
				res.setPresent(true);
				res.setxStart(rs.getDouble("start_x"));
				res.setxEnd(rs.getDouble("end_x"));
				res.setyStart(rs.getDouble("end_x"));
				res.setyEnd(rs.getDouble("end_y"));
				resp.setCoordinates(res);
				return resp;
			}
		});
	}
}
