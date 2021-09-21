package com.tech4me.music.service;

import java.util.List;
import java.util.Optional;

import com.tech4me.music.model.Musica;
import com.tech4me.music.shared.MusicaDTO;
import org.springframework.stereotype.Service;

@Service
public interface MusicaService {

   List<MusicaDTO> obterTodos();  
   
   Optional<MusicaDTO> obterPorId(String idMusica);

   MusicaDTO adicionar(MusicaDTO musica);
   
   Musica atualizar(String idMusica, Musica musica);

   void deletar(String idMusica);


    
}
