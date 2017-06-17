package com.amazonaws.lambda.demo;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class LambdaFunctionHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    public LambdaFunctionHandler() {
        super(new ImdbSpeechlet(new OmdbServiceImpl()), supportedApplicationIds);
    }

    // Only for test purpose. Missing DI
    public LambdaFunctionHandler(OMDBService omdbService) {
        super(new ImdbSpeechlet(omdbService), supportedApplicationIds);
    }
}