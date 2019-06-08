package tasks.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tasks.dao.UsuarioDao;
import tasks.model.Usuario;

@Controller
public class LoginController {

	private final UsuarioDao dao;
	
	public LoginController() {
		this.dao = new UsuarioDao();
	}
	
	@RequestMapping("formlogin")
	public String formLogin() {
		return "tasks/form-login";
	}
	
	@RequestMapping("getlogin")
	public String getLogin(Usuario usuario, HttpSession session) {
		if (dao.existeUsuario(usuario)) {
			session.setAttribute("usuariologado", usuario);
			System.out.println("testando login");
			return "tasks/portal";
		}
		
		return "redirect:formlogin";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:formlogin";
	}
}
