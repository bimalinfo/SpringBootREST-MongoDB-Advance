package com.doj.article.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.doj.article.web.ArticleRepository;
import com.doj.article.web.ArticleRepositoryImpl;

@SpringBootApplication
@EnableDiscoveryClient
public class WebclientMicroserviceServerArticleApplication {
	
	public static final String ARTICLES_SERVICE_URL = "http://ARTICLE-SERVICE";
	
	public static void main(String[] args) {
		SpringApplication.run(WebclientMicroserviceServerArticleApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
	@Bean
	public ArticleRepository articleRepository(){
		return new ArticleRepositoryImpl(ARTICLES_SERVICE_URL);
	}
}
