package com.amazonaws.lambda.demo;

import org.json.JSONException;
import org.json.JSONObject;

public class OmdbServiceExceptionImpl implements OMDBService {

    @Override
    public JSONObject callOmdbApi(String movieName) throws JSONException {
        // TODO Auto-generated method stub
        throw new JSONException("");
    }

}
