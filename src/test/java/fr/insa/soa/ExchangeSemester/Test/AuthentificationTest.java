package fr.insa.soa.ExchangeSemester.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import javax.print.attribute.standard.Media;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;


import fr.insa.soa.ExchangeSemester.restServices.UserRESTService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRESTService.class)
@WithMockUser(authorities="ADMIN")
public class AuthentificationTest {
	
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Before
    public void setUp() {		
		
		mockMvc = MockMvcBuilders
	            .webAppContextSetup(context)
	            .apply(springSecurity())
	            .build();

	}
	
	   @Test
	    public void testAnonymous() throws Exception {
	        mockMvc.perform(get("/api/user/account")).andExpect(status().is3xxRedirection());
	    }
}

