package ru.stqa.pft.mfp;

import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "C#", "Python", "C++");

        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
