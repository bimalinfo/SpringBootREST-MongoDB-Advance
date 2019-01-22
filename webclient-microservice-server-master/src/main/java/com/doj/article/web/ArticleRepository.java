/**
 * 
 */
package com.doj.article.web;

import java.util.List;

/**
 * @author Dinesh.Rajput
 *
 */
public interface ArticleRepository {
	
	List<Article> getAllArticles();
	
	Article getArticle(String number);
}
