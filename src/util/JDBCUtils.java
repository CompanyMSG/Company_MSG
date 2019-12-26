package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * jdbc������
 * 1.���ݿ����ӵĻ�ȡ
 * 2.���ݿ���Դ�Ĺر�
 * 3.insert/update/delete�����ķ�װ
 * 4.select�����ķ�װ
 *
 */
public class JDBCUtils {
    private static String driverName = "com.mysql.jdbc.Driver";// ���ݿ������ַ���
    private static String url = "jdbc:mysql://106.13.78.17:3306/test";// ����URL�ַ���
    private static String username = "root"; // ���ݿ��û���
    private static String password = "1154162893"; // �û�����

	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	//ִ��һ��
	static{
		
		try {
			Class.forName(driverName);
			
		} catch (ClassNotFoundException e) {
			System.out.println("��������ʧ�ܣ����mysql�������Ƿ����");
		}
	}
	/**
	 * ��ȡ���ݿ�����
	 * @return conn
	 */
	public static Connection getConnection(){
		try {
			if(conn == null || conn.isClosed()){
				conn = DriverManager.getConnection(url, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * �ر����ݿ�����
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
		try {
			if(rs != null){rs.close();}
			if(pstmt != null){pstmt.close();}
			if(conn != null){conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �رղ�ѯ�����ݿ�����
	 *
	 */
	public static void closeAll(){
		try {
			if(rs != null){rs.close();}
			if(pstmt != null){pstmt.close();}
			if(conn != null){conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ����insert/update/delete����
	 * @param sql
	 * @param params
	 * @return  num
	 */
	public static int executeUpdate(String sql,Object... params){
		int num = 0;
		try {
			conn = getConnection();
			//��ȡԤ�������
			pstmt =conn.prepareStatement(sql);
			//������
			if(params != null){
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			//ִ��sql
			num = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	/**
	 * ����select����
	 */
	public static ResultSet executeQuery(String sql,Object... params){
		try{
			//1.��ȡ����
			conn = getConnection();
			//2.Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			//3.������
			if(params != null){
				for(int i=0;i<params.length;i++){
					pstmt.setObject((i+1), params[i]);
				}
			}
			//4.ִ��sql
			rs = pstmt.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
}
