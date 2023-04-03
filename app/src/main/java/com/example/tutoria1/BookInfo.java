package com.example.tutoria1;

import org.json.JSONObject;

import java.util.ArrayList;

public class BookInfo {

    // creating string, int and array list
    // variables for our book details
    private String title, subtitle, publisher, publishedDate, description, thumbnail, previewLink, infoLink, buyLink, isbn13,
    printType, category, smallImageLink, language, canonicVolumeLink, country, webReaderLink, textSnippet, saleability, viewability;
    private boolean isEbook, epub, pdf;
    private ArrayList<String> authors;
    private int pageCount;


    // creating getter and setter methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }
    public void setIsbn13(String isbn13){this.isbn13 = isbn13;}
    public String getIsbn13(){return isbn13;}

    public String JsonString()
    {
        return BookJson().toString();
    }
    public JSONObject BookJson()
    {
        return null;
    }

    // creating a constructor class for our BookInfo
    public BookInfo(String title, String subtitle, String publisher, String publishedDate, String description, String thumbnail, String previewLink,
                    String infoLink, String buyLink, String isbn13,
                    String printType, String category, String smallImageLink, String language, String canonicVolumeLink,
                    String country, String webReaderLink, String textSnippet,
                    String saleability, boolean isEbook, String viewability, boolean epub,
                    boolean pdf, ArrayList<String> authors, int pageCount) {

        this.title = title;
        this.subtitle = subtitle;
        this.publisher = publisher;
        this.description = description;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.thumbnail = thumbnail;
        this.previewLink = previewLink;
        this.infoLink = infoLink;
        this.buyLink = buyLink;
        this.isbn13 = isbn13;
        this.printType = printType;
        this.category = category;
        this.smallImageLink = smallImageLink;
        this.language = language;
        this.canonicVolumeLink = canonicVolumeLink;
        this.country = country;
        this.webReaderLink = webReaderLink;
        this.textSnippet = textSnippet;
        this.saleability = saleability;
        this.isEbook = isEbook;
        this.viewability = viewability;
        this.epub = epub;
        this.pdf = pdf;
        this.authors = authors;
        this.pageCount = pageCount;
    }
}