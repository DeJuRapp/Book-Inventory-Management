package com.example.tutoria1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//TODO open this view at the appropriate point
public class activity_library extends AppCompatActivity {
    View view;
    private ProgressBar progressBar2;
    private RequestQueue mRequestQueue;
    private ArrayList<BookInfo> bookInfoArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        view = this.getWindow().getDecorView();
        progressBar2 = findViewById(R.id.progressBar2);
        initAll();

    }
    private void initAll()
    {
        String query = "Java";
        progressBar2.setVisibility(View.VISIBLE);
        // creating a new array list.
        bookInfoArrayList = new ArrayList<>();

        // below line is use to initialize
        // the variable for our request queue.
        mRequestQueue = Volley.newRequestQueue(activity_library.this);

        // below line is use to clear cache this
        // will be use when our data is being updated.
        mRequestQueue.getCache().clear();

        // below is the url for getting data from API in json format.
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + query;//https://www.googleapis.com/books/v1/volumes?q=isbn:

        // below line we are  creating a new request queue.
        RequestQueue queue = Volley.newRequestQueue(activity_library.this);


        // below line is use to make json object request inside that we
        // are passing url, get method and getting json object. .

        JsonObjectRequest booksObjrequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar2.setVisibility(View.GONE);
                // inside on response method we are extracting all our json data.
                try {
                    JSONArray itemsArray = response.getJSONArray("items");
                    for (int i = 0; i < itemsArray.length(); i++) {
                        JSONObject itemsObj = itemsArray.getJSONObject(i);
                        JSONObject volumeObj = itemsObj.getJSONObject("volumeInfo");
                        String title = volumeObj.optString("title");
                        String subtitle = volumeObj.optString("subtitle");
                        JSONArray authorsArray = volumeObj.getJSONArray("authors");
                        String publisher = volumeObj.optString("publisher");
                        String publishedDate = volumeObj.optString("publishedDate");
                        String description = volumeObj.optString("description");
                        int pageCount = volumeObj.optInt("pageCount");
                        JSONObject imageLinks = volumeObj.optJSONObject("imageLinks");
                        String thumbnail = "null";
                        /*if(itemsArray.getJSONObject(i).getJSONObject("volumeInfo").getJSONObject("readingModes").optBoolean("image"))
                        {
                            thumbnail = imageLinks.optString("thumbnail");
                        }*/
                        try {
                            thumbnail = imageLinks.optString("thumbnail");
                        }catch (Exception e)
                        {
                            Log.println('a',"parser","no thumbnail");
                        }
                        //String thumbnail = imageLinks.optString("thumbnail");
                        String previewLink = volumeObj.optString("previewLink");
                        String infoLink = volumeObj.optString("infoLink");
                        JSONObject saleInfoObj = itemsObj.optJSONObject("saleInfo");
                        String buyLink = saleInfoObj.optString("buyLink");
                        ArrayList<String> authorsArrayList = new ArrayList<>();
                        if (authorsArray.length() != 0) {
                            for (int j = 0; j < authorsArray.length(); j++) {
                                authorsArrayList.add(authorsArray.optString(i));
                            }
                        }
                        //TODO test
                        String isbn13 = "";
                        JSONArray identifyArray = itemsObj.getJSONObject("volumeInfo").getJSONArray("industryIdentifiers");
                        for (int j = 0; j < identifyArray.length(); j++)
                        {
                            JSONObject itemsObj2 = identifyArray.getJSONObject(j);
                            if(itemsObj2.optString("type").equals("ISBN_13"))
                            {
                                isbn13 = itemsObj2.optString("identifier");
                            }
                        }

                        // after extracting all the data we are
                        // saving this data in our modal class.
                        BookInfo bookInfo = new BookInfo(title, subtitle, authorsArrayList, publisher, publishedDate, description, pageCount, thumbnail, previewLink, infoLink, buyLink, isbn13);

                        // below line is use to pass our modal
                        // class in our array list.
                        bookInfoArrayList.add(bookInfo);

                        // below line is use to pass our
                        // array list in adapter class.
                        BookAdapter adapter = new BookAdapter(bookInfoArrayList, activity_library.this);

                        // below line is use to add linear layout
                        // manager for our recycler view.
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity_library.this, RecyclerView.VERTICAL, false);
                        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.reclyer2);

                        // in below line we are setting layout manager and
                        // adapter to our recycler view.
                        mRecyclerView.setLayoutManager(linearLayoutManager);
                        mRecyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    // displaying a toast message when we get any error from API
                    Toast.makeText(activity_library.this, "No Data Found" + e, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // also displaying error message in toast.
                Toast.makeText(activity_library.this, "Error found is " + error, Toast.LENGTH_SHORT).show();
            }
        });
        // at last we are adding our json object
        // request in our request queue.
        queue.add(booksObjrequest);
    }

}
