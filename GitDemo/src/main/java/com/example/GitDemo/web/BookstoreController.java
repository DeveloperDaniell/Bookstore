package com.example.GitDemo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;
import com.example.GitDemo.domain.Book;
import com.example.GitDemo.domain.BookRepository;


@Controller

public class BookstoreController {
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/index")
	public String index(Model model) {
		return "index";
		
	}
	
	@GetMapping("/booklist")
	public String booklist(Model model) {
		List<Book> books =  (List<Book>) bookRepository.findAll();
	     
		model.addAttribute("books", books);
		
		return "booklist";
	}
	
	   @GetMapping("/addbook")
	    public String showAddBookForm(Model model) {
	        model.addAttribute("newBook", new Book());
	        return "addbook";
	    }

	   
	    @PostMapping("/addbook")
	    public String addBook(@ModelAttribute("newBook") Book newBook) {
	       
	        bookRepository.save(newBook);
	        return "redirect:/booklist"; 
	    }

	    
	    @GetMapping("/delete/{id}")
	    public String deleteBook(@PathVariable Long id) {
	        bookRepository.deleteById(id);
	        return "redirect:/booklist"; 
	    }
	    @GetMapping("/edit/{id}")
	    public String editBook(@PathVariable("id") Long id, Model model) {
	      
	        Optional<Book> book = bookRepository.findById(id);
	        
	        if (book.isPresent()) {
	            model.addAttribute("book", book.get());
	            return "editbook";
	        } else {
	            return "redirect:/booklist";
	        }
	    }

	    @PostMapping("/editbook")
	    public String saveEditedBook(@ModelAttribute Book editedBook) {
	        bookRepository.save(editedBook);
	        return "redirect:/booklist";
	    }
	
}