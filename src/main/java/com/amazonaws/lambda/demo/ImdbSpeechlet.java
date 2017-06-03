package com.amazonaws.lambda.demo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class ImdbSpeechlet implements Speechlet {
    private OMDBService omdbService;
    private static final Logger log = LoggerFactory.getLogger(ImdbSpeechlet.class);

    public ImdbSpeechlet(OMDBService omdbService) {
        this.omdbService = omdbService;
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session arg1)
            throws SpeechletException {

        Intent intent = request.getIntent();
        // Only 1 intent
        String targetMovie = intent.getSlot("Movie").getValue();
        return getImdbRating(targetMovie);

    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest arg0, Session arg1) {
        return getLaunchResponse();
    }

    @Override
    public void onSessionEnded(SessionEndedRequest arg0, Session arg1)
            throws SpeechletException {
        System.out.println("Session Ended");
    }

    @Override
    public void onSessionStarted(SessionStartedRequest arg0, Session arg1)
            throws SpeechletException {
        // To create dialogues. Lambda lifetime can be a problem for long conversations.
        System.out.println("Session Started");
    }

    private SpeechletResponse getImdbRating(String movieName) {
        SpeechletResponse response = null;
        try {
            JSONObject json = omdbService.callOmdbApi(movieName);
            String rating = json.getString("imdbRating");
            String speechText = "Imdb Rating of " + movieName + " is ," + rating;
            response = createSpeechletResponse(speechText);
        }
        catch (Exception e) {
            log.error("Exception Message :" + e.getMessage());
            log.error(ExceptionUtils.getStackTrace(e));
            response = fallback();
        }
        return response;
    }

    private SpeechletResponse createSpeechletResponse(String speechText) {
        SimpleCard card = new SimpleCard();
        card.setTitle("prasrathImdb");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SpeechletResponse fallback() {
        String speechText = "Please try again later";
        return createSpeechletResponse(speechText);
    }

    private SpeechletResponse getLaunchResponse() {
        String speechText = "Hi, Please say Imdb MovieName. For ex Imdb Titanic";
        return createSpeechletResponse(speechText);
    }
}
