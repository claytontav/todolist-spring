package com.todo.todolist.service;

import java.util.List;
import java.util.Optional;

import com.todo.todolist.domain.Users;
import com.todo.todolist.repository.UserRepository;
import com.todo.todolist.service.Exceptions.DataIntegrityViolationException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<Users> findAll(){
        return repository.findAll();
    }
    
    public Users create(Users obj){
        obj.setId(null);
        return repository.save(obj);
    }

    public Users findByID(Integer id){
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado", Users.class.getName()) );
    }

    public Users update(Integer id, Users newObj){
        Users obj = findByID(id);
        obj.setName(newObj.getName());
        obj.setPass(newObj.getPass());
        return repository.save(obj);
    }

    public void delete(Integer id){
        findByID(id);
        try {

            repository.deleteById(id);

        } catch (DataIntegrityViolationException e)  {

            throw new DataIntegrityViolationException("Usuario não encontrado");

        }
    }
}
