package com.amazonaws.lambda.demo;

import org.json.JSONException;
import org.json.JSONObject;

public interface OMDBService {

    /**
     * It calls OMDB API to fetch IMDB Data.
     *
     * @param movieName name of the movie
     */
    JSONObject callOmdbApi(String movieName) throws JSONException;
}
