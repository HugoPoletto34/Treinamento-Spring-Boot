package com.treino.HugoReply.services;

import com.treino.HugoReply.dto.Request.CityRequestDTO;
import com.treino.HugoReply.dto.Response.CityResponseDTO;
import com.treino.HugoReply.entities.City;
import com.treino.HugoReply.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    @Autowired
    private CityRepository repository;

    @Transactional
    public List<CityResponseDTO> findAll () {
        List<City> listCities = repository.findAll();
        return listCities.stream().map(x -> new CityResponseDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public CityResponseDTO findAllById (Long id) {
        City c = repository.getById(id);
        return new CityResponseDTO(c);
    }

    @Transactional
    public CityResponseDTO findAllByName (String name) {
        City c = repository.getByName(name);
        return new CityResponseDTO(c);
    }

    @Transactional
    public CityRequestDTO insert (CityRequestDTO dto) {
        try {
            City c = repository.save(dto.build());
            return new CityRequestDTO(c);
        }
        catch (Exception e) {
            System.err.println("Erro ao inserir cidade: verifique se a informação da unidade federativa está correta.\n" + e);
            return null;
        }
    }
}
