package com.sxgy.sp25.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxgy.sp25.domain.Book;

@Controller
public class UserController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping("/findBook")
	@ResponseBody
	public Book findBook(@RequestBody Book book) {
		//观察页面传入的数据是否封装到Book对象
		System.out.println(book);
		//设置book的其他信息
		book.setAuthor("Alexis");
		book.setImage("2.jpg");
		book.setPrice(198.98);
		book.setRemark("hehehe");
		return book;
	}
	@RequestMapping("/getJson")
	public String getJson() {
		return "getJson";
	}
	@RequestMapping("/findBooks")
	@ResponseBody
	public List<Book> findBooks() {
		List<Book> books=new ArrayList<>();
		books.add(new Book(1,"book1","1.jpg","Jack",108.0));
		books.add(new Book(2,"book2","2.jpg","Tom",28.0));
		books.add(new Book(3,"book3","3.jpg","Alice",102.2));
		books.add(new Book(4,"book4","4.jpg","Tim",34.4));
		books.add(new Book(5,"book5","5.jpg","Jim",72.2));
		return books;
	}
}
