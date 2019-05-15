package com.cafe24.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.guestbook.vo.GuestbookVo;


public class GuestbookDao {

	private Connection getConnection() throws SQLException {

		Connection conn = null;
		// 1. JDBC Driver(MariaDB) 로딩
		// 직접 코드로 메소드Area에 로딩할 때
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.41:3307/webdb";
			// DriverManager.getConnection을 하면 Connection 인터페이스의 conn이 구현된다.
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}

		return conn;
	}

	public List<GuestbookVo> getList() {
		List<GuestbookVo> result = new ArrayList<GuestbookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. statement 객체 생성
			String sql = "select no, name, contents, date_format(reg_date, '%Y-%m-%d %h:%i:%s') from guestbook order by reg_date;";
			pstmt = conn.prepareStatement(sql);

			// 4. SQL문 실행
			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			// rs 값 중 다음 값이 없다면 false이므로 while문이 멈춘다.
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);

				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setRegDate(regDate);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					// 연결 종료
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	// category의 정보를 insert하는 메소드
		public Boolean insert(GuestbookVo vo) {
			// 사용자의 정보가 제대로 업데이트가 된 경우 result = true로 메소드 종료 아니면 SQLException 오류 발생
			boolean result = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				// connection을 위한 클래스를 별도로 작성하여 conn 변수에 넣어줌
				conn = getConnection();
				String sql = "insert into guestbook values(null, ?,?,?,now());";
				pstmt = conn.prepareStatement(sql);
				// SQL의 ?를 받기 위한 바인딩 작업
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getContents());
				// 준비된 내용을 실행하여 데이터베이스 업데이트 진행
				int count = pstmt.executeUpdate();
				result = (count == 1);
			} catch (SQLException e) {
				System.out.println("error" + e);
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						// 연결 종료
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		public Boolean delete(GuestbookVo vo) {
			// 사용자의 정보가 제대로 업데이트가 된 경우 result = true로 메소드 종료 아니면 SQLException 오류 발생
			boolean result = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				// connection을 위한 클래스를 별도로 작성하여 conn 변수에 넣어줌
				conn = getConnection();
				String sql = "delete from guestbook where no=? and name = ?";
				pstmt = conn.prepareStatement(sql);
				// SQL의 ?를 받기 위한 바인딩 작업
				pstmt.setLong(1, vo.getNo());
				pstmt.setString(1, vo.getName());
				// 준비된 내용을 실행하여 데이터베이스 업데이트 진행
				int count = pstmt.executeUpdate();
				result = (count == 1);
			} catch (SQLException e) {
				System.out.println("error" + e);
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						// 연결 종료
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
	
}
