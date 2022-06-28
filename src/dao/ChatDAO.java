package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JdbcUtil;

public class ChatDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public int setSession(String userId, String sessionId) {
		int n = 0;
		String sql = 
				"INSERT INTO sessions (user_id, nickname, session_id) "
				+ "VALUES (?, ?, ?)";
		
		return n;
	}
	
	public String getUserNickname(String userId) {
		String nickname = "";
		String sql = "SELECT nickname FROM users WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				nickname = rs.getString("nickname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		
		return nickname;
	}
}
