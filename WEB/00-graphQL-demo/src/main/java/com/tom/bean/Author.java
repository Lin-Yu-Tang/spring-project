package com.tom.bean;

import java.util.List;
import java.util.stream.Collectors;

public record Author (String id, String firstName, String lastName) {

    private static List<Author> authors = List.of(
            new Author("author-1", "Joshua", "Bloch"),
            new Author("author-2", "Douglas", "Adams"),
            new Author("author-3", "Douglas", "Tom"),
            new Author("author-999", "Bill", "Bryson")
    );

    public static Author getById(String id) {
        return authors.stream()
				.filter(author -> author.id().equals(id))
				.findFirst()
				.orElse(null);
    }
    
    
    public static List<Author> getByName(String firstName) {
        return authors.stream()
				.filter(author -> author.firstName().equals(firstName))
				.collect(Collectors.toList());
    }
}