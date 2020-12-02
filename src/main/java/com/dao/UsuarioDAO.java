package com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.model.JPAUtil;
import com.model.Usuario;

public class UsuarioDAO {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	public void salvar(Usuario usuario) {
		entity.getTransaction().begin();
		entity.persist(usuario);
		entity.getTransaction().commit();
	//	JPAUtil.shutdown();
	}
	
	public void atualizar(Usuario usuario) {
		entity.getTransaction().begin();
		entity.merge(usuario);
		entity.getTransaction().commit();
	//	JPAUtil.shutdown();
	}
	
	public Usuario buscar(int id) {
		Usuario usuario = new Usuario();
		usuario = entity.find(Usuario.class, id);
	//	JPAUtil.shutdown();
		
		return usuario;
	}
	
	public void excluir(int id) {
		Usuario usuario = new Usuario();
		usuario = entity.find(Usuario.class, id);
		entity.getTransaction().begin();
		entity.remove(usuario);
		entity.getTransaction().commit();
	}
	
	public List<Usuario> retornarUsuarios() {
		List<Usuario> listaUsuarios = new ArrayList();
		Query q = entity.createQuery("SELECT u FROM Usuario u");
		listaUsuarios = q.getResultList();
		
		return listaUsuarios;
	}
	
	public Usuario buscaPorLogin(String email, String senha) {
		Query q = entity.createQuery("SELECT u FROM Usuario u WHERE email=:p_email AND senha=:p_senha");
		q.setParameter("p_email", email);
		q.setParameter("p_senha", senha);
		List<Usuario> aux = q.getResultList();

        if (!aux.isEmpty()) {
        	return aux.get(0);
        }
        else {
        	return null;
        }
	}
	

}
