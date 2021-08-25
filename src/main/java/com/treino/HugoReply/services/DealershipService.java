package com.treino.HugoReply.services;

import com.treino.HugoReply.dto.Request.CityRequestDTO;
import com.treino.HugoReply.dto.Request.DealershipRequestDTO;
import com.treino.HugoReply.dto.Response.CityResponseDTO;
import com.treino.HugoReply.dto.Response.DealershipResponseDTO;
import com.treino.HugoReply.entities.City;
import com.treino.HugoReply.entities.Dealership;
import com.treino.HugoReply.entities.FederativeUnit;
import com.treino.HugoReply.repositories.CityRepository;
import com.treino.HugoReply.repositories.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealershipService {
    @Autowired
    private DealershipRepository repository;
    @Autowired
    private CityRepository cityRep;

    @Transactional
    public DealershipResponseDTO getById (Long id) {
        Dealership d = repository.getById(id);
        return new DealershipResponseDTO(d);
    }

    @Transactional
    public DealershipResponseDTO getByName (String name) {
        Dealership d = repository.getByName(name);
        return new DealershipResponseDTO(d);
    }

    public DealershipResponseDTO getByCNPJ(String cnpj) {
        Dealership d = repository.getByCNPJ(cnpj);
        return new DealershipResponseDTO(d);
    }

    @Transactional
    public List<DealershipResponseDTO> findAll () {
        List<Dealership> listDealerships = repository.findAll();
        return listDealerships.stream().map(x -> new DealershipResponseDTO(x)).collect(Collectors.toList());
    }

    public List<DealershipResponseDTO> findAllDealershipsByCity(CityResponseDTO cidade) {
        List<Dealership> list = repository.findAllByCity(cidade.getNome());
        List<DealershipResponseDTO> listDTO = list.stream().map(x -> new DealershipResponseDTO(x)).collect(Collectors.toList());

        return listDTO;
    }

    public List<DealershipResponseDTO> findAllDealershipsByFU(FederativeUnit objUF) {
        List<Dealership> list = repository.findAllByFU(objUF);
        List<DealershipResponseDTO> listDTO = list.stream().map(x -> new DealershipResponseDTO(x)).collect(Collectors.toList());

        return listDTO;
    }

    @Transactional
    public DealershipRequestDTO insert (DealershipRequestDTO dto) {
        try {
            dto.setCidade(cityRep.getByName(dto.getCidade().getNome()));
            Dealership c = repository.save(dto.build());
            return new DealershipRequestDTO(c);
        }
        catch (Exception e) {
            System.err.println("Erro ao inserir cidade: verifique se a informação da unidade federativa está correta.\n" + e);
            return null;
        }
    }
}
