package com.controller;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.dao.UsuarioDAO;
import com.model.Usuario;

@ManagedBean(name = "usuarioController")
@RequestScoped
public class UsuarioController {
	
	private Usuario usuario = new Usuario();
	
	public String novo() {
		Usuario usuario = new Usuario();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("usuario", usuario);
		return "/faces/novo.xhtml";
	}
	
	public String salvarUsuario(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		return "/faces/index.xhtml";
	}
	
	public String sair() {
		return "/faces/login.xhtml";
	}
	
	public String logar(String email, String senha) {
		System.out.println(email + "-" + senha);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscaPorLogin(email, senha);
		String retorno = null;
		if(usuario != null) {
			retorno = "/faces/index.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao logar!", "Usuário não encontrado."));
		}
		return retorno;
	}
	
	public List<Usuario> retornarUsuarios() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.retornarUsuarios();
	}
	
	public String editar(int id) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		usuario = usuarioDAO.buscar(id);
		System.out.println("************************");
		System.out.println(usuario);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("usuario", usuario);
		return "/faces/editar.xhtml";
	}
	
	public String atualizar(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.atualizar(usuario);
		return "/faces/index.xhtml";
	}
	
	public String excluir(int id) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(id);
		System.out.println("Usuário excluído!");
		return "/faces/index.xhtml";
	}

	 public Usuario getUsuario() {
         return usuario;
    }

    public void setUsuario(Usuario usuario) {
         this.usuario = usuario;
    }
    
}
