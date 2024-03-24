package test.board.controller;

import test.board.controller.Parsing.UrlParsing;
import test.board.controller.mapping.UrlMapping;
import test.common.exception.CustomException;
import test.database.ConnectionPool;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Logger;

import static test.board.model.repository.BoardRepository.sqlProp;
import static test.common.Reader.*;


@WebServlet("*.do")
public class BoardController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(BoardController.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            sqlProp =  readXmlFile(config.getServletContext().getResourceAsStream("/WEB-INF/config/db/sql/board.xml"));
        } catch (ParserConfigurationException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            UrlParsing urlParsing  = new UrlParsing(req,res);
            new UrlMapping().mappingUrl(req,res,urlParsing);
        }catch (CustomException |ServletException |
                IOException |NoSuchMethodException
                |ClassNotFoundException |InvocationTargetException
                |InstantiationException|IllegalAccessException e){
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }
}