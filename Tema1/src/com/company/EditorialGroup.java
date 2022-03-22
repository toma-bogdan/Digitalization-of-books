package com.company;

public class EditorialGroup implements IPublishingArtifact{
    int ID;
    String name;
    Book[] books;

    public EditorialGroup(int ID, String name, Book[] books) {
        this.ID = ID;
        this.name = name;
        this.books = books;
    }
    public String Publish(){
        String info = new String("<xml>\n    <editorialGroup>\n        <ID>" + this.ID + "</ID>\n        <Name>"
                + this.name + "</Name>\n    </editorialGroup>\n    <books>\n");
        for(Book book : books){
            if(book != null){
                StringBuilder builder = new StringBuilder();
                for (String value : book.keywords) {
                    builder.append(value + "; ");
                }
                String text = builder.toString();
                text = text.substring(0, text.length() - 2);//String for keywords

                info += "        <book>\n            <title>" + book.name + "</title>\n            <subtitles>" + book.subtitles + "</subtitles>\n            " + "<ISBN>" + book.ISBN + "</ISBN>\n            " +"<pageCount>"
                        + book.pageCount + "</pagecount>\n            <keywords>" + text + "</keywords>\n            <languageID>" + book.languageID
                        + "</languageID>\n            <createdOn>" + book.createdOn + "</createdOn>\n            <authors>";

                for(Author author : book.authors){
                    if(author != null)
                        info += author.firstName + " " + author.lastName + " ";
                }
                info = info.substring(0,info.length() - 1);
                info += "</authors>\n        </book>\n";
            }
        }
        info += "    </books>\n</xml>";

        return info;
    }
}
