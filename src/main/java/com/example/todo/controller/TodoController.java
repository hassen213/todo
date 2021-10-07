package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoRepository todoRepository;

    @GetMapping("/findall")
    public List<Todo> findall(){
        return this.todoRepository.findAll();
    }

    @PostMapping("/add")
    public Todo addTodo(@RequestBody Todo todo){
        return todoRepository.save(todo);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Integer id){

        if(this.todoRepository.findById(id).isPresent()){
            this.todoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Integer id,@RequestBody Todo todo){
        if(this.todoRepository.findById(id).isPresent()){
            todo.setId(id);
          return new ResponseEntity<>(this.todoRepository.save(todo),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
