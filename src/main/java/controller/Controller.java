package controller;

import input.SamplesImporter;

import java.io.IOException;

public class Controller {

    public static void main(String[] args){
        SamplesImporter importer = new SamplesImporter();

        try {
            importer.readSamplesFromFile("src/main/resources/dataToRead.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
