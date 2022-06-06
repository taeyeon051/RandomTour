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
		String sql = "select * from users where user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
		String sql = "select * from users where nickname = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
	public int joinUser(UserVO vo) {
		int n = 0;
		String sql = "insert into users values (?, ?, PASSWORD(?))";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getNickname());
			pstmt.setString(3, vo.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
}
