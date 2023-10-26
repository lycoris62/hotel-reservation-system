package com.project.hotel.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class CliInputTest {

    @DisplayName("cli command")
    @Test
    void test() throws IOException {
        String input = """
                1
                """;
        var byteArrayInputStream = new ByteArrayInputStream(input.getBytes());

        var cliInput = new CliInput(byteArrayInputStream);

        String command = cliInput.command();
        assertThat(command).isEqualTo("1");
    }
}