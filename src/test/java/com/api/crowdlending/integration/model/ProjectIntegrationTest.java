package com.api.crowdlending.integration.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.crowdlending.ApiCrowdlendingApplication;
import com.api.crowdlending.model.project;
import com.api.crowdlending.repository.projectRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiCrowdlendingApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ProjectIntegrationTest {

    @MockBean
    private projectRepository projectRepository;

    private List<project> expectedprojects = new ArrayList<>();

    @Before
    public void init() {
        project project1 = new project();
        project1.setId(1L);
        project1.setNom("Commerce bio");

        expectedprojects.add(project1);

    }

    @Test
    public void shouldSaveproject() {
        project projectToSave = new project();
        projectToSave.setId(1L);
        projectToSave.setNom("Commerce bio");
        project savedproject = this.projectRepository.save(projectToSave);

        Optional<project> found = this.projectRepository.findById(1L);
        assertThat(found.get()).isEqualTo(savedproject);
    }

    @Test
    public void shouldDisplayprojects() {
        List<project> foundUsers = this.projectRepository.findAll();

        assertNotNull(foundUsers);
        assertEquals(foundUsers.size(), 1);

        assertThat(foundUsers.get(0).getNom()).isEqualTo(expectedprojects.get(0).getNom());

    }

}
