package com.todo.todolist.resources;

import java.util.List;

import com.todo.todolist.domain.Users;
import com.todo.todolist.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired UserService service;

    @GetMapping
    public ResponseEntity<List<Users>> findAll(){
        List<Users> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Users> create(@RequestBody Users obj){
        obj = service.create(obj);
        return ResponseEntity.ok().body(obj);        
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Users> findById(@PathVariable Integer id){
        Users obj = service.findByID(id);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Users> update(@PathVariable Integer id, @RequestBody Users obj){
        Users newObj = service.update(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Users> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
