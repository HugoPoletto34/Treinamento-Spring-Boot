package com.treino.HugoReply.services;

import com.treino.HugoReply.dto.Request.HospitalRequestDTO;
import com.treino.HugoReply.dto.Request.ProfissionalRequestDTO;
import com.treino.HugoReply.dto.Response.HospitalResponseDTO;
import com.treino.HugoReply.dto.Response.ProfissionalResponseDTO;
import com.treino.HugoReply.entities.Hospital;
import com.treino.HugoReply.entities.Profissional;
import com.treino.HugoReply.repositories.HospitalRepository;
import com.treino.HugoReply.repositories.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfissionalService {
    @Autowired
    private ProfissionalRepository pRepository;


    @Transactional
    public ProfissionalRequestDTO insert (Profissional dto) {
        Profissional p = pRepository.save(dto);
        return new ProfissionalRequestDTO(p);
    }

    @Transactional
    public Profissional getProfissionalById(Long codigo) {
        Profissional p = pRepository.getById(codigo);
        return p;
    }

    @Transactional
    public List<ProfissionalResponseDTO> findAll() {
        List<Profissional> list = pRepository.findAll();
        return list.stream().map(x ->
                new ProfissionalResponseDTO(x)
        ).collect(Collectors.toList());
    }

    @Transactional
    public ProfissionalResponseDTO findById(Long codigo) {
        ProfissionalResponseDTO dto = new ProfissionalResponseDTO(pRepository.getById(codigo));
        return dto;
    }
}
