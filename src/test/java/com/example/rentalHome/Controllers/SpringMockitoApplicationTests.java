package com.example.rentalHome.Controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

import com.example.rentalHome.Domains.UserCredentials;
import com.example.rentalHome.Domains.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//--> IntegrationTest
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringMockitoApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper om = new ObjectMapper();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	//--> Object to JSON
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void createUserTest() throws Exception {
		UserData userData = new UserData(1L,"SAnketJaiswal","8959926364","SanketJiaswal.Newyork12@Gmail.com","118 Bahagt Singh Marge","2018-03-19","M","san-pic");
		mockMvc.perform(post("/user/api/create_user/v1.0").content(asJsonString(userData))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is("SAnketJaiswal")))
				.andDo(print());
	}


	@Test
	public void singInTestCase() throws Exception {
	 UserCredentials credentials = new UserCredentials(1L,"SanketJiaswal.Newyork@Gmail.com","Hack@1345678","8959926364");
		mockMvc.perform(post("/user/api/singup/v1.0").content(asJsonString(credentials))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.emailId", is("SanketJiaswal.Newyork@Gmail.com")))
				.andExpect(jsonPath("$.password", is("Hack@1345678")))
				.andExpect(jsonPath("$.mobile", is("8959926364")))
				.andDo(print());
	}

	@Test
	public void updateUserTest() throws Exception {
		UserData userData = new UserData(1L,"SAnketJaiswal","8959926364","SanketJiaswal.harvard@Gmail.com","118 Bahagt Singh Marge","2018-03-19","M","san-pic");
		mockMvc.perform(put("/user/api/updateuser/v1.0").content(asJsonString(userData)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("SAnketJaiswal")))
				.andExpect(jsonPath("$._links.self.href", is("http://localhost/user/api/updateuser/v1.0")))
				.andDo(print());
	}

	@Test
	public void searchUserIdTest() throws Exception {
		UserData userData = new UserData();
		userData.setUserId(1L);
		mockMvc.perform(post("/user/api/search_user/v1.0").content(asJsonString(userData))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userId", is(1)))
				.andExpect(jsonPath("$.name", is("SanketDineshJaiswal")))
				.andExpect(jsonPath("$._links.self.href", is("http://localhost/user/api/search_user/v1.0")))
				.andDo(print());
	}

	@Test
	public void deleteUserTest() throws Exception {
		UserData userData = new UserData();
		userData.setUserId(5L);
		mockMvc.perform(delete("/user/api/deleteuser/v1.0").content(asJsonString(userData))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNoContent()).andDo(print());
	}

	@Test
	public void deleteUser_NotFoundTest() throws Exception
	{
		UserData userData = new UserData();
		userData.setUserId(10L);
		mockMvc.perform(delete("/user/api/deleteuser/v1.0").content(asJsonString(userData))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound()).andDo(print());
	}

	@Test
	public void loginUserTest() throws Exception {
		UserCredentials credentials = new UserCredentials();
		credentials.setEmailId("sanket.jaiswal2409@gmail.com");
		credentials.setPassword("password@123");
		mockMvc.perform(post("/user/api/login/v1.0").content(asJsonString(credentials))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.userId",is(1)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name",is("SanketDineshJaiswal")))
				.andExpect(jsonPath("$._links.self.href",is("http://localhost/user/api/login/v1.0")))
				.andDo(print());
	}

	@Test
	public void getAllUser() throws Exception {
		mockMvc.perform(get("/user/api/show_all_users/v1.0"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].userId",is(1)))
				.andExpect(jsonPath("$[0].name",is("SanketDineshJaiswal")))
				.andExpect(jsonPath("$[1].userId",is(2)))
				.andExpect(jsonPath("$[1].name",is("LokiMarvelAvegger")))
				.andExpect(jsonPath("$[0].links[0].rel", is("self")))
				.andExpect(jsonPath("$[0].links[0].href", is("http://localhost/user/api/show_all_users/v1.0")))
				.andExpect(jsonPath("$[0].links[1].rel", is("UpdateUser")))
				.andExpect(jsonPath("$[0].links[1].href", is("http://localhost/user/api/updateuser/v1.0")))
				.andDo(print());
	}
}
