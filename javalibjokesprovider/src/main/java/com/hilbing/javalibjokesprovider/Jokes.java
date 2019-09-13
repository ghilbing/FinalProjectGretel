package com.hilbing.javalibjokesprovider;

import java.util.Random;

public class Jokes {
    
  /*  public String getJoke() {

        return "Why do we tell actors to break a leg? \n" +
                "Because every play has a cast!";

    }*/

    //https://stackoverflow.com/questions/5034370/retrieving-a-random-item-from-arraylist

  private static Random NR = new Random();

  public Jokes(){}

  private static String[] jokes = {
          "Why do we tell actors to break a leg? \n" +
                  "Because every play has a cast!",
          "What is the best thing about Switzerland? \n" +
                  "I do not know, but the flag is a big plus.",
          "I invented a new word! \n" +
                  "Plagiarism!",
          "Hear about the new restaurant called Karma? \n" +
                  "There's no menu: You get what you deserve.",
          "Did you hear about the claustrophobic astronaut? \n" +
                  "She just needed a little space.",
          "Why don't scientists trust atoms? \n" +
                  "Because they make up everything"

  };

  public static String getJoke(){
      int index = NR.nextInt(jokes.length);
      return jokes[index];
  }

}
