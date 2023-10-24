package com.project.hotel.input;

public class CliInput implements Input{
    public static CliInput create() {
        return new CliInput();
    }
    @Override
    public String command() {
        return null;
    }
}
