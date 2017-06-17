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

public class OmdbServiceImpl implements OMDBService {
    private static final Logger log = LoggerFactory.getLogger(ImdbSpeechlet.class);

    /**
     * This implementation is highly unsafe. Only for demo purpose.
     * @param movieName name of the movie
     * @return
     */
    public JSONObject callOmdbApi(String movieName) {
        JSONObject json;
        HttpURLConnection con = null;
        InputStream in = null;
        try {
            movieName = URLEncoder.encode(movieName, "UTF-8");
            String address = "https://www.omdbapi.com/?t=" + movieName
                    + "&apikey=289ba303";

            URL url = new URL(address);
            con = (HttpURLConnection) url.openConnection();
            in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            String body = IOUtils.toString(in, encoding);
            json = new JSONObject(body);
        }
        catch (Exception e) {
            log.error("Exception Message :" + e.getMessage());
            log.error(ExceptionUtils.getStackTrace(e));
            json = null;
        }
        finally {
            IOUtils.closeQuietly(in);
            con.disconnect();
        }
        return json;
    }

}
