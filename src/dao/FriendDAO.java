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
	UserDAO dao = new UserDAO();
	
	// 친구 목록
	public ArrayList<FriendVO> getFriendList(String userId) {
		ArrayList<FriendVO> list = new ArrayList<>();
		String nickname = dao.getUserNickname(userId);
		String sql = "SELECT * FROM friends WHERE state = ? AND (send_nickname = ? OR accept_nickname = ?) ORDER BY accept_date DESC";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, nickname);
			pstmt.setString(3, nickname);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FriendVO vo = new FriendVO(
					rs.getString("send_nickname"),
					rs.getString("accept_nickname"),
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
		String nickname = dao.getUserNickname(userId);
		String sql = "SELECT * FROM friends WHERE state = ? AND accept_nickname = ? ORDER BY send_date DESC";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, false);
			pstmt.setString(2, nickname);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FriendVO vo = new FriendVO(
					rs.getString("send_nickname"),
					rs.getString("accept_nickname"),
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
		String nickname = dao.getUserNickname(userId);
		String sql = "SELECT * FROM friends WHERE state = ? AND send_nickname = ? ORDER BY send_date DESC";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, false);
			pstmt.setString(2, nickname);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FriendVO vo = new FriendVO(
					rs.getString("send_nickname"),
					rs.getString("accept_nickname"),
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
		String sql = "INSERT INTO friends (send_nickname, accept_nickname, state, send_date) VALUES (?, ?, ?, NOW())";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getSendNickname());
			pstmt.setString(2, vo.getAcceptNickname());
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
		String sql = "UPDATE friends SET state = ?, accept_date = NOW() WHERE send_nickname = ? AND accept_nickname = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, vo.getSendNickname());
			pstmt.setString(3, vo.getAcceptNickname());
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
		String sql = "DELETE FROM friends WHERE send_nickname = ? AND accept_nickname = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getSendNickname());
			pstmt.setString(2, vo.getAcceptNickname());
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
		String sendNickname = dao.getUserNickname(userId);
		String sql = "SELECT * FROM friends WHERE (send_nickname = ? AND accept_nickname = ?) OR (send_nickname = ? AND accept_nickname = ?)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sendNickname);
			pstmt.setString(2, nickname);
			pstmt.setString(3, nickname);
			pstmt.setString(4, sendNickname);			
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
}
