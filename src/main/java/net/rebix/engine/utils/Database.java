package net.rebix.engine.utils;

import java.sql.*;

public class Database {
    Connection connection;

    String host = "localhost";
    String port = "3306";
    String database = "database";
    String username = "root";
    String password = "password";

    public Database(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;

        connect();
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MySQL disconnected!");
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasConnection() {
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getStringByString(String table, String key, String keycolumn, String valuecolumn) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT " + valuecolumn + " FROM " + table + " WHERE " + keycolumn + " = ?;"
        )) {
            stmt.setString(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String result = resultSet.getString(valuecolumn);
                stmt.close();
                return result;
            }
            System.out.println("No result found for " + key + " in " + table);
            stmt.close();
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean setStringByString(String table, String key, String keycolumn, String valuecolumn, String value) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "UPDATE " + table + " SET " + valuecolumn + " = ? WHERE " + keycolumn + " = ?"
        )) {
            stmt.setString(1, value);
            stmt.setString(2, key);
            System.out.println(key + " was set to " + value + " in " + table);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addLine(String table, String columnname, String columnvalue) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO " + table + "(" + columnname + ") select '" + columnvalue + "'"
        )) {


            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
