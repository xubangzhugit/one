package com.example.controller;

import com.example.pojo.NewsComment;
import com.example.pojo.NewsDetail;
import com.example.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class NewsController {
	
	@Autowired
	private NewsService service;

	@RequestMapping(path="index.action",method=RequestMethod.GET)
    public String index() {
		System.out.println("index...");
		return "redirect:socket.html";
    }

	/**
	 * 根据时间降序查询所有新闻
	 * @return
	 */
	@RequestMapping(value="/findNews",method=RequestMethod.POST)
	@ResponseBody
	public List<NewsDetail> findNews() {
		System.out.println("findNews:");
		List<NewsDetail> list = service.findAllNews();
		return list;
	}

	/**
	 * 保存评论
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
    public String save(NewsComment nc) {
		System.out.println("save...:"+nc.getNewsId());
		nc.setCcreatedate(new Date());
		service.saveNewsComment(nc);
		return "redirect:socket.html";
    }

	/**
	 * 删除新闻
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String del(Long id) {
		System.out.println("del:"+id);
		if(service.deleteNewsDetail(id)>0){
			return "success";
		}
		return "error";
	}

}
