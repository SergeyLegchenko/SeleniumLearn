package com.lsa.unit;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ArgsProv implements ArgumentsProvider {

    @Override
    public Stream<? extends org.junit.jupiter.params.provider.Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(4,"no"),
                Arguments.of(15,"half"),
                Arguments.of(100, "yes")
        );
    }
}

