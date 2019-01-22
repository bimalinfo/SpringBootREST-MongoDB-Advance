/**
 * 
 */
package com.doj.article.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Dinesh.Rajput
 *
 */
@Controller
public class ArticleController {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@RequestMapping("/")
	public String home(){
		return "index";
	}
	@RequestMapping("/articleList")
	public String articleList(Model model) {
		System.out.println(".............ArticleList..................");
		model.addAttribute("articles", articleRepository.getAllArticles());
		return "articleList";
	}
	
	@RequestMapping("/articleDetails")
	public String articleDetails(@RequestParam("number") String id, Model model) {
		System.out.println("..............ArticleDetails...............");
		model.addAttribute("article", articleRepository.getArticle(id));
		return "articleDetails";
	}
}
