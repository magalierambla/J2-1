/*
package com.api.crowdlending.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import com.api.crowdlending.model.adminstrateur;
import com.api.crowdlending.model.porte_project;
import com.api.crowdlending.model.user;
import com.api.crowdlending.repository.adminstrateurRepository;
import com.api.crowdlending.repository.porteProjectRepository;
import com.api.crowdlending.repository.projectRepository;
import com.api.crowdlending.repository.userRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

*/
/*@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
@WithMockUser*//*



//@RunWith(SpringRunner.class) //Junit 4
//@ExtendWith(SpringExtension.class) //Junit 5
//@WebMvcTest(controllers = adminController.class)
//@DataJpaTest //
//@SpringBootTest
//@WebMvcTest(controllers = projectController.class)
//@AutoConfigureMockMvc
public class ProjectControllerTests {
	@Mock
	public porteProjectRepository _porteProjectRepository;

	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	public void init() {
	}

	@Test
	public void contextLoads() {}

	@Test
	public void testGetAllPortesProject() throws Exception {
		ResponseEntity<List<porte_project>> portes;
		List<porte_project> response = new ArrayList<>();
		_porteProjectRepository = Mockito.mock(porteProjectRepository.class);

		Mockito.when(_porteProjectRepository.findAll()).thenReturn(response);

			this.mockMvc.perform(get("/project/all_portes"))
					.andExpect(status().isOk());
	}


}
*/
