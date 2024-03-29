package com.merafil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Calculators {

    private UserData data ;

    public Calculators() {
    }

    public Calculators(UserData data) {
        this.data = data;
    }

    public void calculateBMI() {

    double calculation = data.getWeight() / ((data.getHeight()/100) * (data.getHeight()/100)) ;
    BigDecimal bd = new BigDecimal(Double.toString(calculation)).setScale(2, RoundingMode.HALF_UP);
    if(bd.doubleValue() > 25)   {
        System.out.println(bd.doubleValue() + " - Obese");
    }
    else    {
        System.out.println(bd.doubleValue() + " - Normal");
    }
    }

    // IDEAL BODY WEIGHT
    public void calculateIBW()    {

        System.out.println("Euqation Broca: " + (data.getHeight() - 100) + "kg");

        if(data.getGender()=='m') {
            System.out.println("Euqation Lorentza: " + (data.getHeight() - 100 - ((data.getHeight() - 150) / 4)) + "kg");
            System.out.println("Euqation Pottona: " + (data.getHeight() - 100 - ((data.getHeight() - 100) / 20)) + "kg");
        }
        else    {
            System.out.println("Euqation Lorentza: " + (data.getHeight() - 100 - ((data.getHeight() - 150) / 2)) + "kg");
            System.out.println("Euqation Pottona  " + (data.getHeight() - 100 - ((data.getHeight() - 100) / 10)) + "kg");
        }

    }

    // LEAN BODY MASS
    public float calculateLBM()  {
        if(data.getGender()=='m')   {
            float calculation = (float) ((1.1*data.getWeight()) - (128 * ((data.getWeight() / data.getHeight()) * (data.getWeight() / data.getHeight()))));
            BigDecimal bd = new BigDecimal(Double.toString(calculation)).setScale(2, RoundingMode.HALF_UP);
            return bd.floatValue();
        } else  {
            float calculation = (float) ((1.07*data.getWeight()) - (148 * ((data.getWeight() / data.getHeight()) * (data.getWeight() / data.getHeight()))));
            BigDecimal bd = new BigDecimal(Double.toString(calculation)).setScale(2, RoundingMode.HALF_UP);
            return bd.floatValue();
        }
    }

    // BASAL METABOLIC RATE
    public void calculateBMR() {

        if(data.getGender()=='m') {
            BigDecimal bdM = new BigDecimal(Double.toString(((10 * data.getWeight()) + (6.25 * data.getHeight()) - (5 * data.getAge()) + 5))).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Euqation Mifflin: " + bdM.floatValue() + "kcal");

            BigDecimal bdHB = new BigDecimal(Double.toString((66.5 + (13.75 * data.getWeight()) + (5.003 * data.getHeight()) - (6.775 * data.getAge())))).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Euqation Harris-Benedict: " + bdHB.floatValue() + "kcal");
        }   else    {
            BigDecimal bdM = new BigDecimal(Double.toString(((10 * data.getWeight()) + (6.25 * data.getHeight()) - (5 * data.getAge()) - 161))).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Euqation Mifflin: " + bdM.floatValue() + "kcal");

            BigDecimal bdHB = new BigDecimal(Double.toString((655.1 + (9.563 * data.getWeight()) + (1.85 * data.getHeight()) - (4.676 * data.getAge())))).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Euqation Harris-Benedict: " + bdHB.floatValue() + "kcal");
        }

        BigDecimal bdC = new BigDecimal(Double.toString(500 + (22 * calculateLBM()))).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Euqation Cunningham: " + bdC.floatValue() + "kcal");

        BigDecimal bdKM = new BigDecimal(Double.toString(370 + (21.6 * calculateLBM()))).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Euqation Katch-McArdle: " + bdKM.floatValue() + "kcal");

    }

    public BigDecimal SMRValue(){
        BigDecimal bdM;
        if(data.getGender()=='m') {
            bdM = new BigDecimal(Double.toString(((10 * data.getWeight()) + (6.25 * data.getHeight()) - (5 * data.getAge()) + 5) * data.getActivity())).setScale(2, RoundingMode.HALF_UP);
        }
        else    {
            bdM = new BigDecimal(Double.toString(((10 * data.getWeight()) + (6.25 * data.getHeight()) - (5 * data.getAge()) - 161) * data.getActivity())).setScale(2, RoundingMode.HALF_UP);
        }
        return bdM;
    }

    // STANDARD METABOLIC RATE
    public void calculateSMR()  {
        float smr = SMRValue().floatValue();
        System.out.println("Euqation Mifflin: " + smr + "kcal - based on activity of " + data.getActivity());
    }

    // first new BigDecimal is percentage of total calories, second one is amount of kcal per gram
    public void calculateMacro(){
        BigDecimal carbs = SMRValue().multiply(new BigDecimal(0.4)).divide(new BigDecimal(4),2,RoundingMode.HALF_UP).setScale(0,RoundingMode.HALF_UP);
        BigDecimal proteins = SMRValue().multiply(new BigDecimal(0.3)).divide(new BigDecimal(4),2,RoundingMode.HALF_UP).setScale(0,RoundingMode.HALF_UP);
        BigDecimal fats = SMRValue().multiply(new BigDecimal(0.3)).divide(new BigDecimal(9),2,RoundingMode.HALF_UP).setScale(0,RoundingMode.HALF_UP);
        System.out.println("Based on macro ratio of 40% carbs, 30% protein and 30% fats");
        System.out.println("CARBS: " + carbs + "g || " + "PROTEINS: " + proteins + "g || " + "FATS: " + fats  + "g" );
    }

    public void calculateReduction() {
        float reduction = SMRValue().floatValue();
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("You need " + df.format((reduction-250)) + "kcal to lose 0.25 kg per week");
        System.out.println("You need " + df.format((reduction-500)) + "kcal to lose 0.5 kg per week");
        System.out.println("You need " + df.format((reduction-1000)) + "kcal to lose 1 kg per week");
    }

    public void printAll()  {

        System.out.println("*** PERSONAL DATA ***");
        data.printUserData();
        System.out.println("*** RESULT BMI ***");
        calculateBMI();
        System.out.println("*** RESULTS IDEAL BODY WEIGHT ***");
        calculateIBW();
        System.out.println("*** RESULT LEAN BODY MASS ***");
        System.out.println(calculateLBM());
        System.out.println("*** RESULTS BASAL METABOLIC RATE ***");
        calculateBMR();
        System.out.println("*** RESULTS STANDARD METABOLIC RATE ***");
        calculateSMR();
        System.out.println("*** RESULTS FOR MACRONUTRIENTS ***");
        calculateMacro();
        System.out.println("*** RESULTS FOR REDUCTION ***");
        calculateReduction();

    }

}
