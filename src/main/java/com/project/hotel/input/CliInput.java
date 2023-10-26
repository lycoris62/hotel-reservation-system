package com.project.hotel.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CliInput implements Input {

    private final BufferedReader br;
    public CliInput(InputStream stream) {
        br = new BufferedReader(new InputStreamReader(stream));
    }
    @Override
    public String command() throws IOException {
        return br.readLine();
    }
}
