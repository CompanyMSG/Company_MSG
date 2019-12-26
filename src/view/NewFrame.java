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

//新窗口
public class NewFrame extends JFrame {
	JPanel jp1;        //定义面板
	JSplitPane jsp;    //定义拆分窗格
	JTextArea jta1;    //定义文本域
	JScrollPane jspane1;    //定义滚动窗格
	JTextArea jta2;
	JScrollPane jspane2;
	JPanel jp2;
	JButton jb1, jb2;    //定义按钮
	JComboBox jcb1;        //定义下拉框
	//User
	User u1 = new User();
	UserService userservice=new UserServiceImpl();
	public String toname = new String();

	public NewFrame() throws Exception {
		//创建组件
		//上部组件
		jp1 = new JPanel();    //创建面板
		jta1 = new JTextArea();    //创建多行文本框
		jta1.setLineWrap(true);    //设置多行文本框自动换行
		jspane1 = new JScrollPane(jta1);    //创建滚动窗格
		String line = jta1.getText();

		jta2 = new JTextArea();
		jta2.setLineWrap(true);

		jspane2 = new JScrollPane(jta2);
		jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jspane1, jspane2); //创建拆分窗格
		jsp.setDividerLocation(200);    //设置拆分窗格分频器初始位置
		jsp.setDividerSize(1);            //设置分频器大小
		//下部组件
		jp2 = new JPanel();
		jb1 = new JButton("关闭");        //创建按钮
		jb2 = new JButton("发送");

		List list = new ArrayList<>();
		list = userservice.getAllUsers();

		String [] name= new String[list.size()+1];
		name[0] = "all";
		for(int i = 1;i<list.size();i++){
			name[i] = ((User)list.get(i-1)).getUsername();
		}

		jcb1=new JComboBox(name);	//创建下拉框

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


		//设置布局管理
		jp1.setLayout(new BorderLayout());    //设置面板布局
		jp2.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//添加组件
		jp1.add(jsp);
		jp2.add(jcb1);
		jp2.add(jb1);
		jp2.add(jb2);

		this.add(jp1, BorderLayout.CENTER);
		this.add(jp2, BorderLayout.SOUTH);

		//设置窗体实行
		this.setTitle("聊天界面");        //设置界面标题
		this.setSize(400, 350);                //设置界面像素
		this.setLocation(300, 300);            //设置界面初始位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置虚拟机和界面一同关闭
		this.setVisible(true);                //设置界面可视化

		//关闭
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);

				u1.setUsername(LoginOld.username);
				u1.setOnLine(false);

				try {
					userservice.updateUser(u1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dispose();
			}
		});
		//发送
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserService userservice = new UserServiceImpl();
				String name = LoginOld.username;
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("MM-dd hh:mm:ss");
				String string = "【" + ft.format(dNow) + "】" + "\n" + name + "：";
				//提交信息
				Chat chat = new Chat(name, "", jta2.getText(),toname);
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
				for (int j = 0; j < 10000000; j++) {
					//接受信息
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

							//判断时间序列 新增数据 同步
							if((((Chat) list.get(i)).getChatId() > num)){
								//判断新增数据是否与自己相关
								//接受别人发给自己的消息 和 广播
								System.out.println(num);
								System.out.println(((Chat) list.get(i)).getToname().equals(LoginOld.username));
								System.out.println(((Chat) list.get(i)).getToname().equals("all"));
								System.out.println(((Chat) list.get(i)).getName().equals(LoginOld.username));

								if(((Chat) list.get(i)).getToname().equals(LoginOld.username) || ((Chat) list.get(i)).getToname().equals("all") || ((Chat) list.get(i)).getName().equals(LoginOld.username)){
							    	//显示
                                    String line1 = "【" + chat1.getTime() + "】\n" + chat1.getName() + " to " + chat1.getToname() + "： " + chat1.getText();
                                    jta1.append(line1 + "\n");
                                    jta2.setText(" ");
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

			

