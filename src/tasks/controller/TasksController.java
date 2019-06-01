package tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tasks.dao.TaskDao;
import tasks.model.Task;

@Controller
public class TasksController {
	
	private final TaskDao dao;
	
	public TasksController() {
		dao = new TaskDao();
	}
	
	@RequestMapping("/tasks/cadastratask")
	public String cadastra(Task task) {
		//TaskDao dao = new TaskDao();
		dao.inserir(task);
		return "tasks/task-cadastrada";
	}
	
	@RequestMapping("/tasks/novatask")
	public String form() {
		return "tasks/form-tasks";
	}
}
