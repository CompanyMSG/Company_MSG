package view;

import model.Chat;
import model.User;
import service.ChatService;
import service.ChatServiceImpl;
import service.UserService;
import service.UserServiceImpl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;			

//�´���
public class NewFrame extends JFrame {
	JPanel jp1;        //�������
	JSplitPane jsp;    //�����ִ���
	JTextArea jta1;    //�����ı���
	JScrollPane jspane1;    //�����������
	JTextArea jta2;
	JScrollPane jspane2;

	JPanel jp2;
	JButton jb1, jb2;    //���尴ť
	JComboBox jcb1;        //����������

	public NewFrame() {
		//�������
		//�ϲ����
		jp1 = new JPanel();    //�������
		jta1 = new JTextArea();    //���������ı���
		jta1.setLineWrap(true);    //���ö����ı����Զ�����
		jspane1 = new JScrollPane(jta1);    //������������
		String line = jta1.getText();

		jta2 = new JTextArea();
		jta2.setLineWrap(true);

		jspane2 = new JScrollPane(jta2);
		jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jspane1, jspane2); //������ִ���
		jsp.setDividerLocation(200);    //���ò�ִ����Ƶ����ʼλ��
		jsp.setDividerSize(1);            //���÷�Ƶ����С
		//�²����
		jp2 = new JPanel();
		jb1 = new JButton("�ر�");        //������ť
		jb2 = new JButton("����");
		String [] name= {"����","������","˾����","��ƼƼ","�����"};
		jcb1=new JComboBox(name);	//����������

		//���ò��ֹ���
		jp1.setLayout(new BorderLayout());    //������岼��
		jp2.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//������
		jp1.add(jsp);
		jp2.add(jcb1);
		jp2.add(jb1);
		jp2.add(jb2);

		this.add(jp1, BorderLayout.CENTER);
		this.add(jp2, BorderLayout.SOUTH);

		//���ô���ʵ��
		this.setTitle("�������");        //���ý������
		this.setSize(400, 350);                //���ý�������
		this.setLocation(300, 300);            //���ý����ʼλ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //����������ͽ���һͬ�ر�
		this.setVisible(true);                //���ý�����ӻ�

		//�ر�
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				User u1 = new User();
				u1.setUsername(LoginOld.username);
				u1.setOnLine(false);
				UserService userservice=new UserServiceImpl();
				try {
					userservice.updateUser(u1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dispose();
			}
		});
		//����
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserService userservice = new UserServiceImpl();
				String name = LoginOld.username;
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("MM-dd hh:mm:ss");
				String string = "��" + ft.format(dNow) + "��" + "\n" + name + "��";
				//�ύ��Ϣ
				Chat chat = new Chat(name, "", jta2.getText());
				ChatService chatservice = new ChatServiceImpl();
				try {
					System.out.println(chatservice.addChat(chat));
				} catch (SQLException e) {
					e.printStackTrace();
				}
//				//������ʾ
//				String line = string + jta2.getText();
//				jta1.append(line + "\n");
//				jta2.setText(" ");
			}
		});

		Thread1 t = new Thread1();
		t.start();

	}
	class Thread1 extends Thread {
		public void run() {
			synchronized (new Object()) {
				int num = 0;
				for (int j = 0; j < 10000000; j++) {
					//������Ϣ
					ChatService chatservice1 = new ChatServiceImpl();
					Chat chat1 = new Chat();
					List list = new ArrayList<>();
					try {
						list = chatservice1.getAllChat();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if (list.size() != 0 ) {
						for (int i = 0; i < list.size(); i++) {
							chat1 = (Chat) list.get(i);
							String line1 = "��" + chat1.getTime() + "��\n" + chat1.getName() + "�� " + chat1.getText();
							if((((Chat) list.get(i)).getChatId() > num)){
								jta1.append(line1 + "\n");
								jta2.setText(" ");
								num ++ ;
							}
						}
					}
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	public void start() {
		new Thread1().start();
	}
}








//				jb2.addActionListener(new ActionListener() {
//		            @Override
//		            public void actionPerformed(ActionEvent e) {
//		                System.out.println(jta2.getText());
//		            }
//		        });

			

