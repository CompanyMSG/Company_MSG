package view;

import model.User;

import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Chat;
import model.User;
import service.ChatService;
import service.ChatServiceImpl;
import service.UserService;
import service.UserServiceImpl;
public class UserText {
    public static String setString(String text){

        UserService userservice=new UserServiceImpl();



        String name = LoginOld.username;
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("MM-dd hh:mm:ss");
        String string = "¡¾" + ft.format(dNow) + "¡¿" + "\n" +  name + "£º";

        Chat chat = new Chat(name,"",text);
        ChatService chatservice=new ChatServiceImpl();
        try {
            System.out.println(chatservice.addChat(chat));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return string;
    }

//    public static Chat getString(){
//
//
////        System.out.println(string);
//
//        return chat;
//    }

    public static void main(String[] args) throws Exception {
        new NewFrame();
    }
}
