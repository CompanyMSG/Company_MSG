package view;

import model.User;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Chat;
import model.User;
import service.ChatService;
import service.ChatServiceImpl;
import service.UserService;
import service.UserServiceImpl;

public class test {

	public static void main(String[] args) throws Exception {
//		new NewFrame();
//
		ChatService chatservice=new ChatServiceImpl();
		Chat c1=new Chat("2","","nihaoya","yangyang");
		try {
			System.out.println(chatservice.addChat(c1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		
//		
//		List list=new ArrayList<>();
//		try {
//			list=chatservice.getAllChat();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(list);
		
//		//TODO********************
		UserService userservice=new UserServiceImpl();

//		User u1=new User(2,"yangda","123456","na","ÑÏ´ò");
//		boolean count=false;
//		boolean count1=false;
//		try {
//			count=userservice.addUser(u1);
//			System.out.println(count);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		//TODO **************************************
//		List list=new ArrayList<>();
////
//		try {
//			list=userservice.getAllUsers();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(((User)list.get(1)).getUsername());
//
//		
//		try {
//			count1=userservice.select(u1);
//			System.out.println(count1);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}

}
