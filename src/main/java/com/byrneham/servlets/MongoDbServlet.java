package com.byrneham.servlets;

import com.byrneham.mongodb.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/*
*
* if this app is deployed in an openshift project called simpleweb
* and the mongoinit mongodb server is deployed in the same project, then the mongodb host can be
* accessed from the internal DNS name  mongoinit.simpleweb.svc.cluster.local
* by setting the environment var MONGO_HOST to mongoinit.simpleweb.svc.cluster.local
*
* */
public class MongoDbServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder buf = new StringBuilder();
        buf.append("<html>");
        buf.append("<body>");
        buf.append("Request from host: ").append(req.getHeader("host")).append("<br/>");
        buf.append("Request context path: ").append(req.getContextPath()).append("<br/>");
        String mongoHost = System.getenv("MONGO_HOST");
        // System.out.println("MONGO_HOST environment var Mongo Host: " + mongoHost);
        buf.append("MONGO_HOST environment var Mongo Host: ").append(mongoHost).append("<br/>");
        try {
            MongoClient mongoClient = new MongoClient( mongoHost,27017 );
            MongoDatabase db = mongoClient.getDatabase("test");
            FindIterable<Document> iterable = db.getCollection("restaurants").find();
            GsonBuilder gsonBldr = new GsonBuilder();
            gsonBldr.registerTypeAdapter(Restaurant.class, new RestaurantDeserializerFromJsonWithDifferentFields());
            Gson gson = gsonBldr.create();
            List<Restaurant> restaurants = RestaurantHelper.getRestaurants(iterable,gson);
            int size = restaurants.size()>10 ? 10 : restaurants.size();
            for (int i = 0; i < size; i++) {
                Restaurant restaurant =  restaurants.get(i);
                buf.append(restaurant.toString()).append("<br/>");
            }
            mongoClient.close();
        } catch (Exception e) {
            buf.append("Exception: ").append(e.getMessage()).append("<br/>");
        }
        buf.append("</body>");
        buf.append("</html>");
        resp.getWriter().print(buf.toString());
    }

}