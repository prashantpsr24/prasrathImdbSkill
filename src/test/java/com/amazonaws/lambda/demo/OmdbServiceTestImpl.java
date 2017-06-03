package com.amazonaws.lambda.demo;

import org.json.JSONException;
import org.json.JSONObject;

public class OmdbServiceTestImpl implements OMDBService {

    @Override
    public JSONObject callOmdbApi(String movieName) throws JSONException {
        // TODO Auto-generated method stub
        if (movieName.equals("Titanic")) {
            JSONObject json = new JSONObject(
                    "{\"Title\":\"Titanic\",\"Year\":\"1997\",\"Rated\":\"PG-13\",\"Released\":\"19 Dec 1997\",\"Runtime\":\"194 min\",\"Genre\":\"Drama, Romance\",\"Director\":\"James Cameron\",\"Writer\":\"James Cameron\",\"Actors\":\"Leonardo DiCaprio, Kate Winslet, Billy Zane, Kathy Bates\",\"Plot\":\"A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.\",\"Language\":\"English, Swedish\",\"Country\":\"USA\",\"Awards\":\"Won 11 Oscars. Another 110 wins & 74 nominations.\",\"Poster\":\"https://images-na.ssl-images-amazon.com/images/M/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.7/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"88%\"},{\"Source\":\"Metacritic\",\"Value\":\"74/100\"}],\"Metascore\":\"74\",\"imdbRating\":\"7.7\",\"imdbVotes\":\"843,025\",\"imdbID\":\"tt0120338\",\"Type\":\"movie\",\"DVD\":\"N/A\",\"BoxOffice\":\"N/A\",\"Production\":\"Paramount Pictures\",\"Website\":\"http://www.titanicmovie.com/\",\"Response\":\"True\"}\n");
            return json;
        }
        return null;
    }
}
