package com.treino.HugoReply;

import com.treino.HugoReply.Exporter.DealershipExporter;
import com.treino.HugoReply.dto.Response.DealershipResponseDTO;
import com.treino.HugoReply.services.DealershipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class HugoReplyApplicationTests {

	@Autowired
	private DealershipService service;

	@Test
	void contextLoads() throws IOException {
		HttpServletResponse response = null;
		List<DealershipResponseDTO> listUsers = service.findAll();

		DealershipExporter excelExporter = new DealershipExporter(listUsers);

		excelExporter.export(response);
	}

}
