/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Empleado;
import exceptions.ExceptionIncidencia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author daw2
 */
@Stateless
public class SessionEJB {

    @PersistenceUnit EntityManagerFactory emf;
    public void WorkerIn(Empleado e) throws ExceptionIncidencia {
        EntityManager em = emf.createEntityManager();
        Empleado aux = em.find(Empleado.class, e.getNombreusuario());
        if (aux != null) {
            em.close();
            throw new ExceptionIncidencia("This user alrready exists");
        }
        em.persist(e);
        em.close();
    }
    
    public List<Empleado> listadoEmpleados(){
        return emf.createEntityManager().createNamedQuery("Empleado.findAll").getResultList();
    }
}
