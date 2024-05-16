package com.tom.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.tom.bean.Author;
import com.tom.bean.Book;

@Controller
public class HelloController {
	
    @QueryMapping
    public Book bookById(@Argument("id") String id) {
    	/*
    	 query bookDetails {
			  bookById(id: "book-1") {
			    id
			    name
			    pageCount
			    author {
			      firstName
			      lastName
			    }
			  }
			}
    	 */
        return Book.getById(id);
    }
    
    @QueryMapping
    public List<Book> findAllBooks() {
    	/*
    	 query b {
		  findAllBooks{
		    id
		    name
		    pageCount
		    author {
		      firstName
		      lastName
		    }
		  }
		}
    	 */
    	
    	return Book.getAll();
    }
    
    @QueryMapping
    public List<Book> findBooks(@Argument("match") List<String> field) {
    	/*
			query b {
			  findBooks(match: ["name", "Effective Java"]){
			    id
			    name
			    pageCount
			    author {
			      firstName
			      lastName
			    }
			  }
			}
    	 */
    	
    	Field[] fields = Book.class.getDeclaredFields();
    	Stream.of(fields).forEach(e -> {
    		System.out.println(e.getName());
    	});
    	
    	System.out.println("findBooks, arguments: " + field.get(0) +", " + field.get(1));
    	return Book.search(field.get(0), field.get(1));
    }
    
    @QueryMapping
    public List<Book> booksByAuthorFirstName(@Argument("firstName") String firstName) {
    	/*
    	 query b {
			  booksByAuthorFirstName(firstName: "Douglas"){
			    id
			    name
			    pageCount
			    author {
			      firstName
			      lastName
			    }
			  }
			}
    	 */
    	return Book.booksByAuthorFirstName(firstName);
    }
    
    
    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }
}
