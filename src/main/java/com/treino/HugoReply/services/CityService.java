package com.treino.HugoReply.services;

import com.treino.HugoReply.dto.Request.CityRequestDTO;
import com.treino.HugoReply.dto.Response.CityResponseDTO;
import com.treino.HugoReply.entities.City;
import com.treino.HugoReply.entities.Dealership;
import com.treino.HugoReply.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Enumerated;
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

    private ResponseEntity valida(City obj, String t) {
        try {
            CityResponseDTO o = new CityResponseDTO(obj);
            return ResponseEntity.ok().body(o);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(t);
        }
    }

    @Transactional
    public ResponseEntity<CityResponseDTO> getById (Long id) {
        City c = repository.getById(id);
        return valida(c, "Cidade id=\""+id+"\" não encontrada: Verifique se a ID cidade informada está correta.");
    }

    @Transactional
    public ResponseEntity<CityResponseDTO> getByName (String name) {
        City c = repository.getByName(name);
        return valida(c, "Cidade \""+name+"\" não encontrada: Verifique se o nome da cidade informada está correta.");
    }

    @Transactional
    public ResponseEntity<CityRequestDTO> insert (CityRequestDTO dto) {
        City d = repository.save(dto.build());
        return ResponseEntity.ok().body(new CityRequestDTO(d));
    }
}
