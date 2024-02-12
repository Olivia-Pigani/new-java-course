package com.example.spring_todos.controller;

import com.example.spring_todos.entity.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class TodoController {

@RequestMapping(value = "/todos")
    public String getAllTodos(Model model){

    List<Todo> todos = Arrays.asList(
            new Todo(1L,"test","description",false),
            new Todo(2L,"test2","description",false),
            new Todo(3L,"test3","description",false),
            new Todo(4L,"test4","description",false),
            new Todo(5L,"test5","description",false)

    );

    model.addAttribute("todos",todos);



    return "todo/all";
}


@RequestMapping(value = "/todo/2")
    public String getOneTodo(Model model){
    List<Todo> todos = Arrays.asList(
            new Todo(1L,"test","description",false),
            new Todo(2L,"test2","description",false),
            new Todo(3L,"test3","description",false),
            new Todo(4L,"test4","description",false),
            new Todo(5L,"test5","description",false)

    );

    Todo todoToShow = todos.stream()
            .filter(todo -> todo.getId().equals(2L))
            .findFirst()
            .orElse(null);
    model.addAttribute("todoToShow",todoToShow);


    return "todo/details";
}



}
