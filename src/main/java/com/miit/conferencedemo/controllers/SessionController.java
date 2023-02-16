package com.miit.conferencedemo.controllers;

import com.miit.conferencedemo.models.Session;
import com.miit.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController means that this will respond to payload incoming and outgoing as JSON REST endpoints
@RestController
//@RequestMapping simply means that it will tell the router what the mapping URL will look like
@RequestMapping(value = "/api/v1/sessions")
public class SessionController {

    //autowire will help to inject SessionRepository automatically in such way that
    //it will create an instance of the session repository and put it on our class
    @Autowired()
    private SessionRepository sessionRepository;

    @GetMapping
    //it will list all the session
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepository.findById(id).get();
    }

    @PostMapping
    //no requirement of @RequestMapping because it will post to the base part of the class
    // for e.g: "/api/v1/sessions"
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }

}
