package com.artur.demoApp.controllers;

import com.artur.demoApp.models.Speaker;
import com.artur.demoApp.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list(){
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id){
        return speakerRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Speaker create(@RequestBody final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody final Speaker speaker) {
        Speaker existingSpeaker = speakerRepository.findById(id).orElse(null);
        if(existingSpeaker != null){
            BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
            return speakerRepository.saveAndFlush(existingSpeaker);
        } else {
            return null;
        }
    }
}
