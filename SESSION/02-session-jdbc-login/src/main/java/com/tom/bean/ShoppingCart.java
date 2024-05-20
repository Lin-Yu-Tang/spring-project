package com.tom.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@SessionScope
@Component
public class ShoppingCart implements Serializable {
  /**
  * 
  */
 private static final long serialVersionUID = -5494311567944263493L;
// private List<Book> cart = new ArrayList<Book>();
//
// public Iterable<Book> getCart(){
//   return cart;
// }
// public void add(Book book){
//  cart.add(book);
// }
// public void cleanup(){
//  cart = new ArrayList<Book>();
// }
// 
}
