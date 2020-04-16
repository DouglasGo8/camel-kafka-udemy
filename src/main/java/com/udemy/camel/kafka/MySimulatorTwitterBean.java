package com.udemy.camel.kafka;

import java.util.Random;

public class MySimulatorTwitterBean {

    private static final Random rdn = new Random();

    public String bunchOfTweets() {

        var tweets = new String[]
                {
                        "But door java sounds not be added",
                        "Program Java \"Statistika\" : http://bit.ly/SVFSzJ #www.terimajasa.net",
                        "Marc, please help me I bought Minecraft Java in 2016, but I couldn't redeem Minecraft for Windows 10",
                        "late night java",
                        "Tava aqui tentando encontrar algum contexto onde Java e lento não combinam... não consegui.",
                        "im doing nothing, java and laura are just in my mentions to get on every single one of my nerve",
                        "#java  va a salir campeón"
                };

        return tweets[rdn.nextInt(tweets.length)];

    }

}
