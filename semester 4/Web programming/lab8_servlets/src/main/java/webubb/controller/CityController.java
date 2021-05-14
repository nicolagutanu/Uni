package webubb.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.City;
import webubb.domain.User;
import webubb.model.DBManager;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityController extends HttpServlet {
    private User currentUser = new User(1, "a", "a");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ((action != null) && action.equals("getAll")) {
            int userid = Integer.parseInt(request.getParameter("userid"));

            response.setContentType("application/json");
            DBManager dbmanager = new DBManager();
            ArrayList<City> cities = dbmanager.getAllCities(userid);
            JSONArray jsonAssets = new JSONArray();
            for (int i = 0; i < cities.size(); i++) {
                JSONObject jObj = new JSONObject();
                jObj.put("id", cities.get(i).getId());
                jObj.put("name", cities.get(i).getName());
                jsonAssets.add(jObj);
            }
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonAssets.toJSONString());
            out.flush();
        } else if ((action != null) && action.equals("addToRoute")) {
            int userid = Integer.parseInt(request.getParameter("userid"));
            int cityid = Integer.parseInt(request.getParameter("cityid"));

            DBManager dbmanager = new DBManager();
            User u = dbmanager.getCurrentUser(userid);
            currentUser.setId(u.getId());
            currentUser.setUsername(u.getUsername());
            currentUser.setPassword(u.getPassword());
            currentUser.setRoute(cityid);
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println("City added successfully");
            out.flush();
        } else if ((action != null) && action.equals("getNeighbours")) {
            int cityid = Integer.parseInt(request.getParameter("cityid"));

            response.setContentType("application/json");
            DBManager dbmanager = new DBManager();
            ArrayList<City> cities = dbmanager.getNeighbours(cityid);
            JSONArray jsonAssets = new JSONArray();
            for (int i = 0; i < cities.size(); i++) {
                JSONObject jObj = new JSONObject();
                jObj.put("id", cities.get(i).getId());
                jObj.put("name", cities.get(i).getName());
                jsonAssets.add(jObj);
            }
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonAssets.toJSONString());
            out.flush();
        } else if ((action != null) && action.equals("getRoute")) {
            response.setContentType("application/json");
            DBManager dbmanager = new DBManager();
            ArrayList<City> cities = new ArrayList<>();
            try {
                cities = dbmanager.getFinalRoute(currentUser.getRoute());
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            JSONArray jsonAssets = new JSONArray();
            for (int i = 0; i < cities.size(); i++) {
                JSONObject jObj = new JSONObject();
                jObj.put("id", cities.get(i).getId());
                jObj.put("name", cities.get(i).getName());
                jsonAssets.add(jObj);
            }
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonAssets.toJSONString());
            out.flush();
        } else if ((action != null) && action.equals("changeRoute")) {
            int citypos = Integer.parseInt(request.getParameter("citypos"));

            String currentRoute = currentUser.getRoute();
            currentUser.resetRoute();
            List<String> r = new ArrayList<String>(Arrays.asList(currentRoute.split(" ")));
            for (int i=0; i<citypos; i++){
                System.out.println(r.get(i));
                currentUser.setRoute(Integer.parseInt(r.get(i)));
            }
            System.out.println("ChangeROUTE: "+currentUser.getRoute());
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println("Route changed successfully");
            out.flush();
        } else if ((action != null) && action.equals("getNeighboursWithoutId")) {
            String currentRoute = currentUser.getRoute();
            System.out.println(currentRoute);
            List<String> r = new ArrayList<String>(Arrays.asList(currentRoute.split(" ")));
            int cityid = Integer.parseInt(r.get(r.size()-1));
            System.out.println(cityid);

            response.setContentType("application/json");
            DBManager dbmanager = new DBManager();
            ArrayList<City> cities = dbmanager.getNeighbours(cityid);
            JSONArray jsonAssets = new JSONArray();
            for (int i = 0; i < cities.size(); i++) {
                JSONObject jObj = new JSONObject();
                jObj.put("id", cities.get(i).getId());
                jObj.put("name", cities.get(i).getName());
                jsonAssets.add(jObj);
            }
            System.out.println(jsonAssets);
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonAssets.toJSONString());
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
