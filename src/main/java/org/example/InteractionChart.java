package org.example;

import io.quickchart.QuickChart;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.IOException;
import java.lang.invoke.ConstantCallSite;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InteractionChart implements  Runnable{

    private QuickChart chart;

    private HashMap<String,Integer> interactionMap;

    public InteractionChart(TelegramBot telegramBot){
        this.interactionMap = telegramBot.getCounterMap();
        this.chart = new QuickChart();
        this.chart.setWidth(500);
        this.chart.setHeight(300);
        this.chart.setVersion("2.9.4");
    }

    public void run(){
        while(true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //System.out.println(this.chart.getConfig());
            try {
                this.chart.setConfig(
                        """
                        {
                        "type": "outlabeledPie",
                        "data": {
                    "labels": ["Cat Facts", "Jokes", "Numbers", "Activities", "Random Dog"],
                    "datasets": [{
                    "backgroundColor": ["#FF3784", "#36A2EB", "#4BC0C0", "#F77825", "#9966FF"],
                    """
                                +
                                """
                                "data": [
                                """
                                + this.interactionMap.get(Constants.API_CAT_FACT)+", "+this.interactionMap.get(Constants.API_JOKES)+", "+ this.interactionMap.get(Constants.API_NUMBERS)+", "+ this.interactionMap.get(Constants.API_ACTIVITIES)+", "+ this.interactionMap.get(Constants.API_RANDOM_DOG)+ "]" +
                                """
                                
                                }]
                            },
                            "options": {
                                "plugins": {
                                    "legend": true,
                                            "outlabels": {
                                        "text": "%l %p",
                                                "color": "white",
                                                "stretch": 30,
                                                "font": {
                                            "resizable": true,
                                                    "minSize": 12,
                                                    "maxSize": 18
                                        }
                                    }
                                }
                            }
                }""");
                this.chart.toFile("res/chart.png");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }
}
