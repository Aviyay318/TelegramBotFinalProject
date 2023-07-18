package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiManager {
    public ApiManager() {
    }


    public String activitiesApi(){
        Bored bored;
        try {
            GetRequest getRequest = Unirest.get("https://www.boredapi.com/api/activity");
            System.out.println(getRequest.asString().getBody());
            HttpResponse<String> response = getRequest.asString();
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            bored = objectMapper.readValue(json, new TypeReference<>(){});
            System.out.println(bored);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bored.toString();
    }

    public String jokeApi(String category){
          GetRequest getRequest = Unirest.get("https://v2.jokeapi.dev/joke/"+category+"?format=txt");
          try {
              System.out.println(getRequest.asString().getBody());
              return getRequest.asString().getBody();
           }catch (Exception e) {
        throw new RuntimeException(e);
    }

    }
    public void dogApi(){
        RandomDog randomDog;
        try {
            GetRequest getRequest = Unirest.get("https://dog.ceo/api/breeds/image/random");
            HttpResponse<String> response = getRequest.asString();
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            randomDog = objectMapper.readValue(json, new TypeReference<>() {});
            randomDog.setUrlToPhotoOfRandomDog();

        }catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    public String catsFactApi(){
        CatFacts catFacts;
        GetRequest getRequest = Unirest.get("https://catfact.ninja/fact");
        ObjectMapper objectMapper= new ObjectMapper();
        try {
            HttpResponse<String> response = getRequest.asString();
            String json = response.getBody();
            catFacts= objectMapper.readValue(json, new TypeReference<>() {
            });
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

            return (catFacts.getFact());
    }

    public String numberApi(){
        GetRequest getRequest = Unirest.get("http://numbersapi.com/random/math");
        try {
            return getRequest.asString().getBody();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
