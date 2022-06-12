package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JdbcUtil;
import vo.UserVO;

public class UserDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 아이디 중복 체크
	public boolean userIdCheck(String userId) {
		boolean check = false;
		String sql = "SELECT * FROM users WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return check;
	}

	// 닉네임 중복 체크
	public boolean nicknameCheck(String nickname) {
		boolean check = false;
		String sql = "SELECT * FROM users WHERE nickname = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return check;
	}
	
	// 회원가입
	public int userJoin(UserVO user) {
		int n = 0;
		String sql = "INSERT INTO users (user_id, user_name, nickname, password) VALUES (?, ?, ?, CONCAT('*', UPPER(SHA1(UNHEX(SHA1(?))))))";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getNickname());
			pstmt.setString(4, user.getPassword());
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
	
	// 로그인
	public UserVO userLogin(String userId, String password) {
		UserVO user = null;
		String sql = "SELECT * FROM users WHERE user_id = ? AND password = CONCAT('*', UPPER(SHA1(UNHEX(SHA1(?)))))";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int n = userLoginCheck(rs);
				if (n > 0) {
					user = new UserVO(
						rs.getString("user_id"),
						rs.getString("user_name"),
						rs.getString("nickname"),
						rs.getString("password"),
						rs.getBoolean("login_check"),
						rs.getInt("now_room")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return user;
	}
	
	// 로그인 체크
	public int userLoginCheck(ResultSet rs) {
		int n = 0;
		String sql = "UPDATE users SET login_check = ? WHERE user_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, rs.getString("user_id"));
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	
	// 로그아웃
	public int userLogout(UserVO user) {
		int n = 0;
		String sql = "UPDATE users SET login_check = ? WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, false);
			pstmt.setString(2, user.getUserId());
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
	
	// 아이디 찾기
	public String findUserId(String username, String nickname) {
		String userId = "";
		String sql = "SELECT user_id FROM users WHERE user_name = ? AND nickname = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userId = rs.getString("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return userId;
	}
}
