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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getById (Long id) {
        Dealership d = repository.getById(id);
        return valida(d, "Concessionária id=\""+id+"\" não encontrado: Verifique se a ID informada está correta.");
    }

    @Transactional
    public ResponseEntity getByName (String name) {
        Dealership d = repository.getByName(name);
        return valida(d, "Concessionária \""+name+"\" não encontrado: Verifique se nome informada está correta.");
    }

    @Transactional
    public ResponseEntity getByCNPJ(String cnpj) {
        Dealership d = repository.getByCNPJ(cnpj);
        return valida(d, "Concessionária cnpj=\""+cnpj+"\" não encontrado: Verifique se o CNPJ informada está correta.");
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

    private ResponseEntity valida(Dealership obj, String t) {
        try {
            DealershipResponseDTO o = new DealershipResponseDTO(obj);
            return ResponseEntity.ok().body(o);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(t);
        }
    }

    @Transactional
    public ResponseEntity insert (DealershipRequestDTO dto) {

        dto.setCidade(cityRep.getByName(dto.getCidade().getNome()));
        if (dto.getCidade() == null)
            return ResponseEntity.badRequest().body("Cidade não encontrada: Verifique se a cidade informada está correta.");
        Dealership c = repository.save(dto.build());
        return ResponseEntity.ok().body(c);

    }
}
