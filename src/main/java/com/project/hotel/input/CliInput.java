package com.project.hotel.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CliInput implements Input {
    public static CliInput create() {
        return new CliInput();
    } //팩토리 매서드
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    @Override
    public String command() throws IOException {
        String s = br.readLine();
        return s;
    }
}
