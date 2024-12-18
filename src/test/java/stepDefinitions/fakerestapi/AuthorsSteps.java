package stepDefinitions.fakerestapi;

import fakerestapi.response.AuthorsResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static fakerestapi.requests.AuthorRequests.getAuthors;
import static fakerestapi.requests.AuthorRequests.postAuthors;

public class AuthorsSteps {
    AuthorsResponse authorsResponse = new AuthorsResponse();
    Response response;

    @Given("the user is created author with id = {int}")
    public void the_user_is_created_author_with_id(int id) {
        authorsResponse.setId(id);
    }

    @Given("the user is created book with id = {int}")
    public void the_user_is_created_book_with_id(int idBook) {
        authorsResponse.setIdBook(idBook);
    }

    @Given("the first name is = {string}")
    public void the_first_name_is(String firstName) {
        authorsResponse.setFirstName(firstName);
    }

    @Given("the last name is = {string}")
    public void the_last_name_is(String lastName) {
        authorsResponse.setLastName(lastName);
    }

    @Then("the user is creating author using parameters above")
    public void the_user_is_creating_author_using_parameters_above() {
        response = postAuthors(authorsResponse);
    }

    @Then("print the first and last name")
    public void print_the_first_and_last_name() {
        authorsResponse = response.as(AuthorsResponse.class);

        System.out.println(authorsResponse.getFirstName());
        System.out.println(authorsResponse.getLastName());
        System.out.println(authorsResponse.getId());
        System.out.println(authorsResponse.getIdBook());
    }

    @Given("the user is get authors")
    public void the_user_is_get_authors() {
        getAuthors().prettyPrint();
    }
}
