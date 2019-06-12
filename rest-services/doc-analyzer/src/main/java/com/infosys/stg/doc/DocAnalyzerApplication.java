package com.infosys.stg.doc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({ @PropertySource("classpath:doc-analyzer.properties"), @PropertySource("classpath:db.properties"),
		@PropertySource("messages.properties") })
public class DocAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocAnalyzerApplication.class, args);
	}

}
