package com.project.hotel.input;

import com.project.hotel.output.CliOutput;

public class CliInput implements Input{
    public static CliInput create() {
        return new CliInput();
    }

    @Override
    public String command() {
        return null;
    }
}
