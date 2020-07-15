package com.example.controller;

import com.example.pojo.NewsDetail;
import com.example.service.NewsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HelloController {
	
	@Autowired
	private NewsService service;

	@GetMapping("/hello")
    public String hello(Model model, HttpSession session) {
		System.out.println("hello...");

		NewsDetail n = new NewsDetail();
		n.setId(3);
		n.setTitle("标题111333");
		List<NewsDetail> list = service.findAllNews();

		model.addAttribute("entity",n);
		model.addAttribute("list",list);
		model.addAttribute("name", "admin");
		model.addAttribute("count",50);
		model.addAttribute("total",100);

		session.setAttribute("nd",n);
		return "hello";
    }

	/**
	 * 查询所有新闻
	 * @return
	 */
	@RequestMapping(value="/find")
	public String findAllNews(Model model) {
		System.out.println("find:");
		Page<NewsDetail> pg = PageHelper.startPage(1,2);
		service.findAllNews();
		System.out.println("list:"+pg.getResult());

		model.addAttribute("list",pg.getResult());
		model.addAttribute("comments",service.selectAll());
		return "news";
	}

	/**
	 * 查询所有新闻
	 * @return
	 */
	@RequestMapping(value="/findComment")
	public String findComment(Model model) {
		System.out.println("findComment:");
		model.addAttribute("comments",service.selectAll());
		return "comment";
	}

}
