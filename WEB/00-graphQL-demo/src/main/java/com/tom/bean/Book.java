package com.tom.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public record Book (String id, String name, int pageCount, String authorId) {

    private static List<Book> books = List.of(
            new Book("book-1", "Effective Java", 416, "author-1"),
            new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
            new Book("book-3", "Hello world", 208, "author-3"),
            new Book("book-999", "Down Under", 436, "author-999")
    );
    
    private static List<Book> findByIds(Object[] ids) {
    	List<Predicate<Book>> predicates = new ArrayList<>();
    	
    	Stream.of(ids).forEach(id -> predicates.add(e -> e.authorId().equals(id)));
    	
    	Predicate<Book> compositePredicate = predicates.stream()
    		              							.reduce(b -> false, Predicate::or);

    	return books.stream()
					.filter(compositePredicate)
					.collect(Collectors.toList());
    }
    
    public static List<Book> getAll() {
    	return books;
    }
    
    public static List<Book> booksByAuthorFirstName(String firstName) {
    	List<Author> authors = Author.getByName(firstName);
    	Object[] ids = authors.stream().map(e -> e.id()).toArray();
        return findByIds(ids);
    }
    
    public static List<Book> search(String type, String argument) {
    	
    	if ("id".equals(type)) {
    		System.out.println("id");
    		return books.stream()
    		    	 .filter(book -> book.id().equals(argument))
    				 .collect(Collectors.toList());
    	}
    	if ("name".equals(type)) {
    		System.out.println("name");
    		return books.stream()
   		    	 .filter(book -> book.name().equals(argument))
   				 .collect(Collectors.toList());
    	}
    	if ("pageCount".equals(type)) {
    		System.out.println("pageCount");
    		return books.stream()
   		    	 .filter(book -> book.pageCount() == Integer.parseInt(argument))
   				 .collect(Collectors.toList());
    	}
    	if ("authorId".equals(type)) {
    		System.out.println("authorId");
    		return books.stream()
   		    	 .filter(book -> book.authorId().equals(argument))
   				 .collect(Collectors.toList());
    	}
    	return Collections.emptyList();
    	
    }
    
    public static List<Book> findByName(String name) {
    	return books.stream()
				.filter(book -> book.name().equals(name))
				.collect(Collectors.toList());
    }

    public static Book getById(String id) {
        return books.stream()
				.filter(book -> book.id().equals(id))
				.findFirst()
				.orElse(null);
    }
    
    public static Book getByName(String name) {
    	return books.stream()
				.filter(book -> book.name().equals(name))
				.findFirst()
				.orElse(null);
    }
}
