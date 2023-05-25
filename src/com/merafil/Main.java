package com.merafil;

public class Main {

    public static void main(String[] args) {

        // activity levels - 1.4 exercise 2-3 days a week - 1.3 move decent amount - 1.2 sit whole day

        UserData me = new UserData('m', "M","A",27,181,72, 1.5);

        //UserData me = new UserData('m', "Madon","Pawel",19,180,79,1.25);

        Calculators calculator = new Calculators(me);
        calculator.printAll();

    }
}
