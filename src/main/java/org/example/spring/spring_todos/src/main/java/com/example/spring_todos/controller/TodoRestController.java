package com.example.spring_todos.controller;

import com.example.spring_todos.entity.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/base")
public class TodoRestController {

    @GetMapping(value = "alltodos")
    public List<Todo> allTodos(){
        List<Todo> todos = Arrays.asList(
                new Todo(1L,"test","description",false),
                new Todo(2L,"test2","description",false),
                new Todo(3L,"test3","description",false),
                new Todo(4L,"test4","description",false),
                new Todo(5L,"test5","description",false)

        );

      return todos;
    }

    @GetMapping(value = "oneTodo")
    public Todo oneTodo(){
        Todo todo = new Todo(45L,"fsf", "sefsfse",true);
        return todo;
    }
}
