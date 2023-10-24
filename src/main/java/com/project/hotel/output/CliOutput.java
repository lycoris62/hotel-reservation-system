package com.project.hotel.output;

public class CliOutput implements Output{

    public static CliOutput create() {
        return new CliOutput();
    }

    @Override
    public void print(String page) {
        System.out.print(page);
    }

    @Override
    public void println(String page) {
        System.out.println(page);
    }
}
