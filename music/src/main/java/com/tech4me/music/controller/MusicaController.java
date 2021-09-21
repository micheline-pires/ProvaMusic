package com.tech4me.music.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.tech4me.music.model.Musica;
import com.tech4me.music.shared.MusicaDTO;
import com.tech4me.music.service.MusicaServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/musicas")
public class MusicaController {
    
    @Autowired
    MusicaServiceImpl servicoMusica;

    @GetMapping
    public ResponseEntity<List<MusicaDTO>> obterTodos(){
       return new ResponseEntity<>(servicoMusica.obterTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MusicaDTO>> obterPorId(@PathVariable String id){
      
        Optional<MusicaDTO> musicaDto = servicoMusica.obterPorId(id);

        return new ResponseEntity<>(musicaDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MusicaDTO> adicionar(@RequestBody MusicaDTO musicaDto){
        MusicaDTO musicaCadastrada = servicoMusica.adicionar(musicaDto);
        
        return new ResponseEntity<>(musicaCadastrada, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id){
        servicoMusica.deletar(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musica> atualizar(
        @PathVariable String id, 
        @RequestBody Musica musica){
            
        Musica musicaAtualizada = servicoMusica.atualizar(id, musica);
        return new ResponseEntity<>(musicaAtualizada, HttpStatus.OK);
    }



}
    

