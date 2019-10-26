package com.sxgy.sp24.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sxgy.sp24.domain.Book;

@Controller
public class UserController {
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("username","SXGY");
		List<Book> books=new ArrayList<>();
		books.add(new Book(1,"book1","1.jpg","Jack",108.0));
		books.add(new Book(2,"book2","2.jpg","Tom",28.0));
		books.add(new Book(3,"book3","3.jpg","Alice",102.2));
		books.add(new Book(4,"book4","4.jpg","Tim",34.4));
		books.add(new Book(5,"book5","5.jpg","Jim",72.2));
		model.addAttribute("books",books);
		return "index";
	}
}
