/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author kike
 */
public class ClienteDAO {

    private static EntityManagerFactory getEntity() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pe.gestor_Cripto05_war_1.0-SNAPSHOTPU");
            return emf;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Cliente> listar() {
        try {
            EntityManager em = getEntity().createEntityManager();
            Query q = em.createNamedQuery("Cliente.findAll");
            List<Cliente> lista = q.getResultList();
            return lista;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public static boolean agregar(Cliente persona) {
        try {
            EntityManager em = getEntity().createEntityManager();
            em.getTransaction().begin();
            em.persist(persona);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //probar
    public static boolean editar(Cliente persona) {
        try {
            EntityManager em = getEntity().createEntityManager();
            em.getTransaction().begin();
            em.merge(persona);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static void eliminar(String codiAlum) {
        Cliente tmp = new Cliente();
        EntityManager em = getEntity().createEntityManager();
        tmp = em.getReference(Cliente.class, codiAlum);
        em.getTransaction().begin();
        em.remove(tmp);
        em.getTransaction().commit();
    }

    public static void main(String[] args) {
        List<Cliente> lista = ClienteDAO.listar();
        for (Cliente cliente : lista) {
            System.out.println(cliente.getNombClie());
        }
    }
}
