package com.company;


public class Book implements IPublishingArtifact{
    int ID, pageCount, languageID;
    String name;
    String[] keywords;
    String ISBN, subtitles, createdOn;
    Author[] authors;

    public Book(int ID, String name, String subtitles, String ISBN, int pageCount, String[] keywords, int languageID, String createdOn, Author[] authors) {
        this.ID = ID;
        this.pageCount = pageCount;
        this.name = name;
        this.subtitles = subtitles;
        this.ISBN = ISBN;
        this.keywords = keywords;
        this.languageID = languageID;
        this.createdOn = createdOn;
        this.authors = authors;
    }
    public String Publish(){
        StringBuilder builder = new StringBuilder();
        for (String value : this.keywords) {
            builder.append(value + "; ");
        }
        String text = builder.toString();
        text = text.substring(0, text.length() - 2);
        String info = new String("<xml>\n    <title>" + this.name + "</title>\n    <subtitles>" + this.subtitles + "</subtitles>\n    <ISBN>"
                                + this.ISBN + "</ISBN>\n    " + "<pageCount>" + this.pageCount + "</pageCount>\n    "  + "<keywords>" + text + "</keywords>\n    <languageID>" + this.languageID
                                + "</languageID>\n    <createdOn>" + this.createdOn + "</createdOn>\n    <authors>");
        for(Author author : authors){
            if(author != null)
                info += author.firstName + " " + author.lastName + " ";
        }
        info = info.substring(0,info.length() - 1);
        info += "</authors>\n</xml>";
        return info;
    }

}
