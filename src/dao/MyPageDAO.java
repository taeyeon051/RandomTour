package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.JdbcUtil;
import vo.ChattingVO;
import vo.InquiryVO;

public class MyPageDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	FriendDAO friendDao = new FriendDAO();
	
	// 문의하기 내용 DB 저장
	public int sendInquiry(InquiryVO vo) {
		int n = 0;
		String sql = "INSERT INTO inquiries VALUES (NULL, ?, ?, ?, ?)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getSelect());
			pstmt.setString(4, vo.getContent());
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
	
	// 문의하기 목록 가져오기
	public ArrayList<InquiryVO> getInquiryList() {
		ArrayList<InquiryVO> list = new ArrayList<>();
		String sql = "SELECT `id`, `user_id`, `title`, `select` FROM inquiries ORDER BY id DESC";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				InquiryVO vo = new InquiryVO();
				vo.setId(rs.getInt("id"));
				vo.setUserId(rs.getString("user_id"));
				vo.setTitle(rs.getString("title"));
				vo.setSelect(rs.getString("select"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return list;
	}
	
	// 문의하기 내용 하나만 가져오기
	public InquiryVO getInquiry(int id) {
		InquiryVO vo = null;
		String sql = "SELECT * FROM inquiries WHERE id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new InquiryVO(
					rs.getInt("id"),
					rs.getString("user_id"),
					rs.getString("title"),
					rs.getString("select"),
					rs.getString("content")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return vo;
	}
	
	public ArrayList<ChattingVO> getChattingList(String userId) {
		ArrayList<ChattingVO> list = new ArrayList<>();
		String sql = "SELECT * FROM chattings WHERE send_user_id = ? OR accept_user_id = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ChattingVO vo = new ChattingVO(
					rs.getString("send_user_id"),
					rs.getString("accept_user_id"),
					"",
					rs.getDate("send_date"),
					null
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
	
	public int insertChat(ChattingVO vo) {
		int n = 0;
		if (chatCheck(vo)) return 1;
		String sql = "INSERT INTO chattings VALUES (?, ?, ?, NOW(), null)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getSendUserId());
			pstmt.setString(2, vo.getAcceptUserId());
			pstmt.setString(3, vo.getChatting());
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt);
		}
		return n;
	}
	
	public boolean chatCheck(ChattingVO vo) {
		boolean check = false;
		String sql = "SELECT * FROM chattings WHERE (send_user_id = ? AND accept_user_id = ?) OR (send_user_id = ? AND accept_user_id = ?)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getSendUserId());
			pstmt.setString(2, vo.getAcceptUserId());
			pstmt.setString(3, vo.getAcceptUserId());
			pstmt.setString(4, vo.getSendUserId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				check = true;
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return check;
	}
	
	public ArrayList<ChattingVO> getChatting(String userId, String nickname) {
		ArrayList<ChattingVO> list = new ArrayList<>();
		String friendId = friendDao.getUserId(nickname);
		String sql = "SELECT * FROM chattings WHERE (send_user_id = ? AND accept_user_id = ?) OR (send_user_id = ? AND accept_user_id = ?)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, friendId);
			pstmt.setString(3, friendId);
			pstmt.setString(4, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ChattingVO vo = new ChattingVO(
					rs.getString("send_user_id"),
					rs.getString("accept_user_id"),
					rs.getString("chat"),
					rs.getDate("send_date"),
					rs.getDate("accept_date")
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
}
