package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JdbcUtil;
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
		String sql = "INSERT INTO users (user_id, nickname, password) VALUES (?, ?, CONCAT('*', UPPER(SHA1(UNHEX(SHA1(?))))))";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getNickname());
			pstmt.setString(3, user.getPassword());
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
				sql = "UPDATE users SET login_check = ? WHERE id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setBoolean(1, true);
				pstmt.setInt(2, rs.getInt("id"));
				int n = pstmt.executeUpdate();
				if (n > 0) {
					user = new UserVO(
						rs.getInt("id"),
						rs.getString("user_id"),
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
	
	
	// 로그아웃
	public int userLogout(UserVO user) {
		int n = 0;
		String sql = "UPDATE users SET login_check = ? WHERE id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, false);
			pstmt.setInt(2, user.getId());
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
}
