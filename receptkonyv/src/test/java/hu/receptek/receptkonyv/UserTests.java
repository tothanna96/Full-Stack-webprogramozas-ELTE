package hu.receptek.receptkonyv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hu.receptek.receptkonyv.entities.Recipe;
import hu.receptek.receptkonyv.entities.Role;
import hu.receptek.receptkonyv.entities.User;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    private String getTokenForUser(String username, String password) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        ResponseEntity<String> responseAuth = restTemplate.postForEntity("/users/login", user, String.class);
        String jsonString = responseAuth.getBody();
        JSONObject json2 = new JSONObject(jsonString);
        return json2.getString("token");
    }

    private HttpEntity getRequestEntityForUser(String username, String password) throws Exception {
        String token = getTokenForUser(username, password);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return new HttpEntity(null, headers);
    }

    @Test
    public void getUserById() throws Exception {
        HttpEntity requestEntity = getRequestEntityForUser("user2", "User2");
        ResponseEntity<User> response = restTemplate.exchange(
                "http://localhost:" + port + "/users/1",
                HttpMethod.GET,
                requestEntity,
                User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        User us = response.getBody();
        assertThat(us.getUsername()).isEqualTo("user1");
    }


    @Test
    public void getAllUsersAdmin() throws Exception{

        HttpEntity requestEntity = getRequestEntityForUser("user2", "User2");
        ResponseEntity<List<User>> response = restTemplate.exchange(
                "http://localhost:" + port + "/users",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<User>>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());
        assertThat(response.getBody().size()).isEqualTo(2);
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void signUp() throws Exception{
        User user = new User();
        user.setUsername("new user");
        user.setPassword("password");
        user.setEmail("akarmi@valami");
        user.setRole(Role.ROLE_ADMIN);
        //user.setEnabled(true);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(user);
        HttpEntity<String> requestEntityWithBody = new HttpEntity<String>(json, headers);

         ResponseEntity<Recipe> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/users",
                HttpMethod.POST,
                requestEntityWithBody,
                Recipe.class
        );

        assertNotNull(responsePost);
        assertThat(responsePost.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        HttpEntity requestEntity = getRequestEntityForUser("user1", "User1");
        ResponseEntity<List<User>> responseAfter = restTemplate.exchange(
                "http://localhost:" + port + "/users",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<User>>() {});
        assertThat(responseAfter.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseAfter.getBody().size()).isEqualTo(3);

    }



}
