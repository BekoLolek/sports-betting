package models;

import java.text.MessageFormat;

public class Calculator {

    public static String calculate(float home, float away, float draw, float total){

        float sumOfInverses = 1/home + 1/away + 1/draw;

        if(sumOfInverses < 1){

            float homeStake = total/home * 1/sumOfInverses;
            float awayStake = total/away * 1/sumOfInverses;
            float drawStake = total/draw * 1/sumOfInverses;

            return MessageFormat.format("Home amount: {0}, odds: {1}; Draw amount: {2}, odds: {3} Away amount: {4}, odds: {5}; with total of {6}", homeStake,home,drawStake,draw,awayStake,away, total ) ;


        }else{
            return "No arbitrary solution";
        }

    }

}
