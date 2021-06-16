package com.n26.api.tests;

import com.n26.constants.APIEndPoints;
import com.n26.helpers.PetServiceHelper;
import com.n26.models.Category;
import com.n26.models.Pet;
import com.n26.models.Status;
import com.n26.models.Tag;
import com.n26.utils.JsonParser;
import io.restassured.response.Response;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.HttpStatus;
import org.json.simple.JSONArray;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetTests extends PetServiceHelper {
    JsonParser jsonTestData = new JsonParser("TestData");

    Category category = new Category();
    List<Tag> tagList = new ArrayList<Tag>();
    List<String> tagsName = new ArrayList<String>();

    /**
     * Create users to be assigned to car as car must be assigned to user
     */
    @BeforeClass
    public void prepareData() {
        prepareCategory();
        prepareTagLists();
    }

    @Test
    public void addPet() {
        Pet pet = new Pet();
        pet.setId((Long) (jsonTestData.getData("PetData").get("id")));
        pet.setName(String.valueOf(jsonTestData.getData("PetData").get("name")));
        pet.setCategory(category);

        JSONArray photoURLS = (JSONArray) jsonTestData.getData("PetData").get("photoUrls");
        pet.setPhotoUrls(photoURLS);

        pet.setTags(tagList);
        pet.setStatus(Status.valueOf(String.valueOf(jsonTestData.getData("PetData").get("status"))));

        Response restResponse = executePost(pet, HttpStatus.SC_OK, APIEndPoints.ADD_PET);
        Pet petResponse = restResponse.as(Pet.class);

        Assert.assertEquals(petResponse, pet);
        //Delete test data
        executeDeleteById(HttpStatus.SC_OK, APIEndPoints.DELETE_PET, petResponse.getId());

    }

    @Test
    public void findPetByStatus() {
        Pet pet = new Pet();
        pet.setId((Long) (jsonTestData.getData("PetData").get("id")));
        pet.setName(String.valueOf(jsonTestData.getData("PetData").get("name")));
        pet.setCategory(category);

        JSONArray photoURLS = (JSONArray) jsonTestData.getData("PetData").get("photoUrls");
        pet.setPhotoUrls(photoURLS);

        pet.setTags(tagList);
        pet.setStatus(Status.valueOf(String.valueOf(jsonTestData.getData("PetData").get("status"))));
        //Prepare test data
        Response restResponse = executePost(pet, HttpStatus.SC_OK, APIEndPoints.ADD_PET);
        Pet petResponse = restResponse.as(Pet.class);

        //Get all pets with specific status "available"
        restResponse = findByStatus(HttpStatus.SC_OK, APIEndPoints.GET_PET_BY_STATUS, String.valueOf(Status.available));
        Pet[] availablePets = restResponse.as(Pet[].class);

        Assert.assertTrue(availablePets.length > 0);
        Assert.assertTrue(ArrayUtils.contains(availablePets, petResponse));

        //Delete test data
        executeDeleteById(HttpStatus.SC_OK, APIEndPoints.DELETE_PET, petResponse.getId());

    }

    @Test
    public void findPetByTags() {
        Pet pet = new Pet();
        pet.setId((Long) (jsonTestData.getData("PetData").get("id")));
        pet.setName(String.valueOf(jsonTestData.getData("PetData").get("name")));
        pet.setCategory(category);

        JSONArray photoURLS = (JSONArray) jsonTestData.getData("PetData").get("photoUrls");
        pet.setPhotoUrls(photoURLS);

        pet.setTags(tagList);
        pet.setStatus(Status.valueOf(String.valueOf(jsonTestData.getData("PetData").get("status"))));
        //Prepare test data
        Response restResponse = executePost(pet, HttpStatus.SC_OK, APIEndPoints.ADD_PET);
        Pet petResponse = restResponse.as(Pet.class);

        //Get all pets with specific tags name
        restResponse = findByTags(HttpStatus.SC_OK, APIEndPoints.GET_PET_BY_TAGS, tagsName);
        Pet[] availablePets = restResponse.as(Pet[].class);

        Assert.assertTrue(availablePets.length > 0);
        Assert.assertTrue(ArrayUtils.contains(availablePets, petResponse));

        //Delete test data
        executeDeleteById(HttpStatus.SC_OK, APIEndPoints.DELETE_PET, petResponse.getId());

    }

    @Test
    public void getPetById() {
        Pet pet = new Pet();
        pet.setId((Long) (jsonTestData.getData("PetData").get("id")));
        pet.setName(String.valueOf(jsonTestData.getData("PetData").get("name")));
        pet.setCategory(category);

        JSONArray photoURLS = (JSONArray) jsonTestData.getData("PetData").get("photoUrls");
        pet.setPhotoUrls(photoURLS);

        pet.setTags(tagList);
        pet.setStatus(Status.valueOf(String.valueOf(jsonTestData.getData("PetData").get("status"))));
        //Prepare test data
        Response restResponse = executePost(pet, HttpStatus.SC_OK, APIEndPoints.ADD_PET);
        pet = restResponse.as(Pet.class);

        //Get all pets with specific status "available"
        restResponse = executeGetById(HttpStatus.SC_OK, APIEndPoints.GET_PET_BY_ID, pet.getId());
        Pet petResponse = restResponse.as(Pet.class);

        Assert.assertEquals(petResponse, pet);

        //Delete test data
        executeDeleteById(HttpStatus.SC_OK, APIEndPoints.DELETE_PET, petResponse.getId());

    }

    @Test
    public void updatePetById() {

        String petNewStatus = String.valueOf(Status.sold);
        String petNewName = "New Pet Name";

        // Arrange
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("name", petNewName);
        searchMap.put("status", petNewStatus);

        Pet pet = new Pet();
        pet.setId((Long) (jsonTestData.getData("PetData").get("id")));
        pet.setName(String.valueOf(jsonTestData.getData("PetData").get("name")));
        pet.setCategory(category);

        JSONArray photoURLS = (JSONArray) jsonTestData.getData("PetData").get("photoUrls");
        pet.setPhotoUrls(photoURLS);

        pet.setTags(tagList);
        pet.setStatus(Status.valueOf(String.valueOf(jsonTestData.getData("PetData").get("status"))));
        //Prepare test data
        Response restResponse = executePost(pet, HttpStatus.SC_OK, APIEndPoints.ADD_PET);
        pet = restResponse.as(Pet.class);

        restResponse = updatePetById(HttpStatus.SC_OK, APIEndPoints.UPDATE_PET_BY_ID, pet.getId(), searchMap);
        Pet petResponse = restResponse.as(Pet.class);

        Assert.assertEquals(petResponse.getName(), petNewName);
        Assert.assertEquals(String.valueOf(petResponse.getStatus()), petNewStatus);
        //Delete test data
        executeDeleteById(HttpStatus.SC_OK, APIEndPoints.DELETE_PET, petResponse.getId());

    }


    //@Test
    public void uploadPetImage() {
        //Prepare test data
        Pet pet = new Pet();
        pet.setId((Long) (jsonTestData.getData("PetData").get("id")));
        pet.setName(String.valueOf(jsonTestData.getData("PetData").get("name")));
        pet.setCategory(category);

        JSONArray photoURLS = (JSONArray) jsonTestData.getData("PetData").get("photoUrls");
        pet.setPhotoUrls(photoURLS);

        pet.setTags(tagList);
        pet.setStatus(Status.valueOf(String.valueOf(jsonTestData.getData("PetData").get("status"))));

        Response restResponse = executePost(pet, HttpStatus.SC_OK, APIEndPoints.ADD_PET);
        pet = restResponse.as(Pet.class);

        uploadImage(HttpStatus.SC_OK, APIEndPoints.UPLOAD_PET_IMAGE, pet.getId());
        //Delete test data
        executeDeleteById(HttpStatus.SC_OK, APIEndPoints.DELETE_PET, pet.getId());
    }

    @Test
    public void deletePet() {
        //Prepare test data
        Pet pet = new Pet();
        pet.setId((Long) (jsonTestData.getData("PetData").get("id")));
        pet.setName(String.valueOf(jsonTestData.getData("PetData").get("name")));
        pet.setCategory(category);

        JSONArray photoURLS = (JSONArray) jsonTestData.getData("PetData").get("photoUrls");
        pet.setPhotoUrls(photoURLS);

        pet.setTags(tagList);
        pet.setStatus(Status.valueOf(String.valueOf(jsonTestData.getData("PetData").get("status"))));

        executePost(pet, HttpStatus.SC_OK, APIEndPoints.ADD_PET);

        //Delete
        executeDeleteById(HttpStatus.SC_OK, APIEndPoints.DELETE_PET, pet.getId());

    }

    private void prepareCategory() {
        category.setId(1234);
        category.setName("Category Test");
    }

    private void prepareTagLists() {
        for (int i = 1; i < 3; i++) {
            Tag tag = new Tag();
            tag.setId(i);
            tag.setName("Tag_Name_" + i);
            tagList.add(tag);
            tagsName.add("Tag_Name_" + i);
        }
    }
}
