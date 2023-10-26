package com.project.hotel.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CliInput implements Input {

    private static CliInput cliInput;
    public static CliInput create() {
        if(cliInput == null) return cliInput = new CliInput(System.in);
        return cliInput;
    } //팩토리 매서드

    public CliInput(InputStream stream) {
        br = new BufferedReader(new InputStreamReader(stream));
    }

    private final BufferedReader br;

    @Override
    public String command() throws IOException {
        return br.readLine();
    }
}
