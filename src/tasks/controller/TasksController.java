package tasks.controller;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import tasks.dao.TaskDao;
import tasks.model.Task;

@Controller
public class TasksController {
	
	private final TaskDao dao;
	
	@Autowired
	public TasksController(TaskDao dao) {
		this.dao = dao;
	}
	
	/*
	 * public TasksController() { this.dao = new TaskDao(); }
	 */
	
	@RequestMapping("cadastratask")
	// parametros @Valid e BindingResult para validacao
	public String cadastra(@Valid Task task, BindingResult result) {
		if (result.hasFieldErrors("descricao")) {
			return "tasks/form-tasks";
		}
		dao.inserir(task);
		//return "tasks/task-cadastrada";
		return "redirect:gettasks";
	}
	
	@RequestMapping("novatask")
	public String form() {
		return "tasks/form-tasks";
	}
	
	@RequestMapping("gettasks")
	// primeira opcao usando Model And View
/*	public ModelAndView getTasks() {
		List<Task> tasks = dao.getTasks();
		ModelAndView mv = new ModelAndView("tasks/get-tasks");
		mv.addObject("tasks", tasks);
		return mv;
	}*/
	public String getTasks(Model model) {
		model.addAttribute("tasks", dao.getTasks());
		return "tasks/get-tasks-ajax";
	}
	
	@RequestMapping("excluitask")
	public String exclui(Task task) {
		// Exclui e redireciona para a action de listar tasks
		dao.exclui(task);
		//Redirecionamento client side
		return "redirect:gettasks";
		//Redirecionamento server side
		//return "forward:tasks/get-tasks";
	}
	
	@RequestMapping("buscartask")
	public String busca(Long id, Model model) {
		model.addAttribute("task", dao.getById(id));
		return "tasks/busca-task";
	}
	
	@RequestMapping("finalizatask")
	//@ResponseBody
	public String finalizar(Long id, Model model) {
		dao.finaliza(id);
		model.addAttribute("task", dao.getById(id));
		return "tasks/data-finalizada";
	}
}
