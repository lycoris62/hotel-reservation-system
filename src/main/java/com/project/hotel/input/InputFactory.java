package com.project.hotel.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InputFactory {

    private static final CliInput cliInput = new CliInput(System.in);

    public static CliInput cliInput(){
        return cliInput;
    }
    public static CliInput fileInput(File file) {
        try {
            var stream = new FileInputStream(file);
            return new CliInput(stream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("파일 입력 실패");
        }
    }
}
