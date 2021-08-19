package com.treino.HugoReply.services;

import com.treino.HugoReply.dto.Request.ProfissionalRequestDTO;
import com.treino.HugoReply.dto.Request.TipoRequestDTO;
import com.treino.HugoReply.dto.Response.ProfissionalResponseDTO;
import com.treino.HugoReply.dto.Response.TipoResponseDTO;
import com.treino.HugoReply.entities.Profissional;
import com.treino.HugoReply.entities.TipoProfissional;
import com.treino.HugoReply.repositories.ProfissionalRepository;
import com.treino.HugoReply.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoService {
    @Autowired
    private TipoRepository tRepository;


    @Transactional
    public TipoRequestDTO insert (TipoProfissional dto) {
        TipoProfissional p = tRepository.save(dto);
        return new TipoRequestDTO(p);
    }

    @Transactional
    public TipoProfissional getProfissionalById(Long codigo) {
        TipoProfissional p = tRepository.getById(codigo);
        return p;
    }

    @Transactional
    public List<TipoResponseDTO> findAll() {
        List<TipoProfissional> list = tRepository.findAll();
        return list.stream().map(x ->
                new TipoResponseDTO(x)
        ).collect(Collectors.toList());
    }

    @Transactional
    public TipoResponseDTO findById(Long codigo) {
        TipoResponseDTO dto = new TipoResponseDTO(tRepository.getById(codigo));
        return dto;
    }

    public TipoProfissional getTipoById(Long codigo) {
        TipoProfissional tp = tRepository.getById(codigo);
        return tp;
    }
}
