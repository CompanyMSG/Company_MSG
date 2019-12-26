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
	private JTextField jt;//�����ı������
	private JPasswordField password;//�����
	private JLabel jLabel1,jLabel2;
	private JPanel jp1,jp2,jp3;
	private JButton jb1,jb2,jb3;//������ť
	
	
	public LoginOld() {
		setSize(400,350);//�����С
		setTitle("��¼");
		jLabel1=new JLabel("�û���");
		jLabel2=new JLabel("��   ��");
		jLabel1.setFont(new Font("����",0,16));
		jLabel2.setFont(new Font("����",0,16));
		jt=new JTextField(15);//�û����ı���
		String value=jt.getText().trim();
		password=new JPasswordField(15);//�����
		String value1=password.getText().trim();
		
		password.setEchoChar('*');
		
		jb1=new JButton("ȷ��");
		jb1.setFont(new Font("����",0,16));
		jb2=new JButton("ȡ��");
		jb2.setFont(new Font("����",0,16));
		jb3=new JButton("ע��");
		jb3.setFont(new Font("����",0,16));
		
		setVisible(true);
		Container mk=getContentPane();//��ȡһ������
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
						JOptionPane.showMessageDialog(null, "��¼�ɹ�");
						setVisible(false);
						u1.setOnLine(1);
//						System.out.println(u1);
						userservice.updateUser(u1);
						username = jt.getText();
						new NewFrame();
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "��¼ʧ��");
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
				new RegistOld();//����ע�ᴰ��
				setVisible(false);
			}
		});
		
	}

	public static void main(String[] args) {
		new LoginOld();
	}

}
