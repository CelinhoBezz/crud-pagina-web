package br.com.leisure.imovel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.leisure.imovel.model.Login;

@Controller
public class LoginController {

	private static List<Login> lista = new ArrayList<>();
	private static int proxId = 1;

	static {
		lista.add(new Login(1, "Marcelo", "@email.com", "11 970757430", "123.456.789-00", "rua 123", "senha123"));
	}

	// CRIAR USUÁRIO
	@GetMapping("/usuario/criar")
	public String novo(Model model) {
		model.addAttribute("login", new Login());
		return "login/criar-usuario";
	}

	// ATUALIZAR DADOS
	@GetMapping("/usuario/{id}/atualizar")
	public String atualizacao(@PathVariable int id, Model model, RedirectAttributes redirect) {
		Login login = buscarPorId(id);
		if (login != null) {
			model.addAttribute("login", login);
			return "login/criar-usuario";
		}
		redirect.addFlashAttribute("mensagem", "Login não existente");
		return "login/criar-usuario";
	}

	// DELETAR USUÁRIO
	@GetMapping("/usuario/{id}/deletar")
	public ModelAndView deletar(@PathVariable int id, RedirectAttributes redirect) {
		ModelAndView modelView = new ModelAndView("redirect:/usuario/criar");
		if (id < proxId) {
			remover(id);
			redirect.addFlashAttribute("mensagem", "Login deletado com sucesso!");
			if (lista.size() == 0) {
				return modelView;
			}
		} else {
			redirect.addFlashAttribute("mensagem", "Imóvel selecionado não existente!");
		}
		return modelView;
	}

	// MÉTODO PARA ANALISAR O ID
	public static Login buscarPorId(int id) {
		for (Login login : lista) {
			if (login.getId() == id) {
				return login;
			}
		}
		return null;
	}

//	@GETMAPPING("/LOGIN")
//	PUBLIC STRING LOGIN(MODEL MODEL) {
////		MODEL.ADDATTRIBUTE("LOGIN", LISTA);
//		RETURN "LOGIN/LOGIN";
//	}

// ---------------------------------------- MÉTODOS ------------------------------------//

	private static void remover(int id) {
		ListIterator<Login> iterator = lista.listIterator();
		while (iterator.hasNext()) {
			Login login = iterator.next();
			if (login.getId() == id) {
				iterator.remove();
				break;
			}
		}
	}

	public static void inserir(Login login) {
		login.setId(proxId++);
		lista.add(login);
	}

	public static void atualizar(Login login) {
		ListIterator<Login> iterator = lista.listIterator();
		while (iterator.hasNext()) {
			Login login_desatualizado = iterator.next();
			if (login_desatualizado.getId() == login.getId()) {
				iterator.set(login);
				break;
			}
		}
	}

	public Login buscarLogin(String nome, String senha) {
		ListIterator<Login> iterator = lista.listIterator();
		while (iterator.hasNext()) {
			Login login = iterator.next();
			if (nome.equals( login.getNome()) && senha.equals(login.getSenha())) {
				return login;
			}
		}
		return null;
	}

//-------------------- MÉTODO CHAMADO ATRAVÉS DO FORM ACTION E METHOD, NO HTML --------//
	@PostMapping("/login/cadastrar-atualizar")
	public ModelAndView criarOuAtualizar(Login login, RedirectAttributes redirect) {
		ModelAndView modelView = new ModelAndView("login/login");
		if (login.getId() == 0) {
			inserir(login);
			redirect.addFlashAttribute("mensagem", "Login criado com sucesso!");
		} else {
			atualizar(login);
			redirect.addFlashAttribute("mensagem", "Login atualizado com sucesso!");
		}
//	redirect.addFlashAttribute("login", login);
		modelView.addObject("login", login);
		return modelView;
	}

	@GetMapping("login/usuarios")
	public ModelAndView Usuarios() {
		ModelAndView modelView = new ModelAndView("login/usuarios");
		modelView.addObject("logins", lista);
		return modelView;
	}

	
	@GetMapping("/cadastro")
	public String cadastrar(Model model) {
		model.addAttribute("login", new Login());
		return "login/logar-ou-cadastrar";
	}
	
	@PostMapping("/cadastro")
	public String cadastro(@RequestParam String nome, @RequestParam String senha, Model model) {
//		ModelAndView modelView = new ModelAndView("/cadastro");
		Login loginEncontrado = buscarLogin(nome, senha);
		if (loginEncontrado != null) {
			model.addAttribute("login", loginEncontrado);
			return "login/login";
		}
		return "usuario-senha-invalido";
	}

}
