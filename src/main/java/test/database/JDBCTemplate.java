package test.database;

import java.sql.*;

public class JDBCTemplate {


    /**
     * Connection commit
     * @param conn
     */
    public static void commit(Connection conn) {
        try {
            if(conn!= null && !conn.isClosed()) {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connection rollback
     * @param conn
     */
    public static void rollback(Connection conn) {
        try {
            if(conn!= null && !conn.isClosed()) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Statement 닫기
     * @param stmt
     */
    public static void close(Statement stmt) {
        try {
            if(stmt!= null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * PreparedStatement 닫기
     * @param pstmt
     */
    public static void close(PreparedStatement pstmt) {
        try {
            if(pstmt!= null && !pstmt.isClosed()) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ResultSet 닫기
     * @param rset
     */
    public static void close(ResultSet rset) {
        try {
            if(rset!= null && !rset.isClosed()) {
                rset.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}