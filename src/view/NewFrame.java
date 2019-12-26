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

//新窗口
public class NewFrame extends JFrame {
	JPanel southPanel,panel,jpanel;		//定义面板
	JSplitPane centerPanel;	//定义拆分窗格
	JTextArea chatRecords,useronline;	//聊天记录框、在线人数
	JTextField message;//发送消息
	JScrollPane leftpanel,rightpanel;//左右
	JButton closeButton,sendButton;	//关闭、发送
	JComboBox jcb1;//下拉框
	
	User u1 = new User();
	UserService userservice=new UserServiceImpl();
	public String toname = new String();
	
	 NewFrame() throws Exception        //构造函数
	{
		
		 closeButton=new JButton("关闭");		//创建按钮
		 sendButton=new JButton("发送");
		
		 panel=new JPanel();
		 panel.setLayout(new BorderLayout());
		 jpanel=new JPanel();
		 jpanel.setLayout(new BorderLayout());
		 
		 useronline=new JTextArea();//在线人数显示
		 useronline.setLineWrap(true);
		 useronline.setEditable(false);
		 leftpanel=new JScrollPane(useronline);
		 leftpanel.setBorder(new TitledBorder("在线用户"));
		 panel.add(leftpanel,BorderLayout.EAST);
			
		 ///聊天记录显示	 
		 chatRecords=new JTextArea();	
		 chatRecords.setLineWrap(true);	//设置多行文本框自动换行
		 chatRecords.setEditable(false);
		 rightpanel=new JScrollPane(chatRecords);	
		 rightpanel.setBorder(new TitledBorder("聊天信息"));
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
		 jcb1=new JComboBox(name);	//创建下拉框
		 southPanel=new JPanel();//发送消息显示
		 message=new JTextField(30);
		 southPanel.add(message);
		 southPanel.add(jcb1);
		 southPanel.add(sendButton);
		 southPanel.add(closeButton);


		 panel.add(southPanel,BorderLayout.SOUTH);
		 jpanel.add(panel,BorderLayout.CENTER);
		 this.add(jpanel);
		 this.setTitle("聊天室");		//设置界面标题
		 this.setSize(640, 480);				//设置界面像素
		 this.setLocation(300, 300);			//设置界面初始位置
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//设置虚拟机和界面一同关闭
		 this.setVisible(true);





		// 添加条目选中状态改变的监听器
		jcb1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// 只处理选中的状态
				if (e.getStateChange() == ItemEvent.SELECTED) {
//					System.out.println("选中: " + jcb1.getSelectedIndex() + " = " + jcb1.getSelectedItem());
					toname = (String) jcb1.getSelectedItem();
				}
			}
		});

		//关闭
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
		//发送
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserService userservice = new UserServiceImpl();
				String name = LoginOld.username;
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("MM-dd hh:mm:ss");
				String string = "【" + ft.format(dNow) + "】" + "\n" + name + "：";
				//提交信息
				Chat chat = new Chat(name, "", message.getText(),toname);
				ChatService chatservice = new ChatServiceImpl();
				try {
					System.out.println(chatservice.addChat(chat));
				} catch (SQLException e) {
					e.printStackTrace();
				}
//				//窗口显示
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
					//接收
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
					//接收用户
					for(int i = 0;i < userlist.size();i++){
						if(((User)userlist.get(i)).isOnLine() == 1){
							//显示
							countonline_now ++;
						}
					}
//					System.out.println("ed " + countonline_ed);
//					System.out.println("now" + countonline_now);
					if(countonline_now != countonline_ed){
						useronline.setText(" ");
						for(int i = 0;i < userlist.size();i++){

							if(((User)userlist.get(i)).isOnLine() == 1){
								String line1 = "【" + ((User) userlist.get(i)).getUsername() + "】";
								useronline.append(line1 + "\n");

							}

//							message.setText(" ");
						}
					}

					countonline_ed = countonline_now;

					//接收信息
					if (chatlist.size() != 0 ) {
						for (int i = 0; i < chatlist.size(); i++) {
							chat1 = (Chat) chatlist.get(i);

							//判断时间序列 新增数据 同步
							if((((Chat) chatlist.get(i)).getChatId() > num)){
								//判断新增数据是否与自己相关
								//接受别人发给自己的消息 和 广播
								if(((Chat) chatlist.get(i)).getToname().equals(LoginOld.username) || ((Chat) chatlist.get(i)).getToname().equals("all") || ((Chat) chatlist.get(i)).getName().equals(LoginOld.username)){
							    	//显示
                                    String line1 = "【" + chat1.getTime() + "】\n" + chat1.getName() + " to " + chat1.getToname() + "： " + chat1.getText();
                                    chatRecords.append(line1 + "\n");
                                    message.setText(" ");
                                }
							    //显示自己发给别人的消息
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

			

