package hu.receptek.receptkonyv;

import hu.receptek.receptkonyv.entities.Category;
import hu.receptek.receptkonyv.entities.Recipe;
import hu.receptek.receptkonyv.entities.Status;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class RecipeTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

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
    public void getAll() throws Exception {
        ResponseEntity<List<Recipe>> response = restTemplate.exchange("http://localhost:" + port + "/recipes",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Recipe>>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(response.getBody().size()).isEqualTo(9);
    }

    @Test
    public void recipeById() {
        ResponseEntity<Recipe> response = restTemplate.exchange(
                "http://localhost:" + port + "/recipes/1",
                HttpMethod.GET,
                null,
                Recipe.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Recipe rec = response.getBody();
        assertThat(rec.getTitle()).isEqualTo("Paprikás krumpli");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addRecipe() throws Exception {
        HttpEntity requestEntity = getRequestEntityForUser("user1", "User1");
        ResponseEntity<List<Recipe>> responseList = restTemplate.exchange("http://localhost:" + port + "/recipes", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Recipe>>() {});
        assertThat(responseList.getStatusCode()).isEqualTo(HttpStatus.OK);
       // assertThat(responseList.getBody().size()).isEqualTo(9);

        Recipe rec = new Recipe();
        rec.setTitle("kaja 5");
        rec.setCategory(Category.MAINCOURSE);
        rec.setIngredients("hozzavalo 1,2,3");
        rec.setInstructions("cook food");
        rec.setStatus(Status.PRIVATE);

        String token = getTokenForUser("user1", "User1");


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-Type", "application/json");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(rec);
        HttpEntity<String> requestEntityWithBody = new HttpEntity<String>(json, headers);

        ResponseEntity<Recipe> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/recipes",
                HttpMethod.POST,
                requestEntityWithBody,
                Recipe.class
        );

        assertThat(responsePost.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responsePost.getBody().getId()).isNotNull();
        //assertThat(responsePost.getBody().getId()).isEqualTo(10);
        assertThat(responsePost.getBody().getTitle()).isEqualTo("kaja 5");
        assertThat(responsePost.getBody().getIngredients()).isEqualTo("hozzavalo 1,2,3");
        assertThat(responsePost.getBody().getInstructions()).isEqualTo("cook food");
        assertThat(responsePost.getBody().getCategory()).isEqualTo("maincourse");
        assertThat(responsePost.getBody().getStatus()).isEqualTo(Status.PRIVATE);

    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deleteOwnRecipe() throws Exception {
        HttpEntity requestEntity = getRequestEntityForUser("user2", "User2");
        ResponseEntity<List<Recipe>> responseList = restTemplate.exchange("http://localhost:" + port + "/recipes", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Recipe>>() {});
        assertThat(responseList.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(responseList.getBody().size()).isEqualTo(9);

        ResponseEntity<Recipe> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/recipes/4",
                HttpMethod.DELETE,
                requestEntity,
                Recipe.class
        );

        ResponseEntity<List<Recipe>> responseList2 = restTemplate.exchange("http://localhost:" + port + "/recipes", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Recipe>>() {});
        assertThat(responseList2.getStatusCode()).isEqualTo(HttpStatus.OK);
      //  assertThat(responseList2.getBody().size()).isEqualTo(8);
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deleteRecipeAsAdmin() throws Exception {
        HttpEntity requestEntity = getRequestEntityForUser("user1", "User1");
        ResponseEntity<List<Recipe>> responseList = restTemplate.exchange("http://localhost:" + port + "/recipes", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Recipe>>() {});
        assertThat(responseList.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(responseList.getBody().size()).isEqualTo(9);

        ResponseEntity<Recipe> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/recipes/1",
                HttpMethod.DELETE,
                requestEntity,
                Recipe.class
        );

        ResponseEntity<List<Recipe>> responseList2 = restTemplate.exchange("http://localhost:" + port + "/recipes", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Recipe>>() {});
        assertThat(responseList2.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(responseList2.getBody().size()).isEqualTo(8);
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void updateRecipeAsAdmin() throws Exception {
        HttpEntity requestEntity = getRequestEntityForUser("user1", "User1");
        ResponseEntity<List<Recipe>> responseList = restTemplate.exchange("http://localhost:" + port + "/recipes", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Recipe>>() {});
        assertThat(responseList.getStatusCode()).isEqualTo(HttpStatus.OK);
       // assertThat(responseList.getBody().size()).isEqualTo(9);

        Recipe rec = new Recipe();
        rec.setTitle("modositva");
        rec.setCategory(Category.DESSERT);
        rec.setIngredients("uj hozzavalok");
        rec.setInstructions("uj utasitas");
        rec.setStatus(Status.PRIVATE);

        String token = getTokenForUser("user1", "User1");


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-Type", "application/json");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(rec);
        HttpEntity<String> requestEntityWithBody = new HttpEntity<String>(json, headers);

        ResponseEntity<Recipe> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/recipes/2",
                HttpMethod.PUT,
                requestEntityWithBody,
                Recipe.class
        );

        assertThat(responsePost.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responsePost.getBody().getId()).isNotNull();
        assertThat(responsePost.getBody().getId()).isEqualTo(2);
        assertThat(responsePost.getBody().getTitle()).isEqualTo("modositva");
        assertThat(responsePost.getBody().getIngredients()).isEqualTo("uj hozzavalok");
        assertThat(responsePost.getBody().getInstructions()).isEqualTo("uj utasitas");
        assertThat(responsePost.getBody().getCategory()).isEqualTo("dessert");
        assertThat(responsePost.getBody().getStatus()).isEqualTo(Status.PRIVATE);
    }

    @Test
    public void updateOwnRecipe() throws Exception {
        HttpEntity requestEntity = getRequestEntityForUser("user2", "User2");

        Recipe rec = new Recipe();
        rec.setTitle("ujnev");
        rec.setCategory(Category.DESSERT);
        rec.setIngredients("hozzavalok");
        rec.setInstructions("utasitas");
        rec.setStatus(Status.PUBLIC);

        String token = getTokenForUser("user2", "User2");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-Type", "application/json");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(rec);
        HttpEntity<String> requestEntityWithBody = new HttpEntity<String>(json, headers);

        ResponseEntity<Recipe> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/recipes/4",
                HttpMethod.PUT,
                requestEntityWithBody,
                Recipe.class
        );

        assertThat(responsePost.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responsePost.getBody().getId()).isNotNull();
        assertThat(responsePost.getBody().getId()).isEqualTo(4);
        assertThat(responsePost.getBody().getTitle()).isEqualTo("ujnev");
        assertThat(responsePost.getBody().getIngredients()).isEqualTo("hozzavalok");
        assertThat(responsePost.getBody().getInstructions()).isEqualTo("utasitas");
        assertThat(responsePost.getBody().getCategory()).isEqualTo("dessert");
        assertThat(responsePost.getBody().getStatus()).isEqualTo(Status.PUBLIC);
    }
}
