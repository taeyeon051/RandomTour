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
	public boolean nicknameCheck(String nickname, String userId) {
		boolean check = false;
		boolean update = userId != null && !userId.equals("");
		String sql = "SELECT * FROM users WHERE nickname = ?";
		if (update) sql += " AND user_id != ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			if (update) pstmt.setString(2, userId);
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
	
	// 인증번호 설정
	public int setCertifyNumber(String userId, String number) {
		int n = 0;
		String sql = "";
		
		String check = certify(userId);
		if (check.equals("")) {
			sql = "INSERT INTO certifies (certify_number, user_id) VALUES (?, ?)";
		} else {
			sql = "UPDATE certifies SET certify_number = ? WHERE user_id = ?";
		}
		
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, number);
			pstmt.setString(2, userId);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
	
	// 인증번호 확인
	public String certify(String userId) {
		String number = "";
		String sql = "SELECT certify_number FROM certifies WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				number = rs.getString("certify_number");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return number;
	}
	
	// 인증번호 삭제
	public int deleteCertify(String userId) {
		int n = 0;
		String sql = "DELETE FROM certifies WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
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
	
	// 로그인 확인
	public boolean loginCheck(String userId) {
		boolean loginCheck = false;
		String sql = "SELECT login_check FROM users WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginCheck = rs.getBoolean("login_check");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginCheck;
	}
	
	// 로그인 체크 표시
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
	public int userLogout(String userId) {
		int n = 0;
		String sql = "UPDATE users SET login_check = ? WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, false);
			pstmt.setString(2, userId);
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
	
	// 비밀번호 변경
	public int updatePwd(String userId, String password) {
		int n = 0;
		String sql = "UPDATE users SET password = CONCAT('*', UPPER(SHA1(UNHEX(SHA1(?))))) WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, userId);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
	
	// 유정 정보 수정
	public int userUpdate(UserVO user) {
		int n = 0;
		String sql = "UPDATE users SET user_name = ?, nickname = ?, password = CONCAT('*', UPPER(SHA1(UNHEX(SHA1(?))))) WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getNickname());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getUserId());
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
}
