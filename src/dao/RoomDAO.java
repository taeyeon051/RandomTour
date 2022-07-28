package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.JdbcUtil;
import vo.RoomVO;

public class RoomDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public boolean privatecheck(String roomId) {
		boolean check = false;
		String sql = "SELECT private FROM rooms WHERE room_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roomId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				check = rs.getBoolean("private");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return check;
	}
	
	public int createRoom(RoomVO vo) {
		int n = 0;
		String sql = "INSERT INTO rooms (room_id, room_title, user_id) VALUES (?, ?, ?)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getRoomId());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getUserId());
			n = pstmt.executeUpdate();
			if (n > 0) n = userRoomUpdate(vo.getRoomId(), vo.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
	
	public int userRoomUpdate(String roomId, String userId) {
		int n = 0;
		String sql = "UPDATE users SET now_room = ? WHERE user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roomId);
			pstmt.setString(2, userId);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
	
	public ArrayList<RoomVO> getRoomList() {
		ArrayList<RoomVO> list = new ArrayList<>();
		String sql = "SELECT * FROM rooms";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RoomVO vo = new RoomVO(
					rs.getString("room_id"),
					rs.getString("room_title"),
					rs.getString("user_id"),
					rs.getInt("personnel"),
					rs.getBoolean("private"),
					rs.getString("password"),
					rs.getBoolean("state"),
					rs.getString("map")
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

	public RoomVO getRoomData(String roomId) {
		RoomVO vo = null;
		String sql = "SELECT * FROM rooms WHERE room_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roomId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new RoomVO(
					rs.getString("room_id"),
					rs.getString("room_title"),
					rs.getString("user_id"),
					rs.getInt("personnel"),
					rs.getBoolean("private"),
					rs.getString("password"),
					rs.getBoolean("state"),
					rs.getString("map")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return vo;
	}
}
