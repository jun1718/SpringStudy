package com.spring.basic.ex04;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		GenericXmlApplicationContext ct = 
				new GenericXmlApplicationContext("classpath:auto-config.xml");
		
		
		Printer printer = ct.getBean("printer", Printer.class);
		printer.showPaperInfo();
		
		Book book = ct.getBean("book", Book.class);
		String datas = Arrays.toString(book.getPaper().data);
		System.out.println(datas);
		
		ct.close();
	}

}
