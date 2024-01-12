package com.carclinic.car_clinic_auto_workshop.dao;

import com.carclinic.car_clinic_auto_workshop.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {

    public static <T>T execute(String sql, Object... objects) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i = 0; i < objects.length; i++ ){
            pstm.setObject((i+1), objects[i]);
        }

        if (sql.startsWith("SELECT")){
            return (T) pstm.executeQuery();
        }else {
            return (T)(Boolean)(pstm.executeUpdate()>0);
        }
    }
}
