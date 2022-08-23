package com.cho.todo.controller;

import com.cho.todo.service.TodoService;
import model.TodoVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list() {

        return "todo/list";
    }

    @RequestMapping(value="list", method= RequestMethod.POST)
    public String list(TodoVO todoVO) {

        return "todo/list";
    }
}
