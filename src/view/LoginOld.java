package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.User;
import service.UserService;
import service.UserServiceImpl;

public class LoginOld extends JFrame {
	public static String username = "hello";
	private JTextField jt;//定义文本框组件
	private JPasswordField password;//密码框
	private JLabel jLabel1,jLabel2;
	private JPanel jp1,jp2,jp3;
	private JButton jb1,jb2,jb3;//创建按钮
	
	
	public LoginOld() {
		setSize(400,350);//窗体大小
		setTitle("登录");
		jLabel1=new JLabel("用户名");
		jLabel2=new JLabel("密   码");
		jLabel1.setFont(new Font("楷体",0,16));
		jLabel2.setFont(new Font("楷体",0,16));
		jt=new JTextField(15);//用户名文本框
		String value=jt.getText().trim();
		password=new JPasswordField(15);//密码框
		String value1=password.getText().trim();
		
		password.setEchoChar('*');
		
		jb1=new JButton("确定");
		jb1.setFont(new Font("楷体",0,16));
		jb2=new JButton("取消");
		jb2.setFont(new Font("楷体",0,16));
		jb3=new JButton("注册");
		jb3.setFont(new Font("楷体",0,16));
		
		setVisible(true);
		Container mk=getContentPane();//获取一个容器
		mk.add(jLabel1);
		mk.add(jLabel2);
		mk.add(jb1);
		mk.add(jb2);
		mk.add(jb3);
		mk.add(jt);
		mk.add(password);
		
		setBounds(800,300,400,350);
		mk.setLayout(null);
		jLabel1.setBounds(40, 40, 60, 25);
		jLabel2.setBounds(40, 80, 60, 25);
		jt.setBounds(100, 40, 200, 25);
		password.setBounds(100, 80, 200, 25);
		jb1.setBounds(140, 180, 70, 35);
		jb2.setBounds(50, 180, 70, 35);
		jb3.setBounds(230, 180, 70, 35);
		
		jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserService userservice=new UserServiceImpl();

				User u1=new User(jt.getText(),password.getText());
				try {
					if(userservice.select(u1)) {
						JOptionPane.showMessageDialog(null, "登录成功");
						setVisible(false);
						u1.setOnLine(1);
//						System.out.println(u1);
						userservice.updateUser(u1);
						username = jt.getText();
						new NewFrame();
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "登录失败");
						setVisible(false);
						new LoginOld();

					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new RegistOld();//进入注册窗体
				setVisible(false);
			}
		});
		
	}

	public static void main(String[] args) {
		new LoginOld();
	}

}
