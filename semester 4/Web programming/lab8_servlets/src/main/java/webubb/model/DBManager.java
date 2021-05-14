package webubb.model;

import webubb.domain.City;
import webubb.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBManager {
    private Statement stmt;

    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cities", "root", "password");
            stmt = con.createStatement();
        } catch(Exception ex) {
            System.out.println("eroare la connect:"+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public User authenticate(String username, String password) {
        ResultSet rs;
        User u = null;
        System.out.println(username+" "+password);
        try {
            rs = stmt.executeQuery("select * from Users where username='"+username+"' and password='"+password+"'");
            if (rs.next()) {
                u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public User getCurrentUser(int userid) {
        ResultSet rs;
        User u = null;
        try {
            rs = stmt.executeQuery("select * from Users where id="+userid);
            if (rs.next()) {
                u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public ArrayList<City> getAllCities(int userid) {
        ArrayList<City> cities = new ArrayList<City>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select * from cities");
            while (rs.next()) {
                cities.add(new City(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public ArrayList<City> getNeighbours(int cityid) {
        ArrayList<City> cities = new ArrayList<City>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * from cities where id in (SELECT idCity1 from neighbours n where n.idCity2=" + cityid + ")");
            while (rs.next()) {
                cities.add(new City(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public ArrayList<City> getFinalRoute(String route) {
        ArrayList<City> cities = new ArrayList<City>();
        ResultSet rs;
        try {
            List<String> r = new ArrayList<String>(Arrays.asList(route.split(" ")));
            rs = stmt.executeQuery("select * from cities where id=" + r.get(0));
            for (int i=0; i<r.size(); i++) {
                rs = stmt.executeQuery("select * from cities where id=" + r.get(i));
                if (rs.next()) {
                    cities.add(new City(
                            rs.getInt("id"),
                            rs.getString("name")
                    ));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}