package com.api.crowdlending.functionutils;

import com.api.crowdlending.functionsUtils.methodesUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@ActiveProfiles("logback-test2")
public class MethodesUtilsTest {
    @Test
    public void should_generate_alpahnumeric_string_token() {
        //Arrange ==> tu prepares tes objets, des mocks
        //Act
        String chaineGeneree = methodesUtils.generateAlphanumericStringToken();

        //Assert
        assertThat(chaineGeneree.matches("^[a-zA-Z0-9_]*$"));
    }

    @Test
    public void should_get_MD5_Hex() throws NoSuchAlgorithmException {
        //Arrange
        //Act
        String chaineGeneree = methodesUtils.getMD5Hex("bonjour");

        //Assert
        //used website to get MD5 Hex : https://cryptii.com/pipes/md5-hash
        assertThat(chaineGeneree.equals("f02368945726d5fc2a14eb576f7276c0"));
    }

    @Test
    public void should_get_empty_string_when_empty_input_string_while_getting_get_MD5_Hex() throws NoSuchAlgorithmException {
        //Arrange
        //Act
        String chaineGeneree = methodesUtils.getMD5Hex("");

        //Assert
        assertThat(chaineGeneree.equals(""));
    }

    @Test
    public void should_get_empty_string_when_null_input_string_while_getting_get_MD5_Hex() throws NoSuchAlgorithmException {
        //Arrange
        //Act
        String chaineGeneree = methodesUtils.getMD5Hex(null);

        //Assert
        assertThat(chaineGeneree.equals(""));
    }

    @Test
    public void should_get_ip_adress() {
        //Arrange
        // Mock up HttpSession and insert it into mocked up HttpServletRequest
        HttpSession session = mock(HttpSession.class);
        given(session.getId()).willReturn("sessionid");

        // Mock up HttpServletRequest
        HttpServletRequest request = mock(HttpServletRequest.class);
        given(request.getSession()).willReturn(session);
        given(request.getSession(true)).willReturn(session);
        given(request.getRemoteAddr()).willReturn("192.168.1.1");
        HashMap<String,String[]> params = new HashMap<>();
        given(request.getParameterMap()).willReturn(params);

        methodesUtils mu = new methodesUtils();
        mu.setRequest(request);

        //Act
        String obtainedIp = methodesUtils.getClientIp();

        //Assert
        assertThat(obtainedIp.equals("192.168.1.1"));
    }


}
