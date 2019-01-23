package com.mongo.embeded;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongo.embeded.Company;
import com.mongo.embeded.Contact;
import com.mongo.embeded.Product;
import com.mongo.embeded.CompanyRepository;

@SpringBootApplication
public class SpringDataMongoDbApplication implements CommandLineRunner{

	@Autowired
	CompanyRepository companyRepo;
	
	@Override
	public void run(String... args) throws Exception {
		// clear database
		System.out.println("==========Delete all company entities==========");
		companyRepo.deleteAll();
		
		// save Documents to MongoDB
		System.out.println("==========Save list of company entities for Apple==========");
		Product p1=new Product("A-123", "Iphone 7", "Price: 649.00 USD & FREE shipping");
		Product p2=new Product("A-456", "IPadPro", "Price: 417.67 USD & FREE shipping");
		List<Product> listProduct1=new ArrayList<Product>();
		listProduct1.add(p1);
		listProduct1.add(p2);
		Contact c1=new Contact("Cupertino, CA 95014", "1-408-996-1010");
		
		//For Samsung
		System.out.println("==========Save list of company entities for Samsung==========");
		Product p3=new Product("Jprime", "Samsung Galaxy 7", "Price: 649.00 USD & FREE shipping");
		Product p4=new Product("Jprime Pro", "Samsung Galaxy 7 Pro", "Price: 649.00 USD & FREE shipping");
		List<Product> listProduct2=new ArrayList<Product>();
		listProduct2.add(p3);
		listProduct2.add(p4);
		Contact c2=new Contact("Japan, CA 95014", "1-408-996-1010");
		
		List<Company> company=Arrays.asList(new Company(1, "Apollo",listProduct1, c1),new Company(2, "Samsung",listProduct2, c2));
		companyRepo.save(company);
		// initial List Companies variable
		List<Company> companies = null;
		
		// fetch all documents
		System.out.println("==========Fetch aLL companies:==========");
		companies = companyRepo.findAll();
		companies.forEach(System.out::println);
		
		// find Company by name
		System.out.println("==========Find a company by name:==========");
		companies = companyRepo.findByName("Samsung");
		companies.forEach(System.out::println);
		
		// find Company by address
		System.out.println("==========Find a company by address:==========");
		companies = companyRepo.findByAddress("Cupertino, CA 95014");
		companies.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataMongoDbApplication.class, args);
	}
}


