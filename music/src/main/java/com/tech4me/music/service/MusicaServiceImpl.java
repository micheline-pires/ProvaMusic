package com.tech4me.music.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.InputMismatchException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech4me.music.repository.MusicaRepository;
import com.tech4me.music.shared.MusicaDTO;
import com.tech4me.music.model.Musica;



@Service
public class MusicaServiceImpl implements MusicaService{

    @Autowired
    MusicaRepository repositorioMusica;

    @Override
    public List<MusicaDTO> obterTodos() {     
        List<Musica> musicas = repositorioMusica.findAll();

        ModelMapper mapper = new ModelMapper();

        return musicas.stream()
        .map(musica -> mapper.map(musica, MusicaDTO.class))
        .collect(Collectors.toList());
        
    }

    @Override
    public MusicaDTO adicionar(MusicaDTO musicaDto) {
        
        ModelMapper mapper = new ModelMapper();
        Musica musica = mapper.map(musicaDto, Musica.class);

        musica.setId(null); 
        musica = repositorioMusica.save(musica);
        return mapper.map(musica, MusicaDTO.class);
       
    }
    
    @Override
    public Optional<MusicaDTO> obterPorId(String idMusica) {
        
        Optional<Musica> musica = repositorioMusica.findById(idMusica);
       
        
        if(musica.isEmpty()){
            throw new InputMismatchException("Musica não encontrado com o ID: " + idMusica);
        }
       
        MusicaDTO musicaDto = new ModelMapper().map(musica.get(), MusicaDTO.class);
        return Optional.of(musicaDto);

    }

    @Override
    public void deletar(String idMusica) {

        repositorioMusica.deleteById(idMusica);

}

    @Override
    public Musica atualizar(String idMusica, Musica musica) {
        musica.setId(idMusica);
        
        return repositorioMusica.save(musica);

      
    }


}

