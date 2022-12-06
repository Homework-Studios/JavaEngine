package net.rebix.engine.utils;

import com.google.gson.JsonObject;

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
        if(!hasConnection()) {
            connect();
        }
        return connection;
    }

    public String getStringByString(String table, String key, String keyColumn, String valueColumn) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT " + valueColumn + " FROM " + table + " WHERE " + keyColumn + " = ?;"
        )) {
            stmt.setString(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String result = resultSet.getString(valueColumn);
                return result;
            }
            stmt.close();
            return "N/A";
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public JsonObject getJsonByString(String table, String key, String keyColumn, String valueColumn) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT " + valueColumn + " FROM " + table + " WHERE " + keyColumn + " = ?;"
        )) {
            stmt.setString(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                JsonObject result = new JsonObject();
                result.addProperty("result", resultSet.getString(valueColumn));
                return result;
            }
            return new JsonObject();
        } catch (SQLException e) {
            e.printStackTrace();
            return new JsonObject();
        }
    }

    public void setJsonByString(String table, String key, String keyColumn, JsonObject value, String valueColumn) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "UPDATE " + table + " SET " + valueColumn + " = ? WHERE " + keyColumn + " = ?;"
        )) {
            stmt.setString(1, value.toString());
            stmt.setString(2, key);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean setStringByString(String table, String key, String keyColumn, String value, String valueColumn) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "UPDATE " + table + " SET " + valueColumn + " = ? WHERE " + keyColumn + " = ?"
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

    public boolean addLine(String table, String Columnname, String Columnvalue) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO " + table + "(" + Columnname + ") select '" + Columnvalue + "'"
        )) {
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
