package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.Chat;
import model.User;
import service.ChatService;
import service.ChatServiceImpl;
import service.UserService;
import service.UserServiceImpl;			

//�´���
public class NewFrame extends JFrame {
	JPanel southPanel,panel,jpanel;		//�������
	JSplitPane centerPanel;	//�����ִ���
	JTextArea chatRecords,useronline;	//�����¼����������
	JTextField message;//������Ϣ
	JScrollPane leftpanel,rightpanel;//����
	JButton closeButton,sendButton;	//�رա�����
	JComboBox jcb1;//������
	
	User u1 = new User();
	UserService userservice=new UserServiceImpl();
	public String toname = new String();
	
	 NewFrame() throws Exception        //���캯��
	{
		
		 closeButton=new JButton("�ر�");		//������ť
		 sendButton=new JButton("����");
		
		 panel=new JPanel();
		 panel.setLayout(new BorderLayout());
		 jpanel=new JPanel();
		 jpanel.setLayout(new BorderLayout());
		 
		 useronline=new JTextArea();//����������ʾ
		 useronline.setLineWrap(true);
		 useronline.setEditable(false);
		 leftpanel=new JScrollPane(useronline);
		 leftpanel.setBorder(new TitledBorder("�����û�"));
		 panel.add(leftpanel,BorderLayout.EAST);
			
		 ///�����¼��ʾ	 
		 chatRecords=new JTextArea();	
		 chatRecords.setLineWrap(true);	//���ö����ı����Զ�����
		 chatRecords.setEditable(false);
		 rightpanel=new JScrollPane(chatRecords);	
		 rightpanel.setBorder(new TitledBorder("������Ϣ"));
		 centerPanel=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftpanel,rightpanel);
		 centerPanel.setDividerLocation(100);
		 panel.add(centerPanel,BorderLayout.CENTER);


		List list = new ArrayList<>();
		list = userservice.getAllUsers();

		String [] name= new String[list.size()+1];
		name[0] = "all";
		for(int i = 1;i<list.size()+1;i++){
			name[i] = ((User)list.get(i-1)).getUsername();
		}
		 jcb1=new JComboBox(name);	//����������
		 southPanel=new JPanel();//������Ϣ��ʾ
		 message=new JTextField(30);
		 southPanel.add(message);
		 southPanel.add(jcb1);
		 southPanel.add(sendButton);
		 southPanel.add(closeButton);


		 panel.add(southPanel,BorderLayout.SOUTH);
		 jpanel.add(panel,BorderLayout.CENTER);
		 this.add(jpanel);
		 this.setTitle("������");		//���ý������
		 this.setSize(640, 480);				//���ý�������
		 this.setLocation(300, 300);			//���ý����ʼλ��
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//����������ͽ���һͬ�ر�
		 this.setVisible(true);





		// �����Ŀѡ��״̬�ı�ļ�����
		jcb1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// ֻ����ѡ�е�״̬
				if (e.getStateChange() == ItemEvent.SELECTED) {
//					System.out.println("ѡ��: " + jcb1.getSelectedIndex() + " = " + jcb1.getSelectedItem());
					toname = (String) jcb1.getSelectedItem();
				}
			}
		});

		//�ر�
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);

				u1.setUsername(LoginOld.username);
				u1.setOnLine(0);

				try {
					userservice.updateUser(u1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dispose();
			}
		});
		//����
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserService userservice = new UserServiceImpl();
				String name = LoginOld.username;
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("MM-dd hh:mm:ss");
				String string = "��" + ft.format(dNow) + "��" + "\n" + name + "��";
				//�ύ��Ϣ
				Chat chat = new Chat(name, "", message.getText(),toname);
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
				int countonline_ed = 0;

				for (int j = 0; j < 10000000; j++) {
					//����
					ChatService chatservice1 = new ChatServiceImpl();
					Chat chat1 = new Chat();
					UserService userService1 = new UserServiceImpl();
					User user1 = new User();

					List chatlist = new ArrayList<>();
					try {
						chatlist = chatservice1.getAllChat();
					} catch (SQLException e) {
						e.printStackTrace();
					}

					List userlist = new ArrayList<>();
					try {
						userlist = userService1.getAllUsers();
					} catch (Exception e) {
						e.printStackTrace();
					}
					int countonline_now = 0;
					//�����û�
					for(int i = 0;i < userlist.size();i++){
						if(((User)userlist.get(i)).isOnLine() == 1){
							//��ʾ
							countonline_now ++;
						}
					}
//					System.out.println("ed " + countonline_ed);
//					System.out.println("now" + countonline_now);
					if(countonline_now != countonline_ed){
						useronline.setText(" ");
						for(int i = 0;i < userlist.size();i++){

							if(((User)userlist.get(i)).isOnLine() == 1){
								String line1 = "��" + ((User) userlist.get(i)).getUsername() + "��";
								useronline.append(line1 + "\n");

							}

//							message.setText(" ");
						}
					}

					countonline_ed = countonline_now;

					//������Ϣ
					if (chatlist.size() != 0 ) {
						for (int i = 0; i < chatlist.size(); i++) {
							chat1 = (Chat) chatlist.get(i);

							//�ж�ʱ������ �������� ͬ��
							if((((Chat) chatlist.get(i)).getChatId() > num)){
								//�ж����������Ƿ����Լ����
								//���ܱ��˷����Լ�����Ϣ �� �㲥
								if(((Chat) chatlist.get(i)).getToname().equals(LoginOld.username) || ((Chat) chatlist.get(i)).getToname().equals("all") || ((Chat) chatlist.get(i)).getName().equals(LoginOld.username)){
							    	//��ʾ
                                    String line1 = "��" + chat1.getTime() + "��\n" + chat1.getName() + " to " + chat1.getToname() + "�� " + chat1.getText();
                                    chatRecords.append(line1 + "\n");
                                    message.setText(" ");
                                }
							    //��ʾ�Լ��������˵���Ϣ
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

	public static void main(String[] args) throws Exception {
		new NewFrame();
	}
}







//				jb2.addActionListener(new ActionListener() {
//		            @Override
//		            public void actionPerformed(ActionEvent e) {
//		                System.out.println(jta2.getText());
//		            }
//		        });

			

