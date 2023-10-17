package br.com.leisure.imovel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.leisure.imovel.model.Imovel;

@Controller
public class ImovelController {
	
	private static List<Imovel> imoveis = new ArrayList<>();
	private static int proxId = 1;
	
	@GetMapping("/imovel/criar")
	public String novo (Model model) {
		model.addAttribute("imovel", new Imovel());
		return "imovel/imovel-form";
	}
	
	@GetMapping("imovel/{id}/detalhar")
	public String detalhe (@PathVariable int id, Model model, RedirectAttributes redirect) {
		Imovel imovel = buscarPorId(id);
		if (imovel != null) {
			model.addAttribute("imovel", imovel);
			return "imovel/imovel-detalhe";
		}
		redirect.addFlashAttribute("mensagem", "Imóvel selecionado não existente!");
		return "redirect:/imovel/carrossel";
	}
	
	@GetMapping("/imovel/{id}/atualizar")
	public String atualizacao (@PathVariable int id, Model model, RedirectAttributes redirect) {
		Imovel imovel = buscarPorId(id);
		if (imovel != null) {
			model.addAttribute("imovel", imovel);
			return "imovel/imovel-form";
		}
		redirect.addFlashAttribute("mensagem", "Imóvel selecionado não existente!");
		return "redirect:/imovel/carrossel";
	}
	
	public static Imovel buscarPorId (int id) {
		for (Imovel imovel : imoveis) {
			if (imovel.getId() == id) {
				return imovel;
			}
		}
		return null;
	}
	
	@GetMapping("/imovel/{id}/deletar")
	public String deletar (@PathVariable int id, RedirectAttributes redirect) {
		if (id < proxId) {
			remover(id);
			redirect.addFlashAttribute("mensagem", "Imóvel deletado com sucesso!");
			if (imoveis.size() == 0) {
				return "redirect:/imovel/criar";
			}
		} else {
			redirect.addFlashAttribute("mensagem", "Imóvel selecionado não existente!");
		}
		return "redirect:/imovel/carrossel";
	}

	private static void remover (int id) {
		ListIterator<Imovel> iterator = imoveis.listIterator();
		while (iterator.hasNext()) {
			Imovel imovel = iterator.next();
			if (imovel.getId() == id) {
				iterator.remove();
				break;
			}
		}
	}
	
	@GetMapping("/imovel/carrossel")
	public String carroselImovel (Model model) {
		model.addAttribute("imoveis", imoveis);
		return "imovel/imovel-carrossel";
	}
	
	@PostMapping("/imovel/cadastrar-atualizar")
	public ModelAndView criarOuAtualizar (Imovel imovel, RedirectAttributes redirect) {
		ModelAndView modelView = new ModelAndView("redirect:/imovel/carrossel");		
		if (imovel.getId() == 0) {
			inserir(imovel);	
			redirect.addFlashAttribute("mensagem", "Imóvel criado com sucesso!");
		} else {
			atualizar(imovel);
			redirect.addFlashAttribute("mensagem", "Imóvel atualizado com sucesso!");
		}
		return modelView;
	}
	
	public static void inserir (Imovel imovel) {
		imovel.setId(proxId++);
		imoveis.add(imovel);
	}
	
	public static void atualizar (Imovel imovel) {
		ListIterator<Imovel> iterator = imoveis.listIterator();
		while (iterator.hasNext()) {
			Imovel imovel_desatualizado = iterator.next();
			if (imovel_desatualizado.getId() == imovel.getId()) {
				iterator.set(imovel);
				break;
			}
		}
	}
	
		
}
