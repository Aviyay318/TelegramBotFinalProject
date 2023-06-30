package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bored {
    private String activity;
    private String type;
    private int participants;
    private int price;
    private String link;
    private int accessibility;

    public String getActivity(){
        return this.activity;
    }
    public String getType(){
        return this.type;
    }
    public String getLink(){
        return this.link;
    }
    public int getParticipants(){
        return this.participants;
    }
    public int getPrice(){
        return this.price;
    }
    public int getAccessibility(){
        return this.accessibility;
    }

    public String toString(){
        String output = "";
        if (!this.link.equals("")){
            output = "Activity: '" + this.activity + "'\nOf type: " + this.type + "\nCosts: " + this.price
                    + "\nAt this level of accessibility: " + this.accessibility
                    + "\nCan be accessed through this link: " + this.link;
        }
        else {
            output = "Activity: '" + this.activity + "'\nOf type: " + this.type + "\nCosts: " + this.price
                    + "\nAt this level of accessibility: " + this.accessibility;
        }
        return output;
    }
}
