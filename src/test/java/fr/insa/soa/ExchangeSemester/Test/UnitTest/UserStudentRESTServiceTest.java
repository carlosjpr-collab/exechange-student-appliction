package fr.insa.soa.ExchangeSemester.Test.UnitTest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import javax.print.attribute.standard.Media;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.insa.soa.ExchangeSemester.config.MvcConfigView;
import fr.insa.soa.ExchangeSemester.config.WebSecurityConfig;
import fr.insa.soa.ExchangeSemester.dao.UserRepository;
import fr.insa.soa.ExchangeSemester.restServices.UserRESTService;
import fr.insa.soa.ExchangeSemester.restServices.UserStudentRESTService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserStudentRESTServiceTest {
	

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserRepository userRepostory;

	
	@Test
	//@WithMockUser(authorities="ROLE_STUDENT")
	public void FirstTest() throws Exception {
		
		when(userRepostory.findAll()).thenReturn(
				Collections.emptyList()
				);
		
		 mockMvc.perform(get("/service/userStudent"))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType("application/json;charset=UTF-8"))
		   .andExpect(jsonPath("$[0].id", is(1061)));

		
	}
}
