package com.brunopw.superstore;

import com.brunopw.superstore.services.ItemService;
import com.brunopw.superstore.services.OrderService;
import com.brunopw.superstore.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SuperStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperStoreApplication.class, args);
	}

	@Bean
	public String initializeData(UserService userService, OrderService orderService, ItemService itemService) {
		User buyer = new User();
		buyer.setName("Bruno");
		buyer.setAddress("Dublin");
		buyer.setType(UserType.CLIENT);
		buyer.setBirthday(LocalDate.of(1992, 01, 28));
		buyer.setEmail("bruno@gmail.com");

		userService.saveOrUpdate(buyer);

		User buyer2 = new User();
		buyer2.setName("Ramon");
		buyer2.setAddress("Dublin");
		buyer2.setType(UserType.CLIENT);
		buyer2.setBirthday(LocalDate.of(1900, 05, 14));
		buyer2.setEmail("ramon@gmail.com");

		userService.saveOrUpdate(buyer2);

		User buyer3 = new User();
		buyer3.setName("Taina");
		buyer3.setAddress("Dublin");
		buyer3.setType(UserType.CLIENT);
		buyer3.setBirthday(LocalDate.of(1970, 01, 31));
		buyer3.setEmail("taina@gmail.com");

		userService.saveOrUpdate(buyer3);

		User buyer4 = new User();
		buyer4.setName("Jean");
		buyer4.setAddress("Porto Alegre");
		buyer4.setType(UserType.CLIENT);
		buyer4.setBirthday(LocalDate.of(1993, 01, 31));
		buyer4.setEmail("jean@gmail.com");

		userService.saveOrUpdate(buyer4);

		User seller = new User();
		seller.setName("Daniel");
		seller.setAddress("Porto Alegre");
		seller.setType(UserType.ADM);
		seller.setEmail("daniel@gmail.com");

		userService.saveOrUpdate(seller);

		List itens = new ArrayList<Item>();

		Item guinness = new Item("Guinness", 1.99, 12);
		itemService.saveOrUpdate(guinness);
		itens.add(guinness);
		Item coke = new Item("Coke", 0.99, 6);
		itemService.saveOrUpdate(coke);
		itens.add(coke);
		Item rice = new Item("Basmati Rice", 0.89, 2);
		itemService.saveOrUpdate(rice);
		itens.add(rice);

		Order order = new Order(buyer,seller, itens);
		order.setDate(ZonedDateTime.of(2020,1,28,9,30,42,100, ZoneOffset.UTC));
		orderService.saveOrUpdate(order);

		itens = new ArrayList<Item>();
		Item hopHouse = new Item("Hop House", 1.73, 6);
		itemService.saveOrUpdate(hopHouse);
		itens.add(hopHouse);

		Order order2 = new Order(buyer2,seller, itens);
		order2.setDate(ZonedDateTime.of(2020,2,14,9,30,28,100, ZoneOffset.UTC));
		orderService.saveOrUpdate(order2);

		itens = new ArrayList<Item>();
		Item bluePaper = new Item("Blue Paper", 10.0, 1);
		itemService.saveOrUpdate(bluePaper);
		itens.add(bluePaper);

		Order order3 = new Order(buyer3,seller, itens);
		order3.setDate(ZonedDateTime.of(2020,3,20,8,8,2,100, ZoneOffset.UTC));
		orderService.saveOrUpdate(order3);

		itens = new ArrayList<Item>();
		Item milk = new Item("Milk", 0.75, 2);
		itemService.saveOrUpdate(milk);
		itens.add(milk);

		Order order4 = new Order(buyer4,seller, itens);
		order4.setDate(ZonedDateTime.of(2020,4,15,11,30,10,100, ZoneOffset.UTC));
		orderService.saveOrUpdate(order4);

		return null;
	}

}
