package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.User;
import service.UserService;
import service.UserServiceImpl;

//ע�ᴰ��
public class RegistOld extends JFrame {
	private JLabel l1;//�û���
	private JLabel l2;//����
	private JLabel l3;//�ٴ�ȷ������
	private JTextField p1;//�û����ı���
	private JPasswordField p2;//�����ı���
	private JPasswordField p3;//�ٴ�ȷ�������
	private JButton b1,b2,b3;
	public RegistOld() {
		setSize(300,250);
		l1=new JLabel("�û���");
		l1.setFont(new Font("����",0,16));
		l2=new JLabel("��   ��");
		l2.setFont(new Font("����",0,16));
		l3=new JLabel("�ٴ�ȷ������");
		
		p1=new JTextField(15);
		p2=new JPasswordField(15);
		p3=new JPasswordField(15);
		
		p2.setEchoChar('*');
		p3.setEchoChar('*');
		
		b1=new JButton("ȷ��");
		b1.setFont(new Font("����",0,16));
		b2=new JButton("����");
		b2.setFont(new Font("����",0,16));
		b3=new JButton("����");
		b3.setFont(new Font("����",0,16));
		
		setVisible(true);
		
		Container mk=getContentPane();
		mk.add(l1);
		mk.add(l2);
		mk.add(l3);
		
		mk.add(p1);
		mk.add(p2);
		mk.add(p3);
		mk.add(b1);
		mk.add(b2);
		mk.add(b3);
		setBounds(800,300,400,350);
		mk.setLayout(null);
		l1.setBounds(40, 40, 60, 25);
		l2.setBounds(40, 80, 60, 25);
		
		p1.setBounds(100, 40, 200, 25);
		p2.setBounds(100, 80, 200, 25);
		b1.setBounds(150, 180, 70, 35);
		b2.setBounds(50, 180, 70, 35);
		b3.setBounds(250, 180, 70, 35);
		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoginOld();
				setVisible(false);
			}
		});
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserService userservice=new UserServiceImpl();
				User u1=new User(p1.getText(),p2.getText());
				try {
					if(! userservice.select(u1)) {
						userservice.addUser(u1);
						JOptionPane.showMessageDialog(null, "ע��ɹ�");
						setVisible(false);
						new LoginOld();
					}else {
						System.out.println("�û�����ͬ");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
//		b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(p1.getText()+"\n"+p2.getText());
//            }
//        });
//			
		
	}
}
