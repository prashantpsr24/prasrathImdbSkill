package com.amazonaws.lambda.demo;

import org.json.JSONException;
import org.json.JSONObject;

public interface OMDBService {

    public JSONObject callOmdbApi(String movieName) throws JSONException;
}
