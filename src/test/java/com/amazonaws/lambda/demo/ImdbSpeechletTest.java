package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.amazon.speech.json.SpeechletRequestEnvelope.Builder;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazonaws.services.ec2.model.SlotStartTimeRangeRequest;

public class ImdbSpeechletTest {
    private LaunchRequest launchRequest;
    private IntentRequest request;
    private Session session;

    @Before
    public void setUp() {
        launchRequest = LaunchRequest.builder().withRequestId("1234").build();
        session = Session.builder().withSessionId("121").build();

        Slot slot = Slot.builder().withName("Movie").withValue("Titanic").build();
        HashMap<String, Slot> slots = new HashMap<>();
        slots.put("Movie", slot);
        Intent intent = Intent.builder().withName("psrImdb").withSlots(slots).build();
        request = IntentRequest.builder().withIntent(intent).withRequestId("123").build();
    }

    @Test
    public void testOnLaunch() {
        SimpleCard expectedSimpleCard = new SimpleCard();
        expectedSimpleCard
                .setContent("Hi, Please say Imdb MovieName. For ex Imdb Titanic");

        ImdbSpeechlet imdbSpeechlet = new ImdbSpeechlet(new OmdbServiceTestImpl());
        SimpleCard actualSimpleCard = (SimpleCard) imdbSpeechlet.onLaunch(launchRequest,
                session).getCard();

        assertEquals(expectedSimpleCard.getContent(), actualSimpleCard.getContent());
    }

    @Test
    public void testOnIntent() throws SpeechletException {
        SimpleCard expectedSimpleCard = new SimpleCard();
        expectedSimpleCard.setContent("Imdb Rating of Titanic is ,7.7");

        ImdbSpeechlet imdbSpeechlet = new ImdbSpeechlet(new OmdbServiceTestImpl());

        SimpleCard actualSimpleCard = (SimpleCard) imdbSpeechlet.onIntent(request,
                session).getCard();

        assertEquals(expectedSimpleCard.getContent(), actualSimpleCard.getContent());

    }

    @Test
    public void testOnIntentWithException() throws SpeechletException {
        SimpleCard expectedSimpleCard = new SimpleCard();
        expectedSimpleCard.setContent("Please try again later");

        ImdbSpeechlet imdbSpeechlet = new ImdbSpeechlet(new OmdbServiceExceptionImpl());
        Slot slot = Slot.builder().withName("Movie").withValue("Titanic").build();
        HashMap<String, Slot> slots = new HashMap<>();
        slots.put("Movie", slot);
        Intent intent = Intent.builder().withName("psrImdb").withSlots(slots).build();
        IntentRequest request = IntentRequest.builder().withIntent(intent)
                .withRequestId("123").build();
        Session session = Session.builder().withSessionId("121").build();
        SimpleCard actualSimpleCard = (SimpleCard) imdbSpeechlet.onIntent(request,
                session).getCard();

        assertEquals(expectedSimpleCard.getContent(), actualSimpleCard.getContent());

    }
}
