package test;

import com.google.gson.JsonObject;
import test.board.controller.BoardController;
import test.database.ConnectionPool;
import test.database.SQLExecutor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.util.logging.Logger;

import static test.common.Reader.*;
import static test.board.controller.Parsing.UrlParsing.jsonObject;

@WebListener
public class testListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(BoardController.class.getName());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            new ConnectionPool(readPropertiesFile(sce.getServletContext().getResourceAsStream("/WEB-INF/config/db/driver.properties")));
            jsonObject = readJsonFile(sce.getServletContext().getResourceAsStream("/WEB-INF/config/urlInfo.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            readClass(String.valueOf(ConnectionPool.class), "closeAllConnections");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
