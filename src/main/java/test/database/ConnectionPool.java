package test.database;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private int maxConnections = 20;
    private static List<Connection> connections = null;

    public ConnectionPool(Properties dbDriver) {
        initializeConnections(dbDriver);
    }

    /**
     * 케넥션풀 생성
     * @param dbDriver
     */
    private void initializeConnections(Properties dbDriver) {
        if (connections != null) return;
        connections = new ArrayList<>(maxConnections);
        try {
            for (int i = 0; i < maxConnections; i++) {
                Class.forName(dbDriver.getProperty("driver"));
                Connection connection = DriverManager.getConnection(dbDriver.getProperty("url")
                        ,dbDriver.getProperty("username")
                        ,dbDriver.getProperty("password"));
                connection.setAutoCommit(false);
                connections.add(connection);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 커넥션풀 사용
     * @return
     * @throws SQLException
     */
    public static synchronized Connection getConnetion() throws SQLException {
        if(connections.isEmpty()) throw new SQLException("Connection pool exhausted");
        return connections.remove(connections.size() - 1);
    }

    /**
     * 커넥션 반납
     * @param connection
     */
    public static synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
    }

    /**
     * 케넥션풀 닫기
     * @throws SQLException
     */
    public void closeAllConnections() throws SQLException {
        for (Connection connection : connections) {
            connection.close();
        }
        connections.clear();
    }
}
