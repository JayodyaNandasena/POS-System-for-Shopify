package edu.shopify.util;

import edu.shopify.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement prStm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            prStm.setObject((i+1),args[i]);
        }
        if (sql.toUpperCase().startsWith("SELECT")){
            return (T) prStm.executeQuery();
        }
        return (T) (Boolean) (prStm.executeUpdate()>0);
    }
}
