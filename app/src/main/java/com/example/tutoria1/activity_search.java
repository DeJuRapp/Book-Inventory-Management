package com.example.tutoria1;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class activity_search extends AppCompatActivity {
    View view;
    Button btn_scan;
    TextView tv1;
    private RequestQueue mRequestQueue;
    private ArrayList<BookInfo> bookInfoArrayList;
    private ProgressBar progressBar;

    private Button searchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        view = this.getWindow().getDecorView();
        //view.setBackgroundColor(Color.parseColor("#00ff00"));
        view.setBackgroundResource(R.drawable.pixil_frame_0_3_);
        tv1 = (TextView)findViewById(R.id.displaytext);
        tv1.setText("9783957988935");//9783957988935
        //9783732014699
        //9783423209939
        //9783551771575
        configButton();
        configScanButton();
        progressBar = findViewById(R.id.progressBar);
        searchBtn = findViewById(R.id.btn_isbn_search);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                // checking if our edittext field is empty or not.
                if (tv1.getText().toString().isEmpty()) {
                    tv1.setError("Please enter search query");
                    return;
                }
                // if the search query is not empty then we are
                // calling get book info method to load all
                // the books from the API.
                getBooksInfo(tv1.getText().toString());
            }
        });
    }



    private void configButton()
    {
        Button backButton = (Button) findViewById(R.id.button_back_to_main);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void configScanButton()
    {
        //setContentView(R.layout.activity_search);
        btn_scan = (Button) findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });
        /*btn_scan.setOnClickListener(v->{
            scanCode();
        }); */
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to turn on flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    private void getBooksInfo(String query) {

        // creating a new array list.
        bookInfoArrayList = new ArrayList<>();

        // below line is use to initialize
        // the variable for our request queue.
        mRequestQueue = Volley.newRequestQueue(activity_search.this);

        // below line is use to clear cache this
        // will be use when our data is being updated.
        mRequestQueue.getCache().clear();

        // below is the url for getting data from API in json format.
        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + query;//https://www.googleapis.com/books/v1/volumes?q=isbn:

        // below line we are  creating a new request queue.
        RequestQueue queue = Volley.newRequestQueue(activity_search.this);


        // below line is use to make json object request inside that we
        // are passing url, get method and getting json object. .

        JsonObjectRequest booksObjrequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
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
                        String printType = volumeObj.optString("printType");
                        String category = volumeObj.getJSONArray("authors").toString();
                        JSONObject saleInfo = volumeObj.getJSONObject("saleInfo");
                        String language = volumeObj.optString("language");
                        String canonicVolumeLink = volumeObj.optString("canonicalVolumeLink");
                        String saleability = saleInfo.optString("saleability");
                        boolean isEbook  = saleInfo.optBoolean("isEbook");
                        JSONObject accessInfo = volumeObj.getJSONObject("accessInfo");
                        String webReaderLink = accessInfo.optString("webReaderLink");
                        boolean pdf = accessInfo.getJSONObject("pdf").optBoolean("isAvailable");
                        String country = accessInfo.optString("country");
                        String viewability = accessInfo.optString("viewability");
                        String textSnippet = volumeObj.getJSONObject("searchInfo").optString("textSnippet");
                        String smallImageLink = volumeObj.getJSONObject("imageLinks").optString("smallThumbnail");
                        boolean epub = accessInfo.getJSONObject("epub").optBoolean("isAvailable");
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
                        String isbn13 = tv1.getText().toString() + "manual";//todo manual entfernen nach tests
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
                        BookInfo bookInfo = new BookInfo(title, subtitle, publisher, publishedDate, description, thumbnail, previewLink,
                                                        infoLink, buyLink, isbn13, printType, category, smallImageLink, language,
                                                        canonicVolumeLink, country, webReaderLink, textSnippet, saleability,
                                                        isEbook, viewability, pdf, epub, authorsArrayList, pageCount);

                        // below line is use to pass our modal
                        // class in our array list.
                        bookInfoArrayList.add(bookInfo);

                        // below line is use to pass our
                        // array list in adapter class.
                        BookAdapter adapter = new BookAdapter(bookInfoArrayList, activity_search.this);

                        // below line is use to add linear layout
                        // manager for our recycler view.
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity_search.this, RecyclerView.VERTICAL, false);
                        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.reclyer);

                        // in below line we are setting layout manager and
                        // adapter to our recycler view.
                        mRecyclerView.setLayoutManager(linearLayoutManager);
                        mRecyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    // displaying a toast message when we get any error from API
                    Toast.makeText(activity_search.this, "No Data Found" + e, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // also displaying error message in toast.
                Toast.makeText(activity_search.this, "Error found is " + error, Toast.LENGTH_SHORT).show();
            }
        });
        // at last we are adding our json object
        // request in our request queue.
        queue.add(booksObjrequest);
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(),result -> {
        if(result.getContents() != null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity_search.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
            tv1.setText(result.getContents());
        }
    });


}