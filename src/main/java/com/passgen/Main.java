package com.passgen;

import java.util.*;
import org.apache.http.auth.AuthenticationException;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws AuthenticationException, IOException {

	  String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
      String CHAR_UPPER = CHAR_LOWER.toUpperCase();
      String NUMBER = "0123456789";
	  String all=CHAR_LOWER+CHAR_UPPER+NUMBER;

	  parser parser = new parser();
	  userlistparser userlist = new userlistparser();
		String vkn = args[0];
		String islem = args[1];
		// İlk Arg VKN Olarak Girilip Girilmediğini Kontrol Ediyor.
		if (vkn.length() <= 9) {
			System.out.println("********************************************\n");
			System.out.println("Lütfen Önce VKN Numarasını Giriniz. ( 10-11 ) Karakterli Değer."); 
			return;

		}
	
		
	   if (islem.isEmpty()){
			
			System.out.println("Yapılması Gereken İşlemi Seçin. ( testk yada canlik )");
			return;
		
		}
		if (islem.equals("testk")){
			parser.setUrl("http://192.168.1.75/api/auth/user/create");
		}
		else if (islem.equals("canlik")){
			parser.setUrl("http://efatura.idecon.com.tr/api/auth/user/create");
		}
		//if(argalias.startsWith("urn:mail:"))
		

		parser.vkn1=vkn;

		userlist.vkn1=vkn;

	  Random rand = new Random();
	 
	  String deneme="";

		for (int i = 0 ; i<10;i++){

			int PassLen = rand.nextInt(all.length());
			deneme += all.charAt(PassLen);

		}
		parser.sifre=deneme;
		
		userlist.jsonyaz1();

		if (userlist.kilit.equals("kilitli")){

		}
		else{
			parser.jsonyaz(userlist.alias);
		}
	}



}