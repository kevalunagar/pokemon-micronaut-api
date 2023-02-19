package com.testapi;

import com.testapi.pokemon.Pokemon;
import com.testapi.power.Power;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

@MicronautTest
class TestApiTest {

//  @Test
//  void testGetPokemonList(RequestSpecification spec) {
//    Pokemon pikachu = new Pokemon(1, "Pikachu", new Power(1, "Grass"), "pikachu.com");
//    List<Pokemon> expected = List.of(pikachu);
//    List<Pokemon> pokemonList =
//        spec.when()
//            .get("/pokemon")
//            .then()
//            .statusCode(200)
//            .extract()
//            .body()
//            .jsonPath()
//            .getList(".", Pokemon.class);
//    Assertions.assertThat(pokemonList).containsExactlyInAnyOrderElementsOf(expected);
//  }

  @Test
  void testAddPokemon(RequestSpecification spec) {
    spec.given()
        .body("{\"name\": \"TestPokemon\", \"power\": 1}")
        .header("Content-Type", "application/json")
        .when()
        .post("/pokemon")
        .then()
        .statusCode(201)
        .assertThat()
        .body("id", Matchers.notNullValue())
        .body("name", Matchers.equalTo("TestPokemon"))
        .body("power.powerId", Matchers.equalTo(1))
        .body("power.name", Matchers.equalTo("Grass"));
  }

  @Test
  void testGetPokemonById(RequestSpecification spec) {
    spec.given()
        .pathParam("id", 1)
        .when()
        .get("/pokemon/{id}")
        .then()
        .statusCode(200)
        .assertThat()
        .body("id", Matchers.equalTo(1))
        .body("name", Matchers.equalTo("Pikachu"))
        .body("power.powerId", Matchers.equalTo(1))
        .body("power.name", Matchers.equalTo("Grass"));
  }

  @Test
  void testUpdatePokemon(RequestSpecification spec) {
    spec.given()
        .pathParam("id", 1)
        .body("{\"name\": \"UpdatedPikachu\", \"power\": 2, \"imageUrl\": \"pikachu.com\" }")
        .header("Content-Type", "application/json")
        .when()
        .put("/pokemon/{id}")
        .then()
        .statusCode(200)
        .assertThat()
        .body("id", Matchers.equalTo(1))
        .body("name", Matchers.equalTo("UpdatedPikachu"))
        .body("power.powerId", Matchers.equalTo(2))
        .body("power.name", Matchers.equalTo("Fire"));
  }

  @Test
  void testDeleteById(RequestSpecification spec) {
    spec.given().pathParam("id", 1).when().delete("/pokemon/{id}").then().statusCode(200);
  }
}
