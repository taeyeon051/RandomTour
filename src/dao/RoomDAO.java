package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JdbcUtil;
import vo.RoomVO;

public class RoomDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public boolean userRoomCheck(String userId) {
		boolean check = false;
		String sql = "SELECT now_room FROM users WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String nowRoom = rs.getString("now_room");
				System.out.println(nowRoom);
				if (nowRoom != null) check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return check;
	}
	
	public int createRoom(RoomVO vo) {
		int n = 0;
		String sql = "INSERT INTO rooms VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getRoomId());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getUserId());
			pstmt.setInt(4, 1);
			pstmt.setBoolean(5, false);
			pstmt.setBoolean(6, false);
			pstmt.setString(7, "korea");
			n = pstmt.executeUpdate();
			if (n > 0) n = userRoomUpdate(vo.getRoomId(), vo.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
	
	public int userRoomUpdate(int roomId, String userId) {
		int n = 0;
		String sql = "UPDATE users SET now_room = ? WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, userId);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
}
