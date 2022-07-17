package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.JdbcUtil;
import vo.FriendVO;

public class FriendDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		
	// 닉네임으로 아이디 찾기
	public String getUserId(String nickname) {
		String userId = "";
		String sql = "SELECT user_id FROM users WHERE nickname = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
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

	// 아이디로 닉네임 찾기
	public String getNickname(String userId) {
		String nickname = "";
		String sql = "SELECT nickname FROM users WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
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

	// 친구 목록
	public ArrayList<FriendVO> getFriendList(String userId) {
		ArrayList<FriendVO> list = new ArrayList<>();
		String sql = "SELECT * FROM friends WHERE state = ? AND (send_user_id = ? OR accept_user_id = ?) ORDER BY accept_date DESC";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, userId);
			pstmt.setString(3, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FriendVO vo = new FriendVO(
					rs.getString("send_user_id"),
					rs.getString("accept_user_id"),
					rs.getBoolean("state")
				);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return list;
	}
	
	// 받은 요청 목록
	public ArrayList<FriendVO> getFriendRequestList(String userId) {
		ArrayList<FriendVO> list = new ArrayList<>();
		String sql = "SELECT * FROM friends WHERE state = ? AND accept_user_id = ? ORDER BY send_date DESC";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, false);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FriendVO vo = new FriendVO(
					rs.getString("send_user_id"),
					rs.getString("accept_user_id"),
					rs.getBoolean("state")
				);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return list;
	}
	
	// 요청 후 대기중인 목록
	public ArrayList<FriendVO> getFriendSendList(String userId) {
		ArrayList<FriendVO> list = new ArrayList<>();
		String sql = "SELECT * FROM friends WHERE state = ? AND send_user_id = ? ORDER BY send_date DESC";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, false);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FriendVO vo = new FriendVO(
					rs.getString("send_user_id"),
					rs.getString("accept_user_id"),
					rs.getBoolean("state")
				);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return list;
	}
	
	// 친구 요청 전송
	public int sendFriend(FriendVO vo) {
		int n = 0;
		String sql = "INSERT INTO friends VALUES (?, ?, ?, NOW())";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getSendUserId());
			pstmt.setString(2, vo.getAcceptUserId());
			pstmt.setBoolean(3, false);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
	
	// 친구 요청 수락
	public int acceptFriend(FriendVO vo) {
		int n = 0;
		String sql = "UPDATE friends SET state = ?, accept_date = NOW() WHERE send_user_id = ? AND accept_user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, vo.getSendUserId());
			pstmt.setString(3, vo.getAcceptUserId());
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
	
	// 친구 요청 거절, 친구 삭제
	public int deleteFriend(FriendVO vo) {
		int n = 0;
		String sql = "DELETE FROM friends WHERE (send_user_id = ? AND accept_user_id = ?) OR (send_user_id = ? AND accept_user_id = ?)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getSendUserId());
			pstmt.setString(2, vo.getAcceptUserId());
			pstmt.setString(3, vo.getAcceptUserId());
			pstmt.setString(4, vo.getSendUserId());
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
	
	// 이미 친구추가 되어있는지 확인
	public boolean friendCheck(String userId, String nickname) {
		boolean check = false;
		String friendId = getUserId(nickname);
		String sql = "SELECT * FROM friends WHERE (send_user_id = ? AND accept_user_id = ?) OR (send_user_id = ? AND accept_user_id = ?)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, friendId);
			pstmt.setString(3, friendId);
			pstmt.setString(4, userId);
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
	
	// 문의하기 내용 DB 저장
	public int sendInquiry(String userId, String title, String select, String content) {
		int n = 0;
		String sql = "INSERT INTO inquiries VALUES (NULL, ?, ?, ?, ?)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, title);
			pstmt.setString(3, select);
			pstmt.setString(4, content);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
}
