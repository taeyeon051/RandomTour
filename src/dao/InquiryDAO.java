package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.JdbcUtil;
import vo.InquiryVO;

public class InquiryDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
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
}
