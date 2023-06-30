package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiManager {
    public ApiManager() {
    }

    public String jokeApi(String category){
          GetRequest getRequest = Unirest.get("https://v2.jokeapi.dev/joke/"+category+"?format=txt");
          try {
              return getRequest.asString().getBody();
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
    public String adviceSlipApi(){
        AdviceSlip adviceSlip;
        GetRequest getRequest = Unirest.get("https://api.adviceslip.com/advice");
        ObjectMapper objectMapper= new ObjectMapper();
        try {
            HttpResponse<String> response = getRequest.asString();
            String json = response.getBody();
            adviceSlip= objectMapper.readValue(json, new TypeReference<>() {
            });
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (adviceSlip.getAdvice());
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
