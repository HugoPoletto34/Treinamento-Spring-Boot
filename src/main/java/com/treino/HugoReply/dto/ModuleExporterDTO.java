package com.treino.HugoReply.dto;

import com.treino.HugoReply.dto.Request.DealershipRequestDTO;
import com.treino.HugoReply.dto.Response.CityResponseDTO;
import com.treino.HugoReply.dto.Response.DealershipResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleExporterDTO {
    private List<DealershipResponseDTO> dealershipList;
    private List<CityResponseDTO> cityList;
}
