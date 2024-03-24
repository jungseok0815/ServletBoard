package test.database;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static test.common.Parsing.underscoreToCamelCase;

public class SQLExecutor {

    /**
     * insert, update, delete를 처리해주는 매소드
     * @param conn
     * @param query
     * @return
     */
    static public int executeUpdate(Connection conn,String query) {
        Integer result = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplate.close(pstmt);
        }
        return result;
    }

    /**
     * 검색 조회 결과가 list로 반환되는 경우
     * @param conn
     * @param clazz
     * @param query
     * @param <T>
     * @return T list
     */
    public static <T> List<T> selectList(Connection conn,Class<T> clazz, String query) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        ResultSet rset = null;
        PreparedStatement pstmt = null;
        ArrayList<T> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(query);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                T obj = clazz.newInstance();
                for (int i = 1; i <= rset.getMetaData().getColumnCount(); i++) {
                    Object columnValue = rset.getObject(rset.getMetaData().getColumnName(i));
                    Field field = clazz.getDeclaredField(underscoreToCamelCase(rset.getMetaData().getColumnName(i))); // 필드 이름으로 필드 가져오기
                    field.setAccessible(true); // private 필드에 접근 가능하도록 설정
                    field.set(obj, columnValue); // 필드에 값 설정
                    if (i == rset.getMetaData().getColumnCount()) list.add(obj); //인스턴스에 담긴 값 리스트에 전달
                }
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }  finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }
    }

    /**
     * 검색 결과 값이 하나인 경우
     * @param conn
     * @param clazz
     * @param query
     * @param <T>
     * @return T obj
     */
    public static <T> T selectOne(Connection conn,Class<T> clazz, String query) throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        ResultSet rset = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            T obj = clazz.newInstance();
            rset = pstmt.executeQuery();
            if (rset.next()) {
                for (int i = 1; i <= rset.getMetaData().getColumnCount(); i++) {
                    Object columnValue = rset.getObject(rset.getMetaData().getColumnName(i)); // 열의 값 가져오기
                    Field field = clazz.getDeclaredField(underscoreToCamelCase(rset.getMetaData().getColumnName(i))); // 필드 이름으로 필드 가져오기
                    field.setAccessible(true); // private 필드에 접근 가능하도록 설정
                    field.set(obj, columnValue); // 필드에 값 설정
                }
            }
            return obj;
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }
    }

    /**
     * query를 보고 데이터를 넣는 곳에 맞는 매칠 값을 찾아서 주입
     * @param sql
     * @param reqData
     * @return qyery
     * @throws SQLException
     */
    static public String checkParams(String sql, Map<String,String> reqData) throws SQLException {
        Pattern pattern = Pattern.compile("#\\{(.+?)\\}");
        Matcher matcher = pattern.matcher(sql);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String value = reqData.get(matcher.group(1));
            matcher.appendReplacement(result, value);
        }
        matcher.appendTail(result);
        return String.valueOf(result);
    }

}

