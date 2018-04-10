/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Empleado;
import exceptions.ExceptionIncidencia;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SessionEJB;

/**
 *
 * @author daw2
 */
public class newWorker {
    
    @EJB SessionEJB miEjb;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //Pickup the variable's values.
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String tlf = request.getParameter("tlf");
//        int tlf = Integer.parseInt(request.getParameter("tlf"));
        String city = request.getParameter("city");
        String password = request.getParameter("password");
        
        Empleado e = new Empleado(name, surname, tlf, city, password);
        
        try {
            miEjb.WorkerIn(e);
            
            request.setAttribute("status", "Worker added");
        } catch (ExceptionIncidencia ex){
            request.setAttribute("status", ex.getMessage());
        }
        request.getRequestDispatcher("/final.jsp").forward(request, response);
    }
}