package test.common.exception;

import java.sql.SQLException;

public class CustomSqlExc extends SQLException {
    public CustomSqlExc(String message){
        super(message);
    }


}

