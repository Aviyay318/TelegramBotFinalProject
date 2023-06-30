package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.*;
import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RandomDog {
    private String message;
    public static final String PATH_TO_DOG = "res/dog/randomDog.jpg";

    public String getMessage(){
        return message;
    }

    public void setUrlToPhotoOfRandomDog(){
        //System.out.println(this.message);
        //TODO: the problem with the dog thing was that randomDogImage was set to null instead of new inputFile()..JUST FYI
        InputFile randomDogImage = new InputFile();
        try{
            URL url = new URL(this.message);
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(PATH_TO_DOG);

            byte[] bytes = new byte[2048];
            int length;

            while((length = inputStream.read(bytes))!=-1){
                outputStream.write(bytes, 0, length);
            }
            inputStream.close();
            outputStream.close();

            randomDogImage.setMedia(PATH_TO_DOG);

        } catch (IOException e){

        }
    }
}
