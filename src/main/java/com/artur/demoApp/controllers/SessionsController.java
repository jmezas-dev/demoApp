package com.artur.demoApp.controllers;

import com.artur.demoApp.models.Session;
import com.artur.demoApp.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list(){
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Session create(@RequestBody final Session session){
        try {
            return sessionRepository.saveAndFlush(session);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody final Session session) {
        Session existingSession = sessionRepository.findById(id).orElse(null);
        if(existingSession != null){
            BeanUtils.copyProperties(session, existingSession, "session_id");
            return sessionRepository.saveAndFlush(existingSession);
        } else {
            return null;
        }
    }
}
