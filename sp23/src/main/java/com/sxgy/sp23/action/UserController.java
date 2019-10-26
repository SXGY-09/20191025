package com.sxgy.sp23.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.sxgy.sp23.domain.Book;

@Controller
public class UserController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	//将数据保存到作用域
	@RequestMapping("datatest")
	public String test1(HttpServletRequest request,HttpSession session) {
		request.setAttribute("book", "book-1");
		session.setAttribute("school", "uestc");
		//application作用域
		request.getServletContext().setAttribute("name", "thymeleaf");
		return "test1";
	}
	@RequestMapping("iftest")
	public String test2(WebRequest request) {
		request.setAttribute("username", "Tom",WebRequest.SCOPE_REQUEST);
		request.setAttribute("age", 21, WebRequest.SCOPE_REQUEST);
		request.setAttribute("role", "admin", WebRequest.SCOPE_SESSION);
		return "test2";
	}
	@RequestMapping("eachtest")
	public String test3(WebRequest request) {
		//模拟数据库数据保存到List集合
		List<Book> books=new ArrayList<>();
		books.add(new Book(1,"book1","1.jpg","Jack",108.0));
		books.add(new Book(2,"book2","2.jpg","Tom",28.0));
		books.add(new Book(3,"book3","3.jpg","Alice",102.2));
		books.add(new Book(4,"book4","4.jpg","Tim",34.4));
		books.add(new Book(5,"book5","5.jpg","Jim",72.2));
		request.setAttribute("books", books, WebRequest.SCOPE_REQUEST);
		return "test3";
	}
}
