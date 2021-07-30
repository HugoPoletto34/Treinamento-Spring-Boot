package com.treino.HugoReply.services;

import com.treino.HugoReply.dto.Request.HospitalRequestDTO;
import com.treino.HugoReply.dto.Response.HospitalResponseDTO;
import com.treino.HugoReply.entities.Hospital;
import com.treino.HugoReply.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository repository;

    @Transactional
    public List<HospitalResponseDTO> findAll () {
        List<Hospital> list = repository.findAll();
        return list.stream().map(x -> new HospitalResponseDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public HospitalResponseDTO findById (Long id) {
        Hospital user = repository.getById(id);
        return new HospitalResponseDTO(user);
    }

    @Transactional
    public HospitalRequestDTO insert (Hospital dto) {
        Hospital u = repository.save(dto);
        return new HospitalRequestDTO(u);
    }

    @Transactional
    public Hospital getHospitalById(Long codigo) {
        Hospital h = repository.getById(codigo);
        return h;
    }
    @Transactional
    public List<HospitalRequestDTO> findAllHospitalsWhoPofissionalWorks(Long matricula) {
        List<Hospital> list = repository.findAllHospitalsWhoPofissionalWorks(matricula);
        return list.stream().map(x -> new HospitalRequestDTO(x)).collect(Collectors.toList());
    }
}
