package com.doj.article.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dinesh.Rajput
 *
 */
public class ArticleRepositoryImpl implements ArticleRepository {
	
	@Autowired
	protected RestTemplate restTemplate;
	
	protected String serviceUrl;
	
	public ArticleRepositoryImpl(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}
	
	@Override
	public List<Article> getAllArticles() {
		//Article[] article = restTemplate.getForObject(serviceUrl+"/articles", Article[].class);
		Article[] article = restTemplate.getForObject(serviceUrl+"/", Article[].class);
		return Arrays.asList(article);
	}

	@Override
	public Article getArticle(String number) {
		return restTemplate.getForObject(serviceUrl + "/{id}",
				Article.class, number);
	}

}
