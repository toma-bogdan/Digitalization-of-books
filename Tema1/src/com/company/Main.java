package com.company;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Main {
    Author[] authors = Initialize_authors();
    Book[] books = InitializeBooks(authors);
    Language[] languages = InitializeLanguages();
    EditorialGroup[] editorialGroups = InitializeEditorialGroups(books);
    PublishingBrand[] publishingBrands = InitializePublishingBrand(books);
    Countries[] countries = InitializeCountries();
    PublishingRetailer[] publishingRetailers = InitializePublishingRetailers(books,publishingBrands,editorialGroups,countries);
    public static void main(String[] args) {

        Administration administration = new Administration();
        System.out.println("Apeluri pentru functia getBooksForPublishingRetailerID:");
        Book[] booksForPublishingRetailerID = administration.getBooksForPublishingRetailerID(1);
        System.out.println("Pentru retailer-ul 1:");
        administration.printbooks(booksForPublishingRetailerID);
        System.out.println();
        System.out.println("Pentru retailer-ul 2:");
        booksForPublishingRetailerID = administration.getBooksForPublishingRetailerID(2);
        administration.printbooks(booksForPublishingRetailerID);
        System.out.println();
        System.out.println("Pentru retailer-ul 3:");
        booksForPublishingRetailerID = administration.getBooksForPublishingRetailerID(3);
        administration.printbooks(booksForPublishingRetailerID);
        System.out.println();
        System.out.println("Pentru retailer-ul 4:");
        booksForPublishingRetailerID = administration.getBooksForPublishingRetailerID(4);
        administration.printbooks(booksForPublishingRetailerID);
        System.out.println();
        System.out.println("Pentru retailer-ul 5:");
        booksForPublishingRetailerID = administration.getBooksForPublishingRetailerID(5);
        administration.printbooks(booksForPublishingRetailerID);

        System.out.println();
        System.out.println("Apeluri pentru functia getLanguagesForPublishingRetailerID");
        System.out.println("Pentru retailer-ul 1:");
        Language[] languagesForPublishingRetailer = administration.getLanguagesForPublishingRetailerID(1);
        administration.printLanguages(languagesForPublishingRetailer);

        System.out.println();
        System.out.println("Apeluri pentru functia getLanguagesForPublishingRetailerID");
        System.out.println("Pentru cartea cu ID-ul 204:");
        Countries[] countries = administration.getCountriesForBookID(204);
        administration.printCountries(countries);
        System.out.println();
        System.out.println("Pentru cartea cu ID-ul 224");
        countries = administration.getCountriesForBookID(224);
        administration.printCountries(countries);
        System.out.println();
        System.out.println("Pentru cartea cu ID-ul 262");
        countries = administration.getCountriesForBookID(262);
        administration.printCountries(countries);
        System.out.println();
        System.out.println("Pentru cartea cu ID-ul 275");
        countries = administration.getCountriesForBookID(275);
        administration.printCountries(countries);
        System.out.println();
        System.out.println("Pentru cartea cu ID-ul 440");
        countries = administration.getCountriesForBookID(440);
        administration.printCountries(countries);

        System.out.println();
        System.out.println("Apeluri pentru functia getCommonBooksForRetailerIDs");
        System.out.println("Cartile comune intre retailer-ul 1 si retailer-ul 2:");
        Book[] commonBooks = administration.getCommonBooksForRetailerIDs(1,2);
        administration.printbooks(commonBooks);
        System.out.println();
        System.out.println("Cartile comune intre retailer-ul 1 si retailer-ul 3:");
        commonBooks = administration.getCommonBooksForRetailerIDs(1,3);
        administration.printbooks(commonBooks);
        System.out.println();
        System.out.println("Cartile comune intre retailer-ul 3 si retailer-ul 4:");
        commonBooks = administration.getCommonBooksForRetailerIDs(3,4);
        administration.printbooks(commonBooks);
        System.out.println();
        System.out.println("Cartile comune intre retailer-ul 4 si retailer-ul 5:");
        commonBooks = administration.getCommonBooksForRetailerIDs(4,5);
        administration.printbooks(commonBooks);
        System.out.println();
        System.out.println("Cartile comune intre retailer-ul 29 si retailer-ul 30:");
        commonBooks = administration.getCommonBooksForRetailerIDs(29,30);
        administration.printbooks(commonBooks);

        System.out.println();
        System.out.println("Apeluri pentru functia getAllBooksForRetailersIDs");
        System.out.println("Uniunea cartilor intre retailer-ul 1 si retailer-ul 2");
        Book[] allBooks = administration.getAllBooksForRetailerIDs(1,2);
        administration.printbooks(allBooks);
        System.out.println();
        System.out.println("Uniunea cartilor intre retailer-ul 2 si retailer-ul 3");
        allBooks = administration.getAllBooksForRetailerIDs(2,3);
        administration.printbooks(allBooks);
        System.out.println();
        System.out.println("Uniunea cartilor intre retailer-ul 3 si retailer-ul 4");
        allBooks = administration.getAllBooksForRetailerIDs(3,4);
        administration.printbooks(allBooks);
        System.out.println();
        System.out.println("Uniunea cartilor intre retailer-ul 7 si retailer-ul 8");
        allBooks = administration.getAllBooksForRetailerIDs(7,8);
        administration.printbooks(allBooks);
        System.out.println();
        System.out.println("Uniunea cartilor intre retailer-ul 10 si retailer-ul 11");
        allBooks = administration.getAllBooksForRetailerIDs(10,11);
        administration.printbooks(allBooks);



    }

    Author[] Initialize_authors(){
        File file = new File("init/authors.in");
        Author[] authors = new Author[2];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int length = 0;
            while ((line = br.readLine()) != null) {
                String[] inputs = line.split("###");
                if(inputs[0].equals("Id")){
                    continue;
                }
                if(length == authors.length){
                    authors = Arrays.copyOf(authors,2 * authors.length);
                }
                authors[length] = new Author(Integer.parseInt(inputs[0]),inputs[1],inputs[2]);

                length++;
            }
        }catch (Exception f){
            System.out.println("File not found");
        }
        return authors;
    }

    Book[] InitializeBooks(Author[] authors){
        File file = new File("init/books.in");
        File file1 = new File("init/books-authors.in");
        Book[] books = new Book[2];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int length = 0;
            while ((line = br.readLine()) != null) {
                String[] inputs = line.split("###");
                Author[] authors_book = new Author[100];
                if(inputs[0].equals("ID")){
                    continue;
                }
                if(length == books.length){
                    books = Arrays.copyOf(books,2 * books.length);
                }

                try(BufferedReader br1 = new BufferedReader(new FileReader(file1))) {
                    String line_ids;
                    while((line_ids = br1.readLine()) != null){
                        String[] inputs_ids = line_ids.split("###");
                        if(inputs_ids[0].equals("BookId"))
                            continue;
                        if(inputs[0].equals(inputs_ids[0])) {
                            int i = 0;
                            for(Author author : authors){
                                if(author != null && author.ID == Integer.parseInt(inputs_ids[1])){
                                    authors_book[i] = author;
                                    i++;
                                }
                            }
                        }
                    }
                }catch (Exception f){
                    System.out.println("Books-authors file not found");
                }
                String[] keywords = inputs[5].split(";");

                books[length] = new Book(Integer.parseInt(inputs[0]),inputs[1],inputs[2],inputs[3],Integer.parseInt(inputs[4]),keywords,Integer.parseInt(inputs[6]),inputs[7],authors_book);
                length++;
            }
        }catch (Exception f){
            System.out.println("Books file not found");
        }
        return books;
    }
    Language[] InitializeLanguages(){
        File file = new File("init/languages.in");
        Language[] languages = new Language[2];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int length = 0;
            while ((line = br.readLine()) != null) {
                String[] inputs = line.split("###");
                if(inputs[0].equals("Id")){
                    continue;
                }
                if(length == languages.length){
                    languages = Arrays.copyOf(languages,2 * languages.length);
                }
                languages[length] = new Language(Integer.parseInt(inputs[0]),inputs[1],inputs[2]);

                length++;
            }
        }catch (Exception f){
            System.out.println("File not found");
        }
        return languages;
    }
    EditorialGroup[] InitializeEditorialGroups(Book[] books){
        File file = new File("init/editorial-groups.in");
        File file_books = new File("init/editorial-groups-books.in");
        EditorialGroup[] editorialGroups = new EditorialGroup[2];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int length = 0;
            while ((line = br.readLine()) != null) {
                String[] inputs = line.split("###");
                Book[] books_eg = new Book[2];
                if(inputs[0].equals("Id")){
                    continue;
                }
                if(length == editorialGroups.length){
                    editorialGroups = Arrays.copyOf(editorialGroups,2 * editorialGroups.length);
                }
                try(BufferedReader br1 = new BufferedReader(new FileReader(file_books))) {
                    String line_ids;
                    while((line_ids = br1.readLine()) != null){
                        String[] inputs_ids = line_ids.split("###");

                        if(inputs_ids[0].equals("GroupId"))
                            continue;

                        if(inputs[0].equals(inputs_ids[0])) {
                            int i = 0;
                            for(Book book : books){
                                if(book != null && book.ID == Integer.parseInt(inputs_ids[1])){
                                    if(i == books_eg.length)
                                        books_eg = Arrays.copyOf(books_eg,2 * books_eg.length);

                                    books_eg[i] = book;
                                    i++;
                                }
                            }
                        }

                    }
                }catch (Exception f){
                    System.out.println("EditorialGroup-books file not found");
                }
                editorialGroups[length] = new EditorialGroup(Integer.parseInt(inputs[0]),inputs[1],books_eg);
                length++;
            }
        }catch (Exception f){
            System.out.println("PublisherBrand file not found");
        }
        return editorialGroups;
    }

    PublishingBrand[] InitializePublishingBrand(Book[] books){
        File file = new File("init/publishing-brands.in");
        File file_books = new File("init/publishing-brands-books.in");
        PublishingBrand[] publishingBrands = new PublishingBrand[2];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int length = 0;
            while ((line = br.readLine()) != null) {
                String[] inputs = line.split("###");
                Book[] books_pb = new Book[2];
                if(inputs[0].equals("Id")){
                    continue;
                }
                if(length == publishingBrands.length){
                    publishingBrands = Arrays.copyOf(publishingBrands,2 * publishingBrands.length);
                }
                try(BufferedReader br1 = new BufferedReader(new FileReader(file_books))) {
                    String line_ids;
                    while((line_ids = br1.readLine()) != null){
                        String[] inputs_ids = line_ids.split("###");

                        if(inputs_ids[0].equals("PublisherId"))
                            continue;

                        if(inputs[0].equals(inputs_ids[0])) {
                            int i = 0;
                            for(Book book : books){
                                if(book != null && book.ID == Integer.parseInt(inputs_ids[1])){
                                    if(i == books_pb.length)
                                        books_pb = Arrays.copyOf(books_pb,2 * books_pb.length);

                                    books_pb[i] = book;
                                    i++;
                                }
                            }
                        }
                    }
                }catch (Exception f){
                    System.out.println("PublisherBrands-books file not found");
                }
                publishingBrands[length] = new PublishingBrand(Integer.parseInt(inputs[0]),inputs[1],books_pb);
                length++;
            }
        }catch (Exception f){
            System.out.println("Editorial-group file not found");
        }

        return publishingBrands;
    }

    Countries[] InitializeCountries(){
        File file = new File("init/countries.in");
        Countries[] countries = new Countries[2];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int length = 0;
            while ((line = br.readLine()) != null) {
                String[] inputs = line.split("###");
                if(inputs[0].equals("ID"))
                    continue;

                if(length == countries.length){
                    countries = Arrays.copyOf(countries,2 * countries.length);
                }
                countries[length] = new Countries(Integer.parseInt(inputs[0]),inputs[1]);
                length++;
            }
        }catch (Exception f){
            System.out.println("Country file not found");
        }
        return countries;
    }
    PublishingRetailer[] InitializePublishingRetailers(Book[] books, PublishingBrand[] publishingBrands, EditorialGroup[] editorialGroups, Countries[] countries){
        File file = new File("init/publishing-retailers.in");
        PublishingRetailer[] publishingRetailers = new PublishingRetailer[2];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int length = 0;
            while ((line = br.readLine()) != null) {
                String[] inputs = line.split("###");
                if(inputs[0].equals("Id"))
                    continue;

                if(length == publishingRetailers.length){
                    publishingRetailers = Arrays.copyOf(publishingRetailers,2 * publishingRetailers.length);
                }
                publishingRetailers[length] = new PublishingRetailer(Integer.parseInt(inputs[0]),inputs[1]);
                length++;
            }
        }catch (Exception f){
            System.out.println("Publishing-retailers file not found");
        }
        File file_countries = new File("init/publishing-retailers-countries.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file_countries))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] inputs_countries = line.split("###");
                Countries[] countries_retailers = new Countries[2];
                if(inputs_countries[0].equals("RetailerId"))
                    continue;
                for(PublishingRetailer publishingRetailer : publishingRetailers){
                    if(publishingRetailer != null && publishingRetailer.ID == Integer.parseInt(inputs_countries[0])){
                        int i = 0;
                        for(Countries country : countries){
                            if(country != null && country.ID == Integer.parseInt(inputs_countries[1])){
                                if(i == inputs_countries.length)
                                    countries_retailers = Arrays.copyOf(countries_retailers,2 * countries_retailers.length);

                                countries_retailers[i] = country;
                                i++;
                            }
                        }
                        publishingRetailer.setCountries(countries_retailers);
                    }
                }
            }
        }catch (Exception f){
            System.out.println("Publishing-retailers-countries file not found");
        }
        File file_books = new File("init/publishing-retailers-books.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file_books))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] inputs_countries = line.split("###");
                if(inputs_countries[0].equals("RetailerId"))
                    continue;
                for(PublishingRetailer publishingRetailer : publishingRetailers){
                    if(publishingRetailer != null && publishingRetailer.ID == Integer.parseInt(inputs_countries[0])){
                        for(Book book : books){
                            if(book != null && book.ID == Integer.parseInt(inputs_countries[1])){
                                publishingRetailer.addPublishingArtifact(book);
                            }
                        }
                    }
                }
            }
        }catch (Exception f){
            System.out.println("Publishing-retailers-books file not found");
        }
        File file_eg = new File("init/publishing-retailers-editorial-groups.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file_eg))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] inputs_eg = line.split("###");
                if(inputs_eg[0].equals("RetailerId"))
                    continue;
                for(PublishingRetailer publishingRetailer : publishingRetailers){
                    if(publishingRetailer != null && publishingRetailer.ID == Integer.parseInt(inputs_eg[0])){
                        for(EditorialGroup editorialGroup : editorialGroups){
                            if(editorialGroup != null && editorialGroup.ID == Integer.parseInt(inputs_eg[1])){
                                publishingRetailer.addPublishingArtifact(editorialGroup);
                            }
                        }
                    }
                }
            }
        }catch (Exception f){
            System.out.println("Publishing-retailers-books file not found");
        }
        File file_pb = new File("init/publishing-retailers-publishing-brands.in");
        try (BufferedReader br = new BufferedReader(new FileReader(file_pb))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] inputs_pb = line.split("###");
                if(inputs_pb[0].equals("RetailerId"))//skips the first line of the file
                    continue;
                for(PublishingRetailer publishingRetailer : publishingRetailers){
                    if(publishingRetailer != null && publishingRetailer.ID == Integer.parseInt(inputs_pb[0])){
                        for(PublishingBrand publishingBrand : publishingBrands){
                            if(publishingBrand != null && publishingBrand.ID == Integer.parseInt(inputs_pb[1])){
                                publishingRetailer.addPublishingArtifact(publishingBrand);
                            }
                        }
                    }
                }
            }
        }catch (Exception f){
            System.out.println("Publishing-retailers-books file not found");
        }
        return publishingRetailers;
    }
}
class Administration {
    public Book[] getBooksForPublishingRetailerID(int publishingRetailerID) {
        Main main = new Main();
        Book[] books = new Book[0];//common books

        for (PublishingRetailer publishingRetailer : main.publishingRetailers) {
            if (publishingRetailer.ID == publishingRetailerID) {
                books = new Book[publishingRetailer.publishingArtifact.length + 5];
                int i = 0;

                for (IPublishingArtifact iPublishingArtifact : publishingRetailer.publishingArtifact) {
                    if (iPublishingArtifact != null) {
                        if (iPublishingArtifact instanceof Book) {
                            books[i] = (Book) iPublishingArtifact;
                            i++;
                        }
                        if (iPublishingArtifact instanceof EditorialGroup) {
                            int ok = 0;
                            for (Book book : ((EditorialGroup) iPublishingArtifact).books) {
                                if (book != null) {
                                    for (Book book1 : books) {
                                        if (book1 != null && book.ID == book1.ID) {
                                            ok = 1;
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        books[i] = book;
                                        i++;
                                    }
                                }
                            }
                        }
                        if (iPublishingArtifact instanceof PublishingBrand) {
                            int ok = 0;
                            for (Book book : ((PublishingBrand) iPublishingArtifact).books) {
                                if (book != null) {
                                    for (Book book1 : books) {
                                        if (book1 != null && book.ID == book1.ID) {
                                            ok = 1;
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        books[i] = book;
                                        i++;
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
        return books;
    }

    public Language[] getLanguagesForPublishingRetailerID(int publishingRetailerID) {
        Main main = new Main();
        Book[] books = getBooksForPublishingRetailerID(publishingRetailerID);
        Language[] languages = new Language[books.length];
        int i = 0;
        for (Book book : books) {
            if (book != null) {
                int ok = 0;
                for (Language lgs : main.languages) {
                    if (lgs != null && lgs.ID == book.languageID) {
                        for (Language l : languages) {
                            if (l != null && l.ID == lgs.ID) {
                                ok = 1;
                                break;
                            }
                        }
                        if (ok == 0) {
                            languages[i] = lgs;
                            i++;
                        }
                        break;
                    }
                }
            }
        }
        return languages;
    }
    public Countries[] getCountriesForBookID(int bookID){
        Main main = new Main();
        Countries[] countries = new Countries[main.countries.length];
        int i = 0;
        for(PublishingRetailer publishingRetailer : main.publishingRetailers){
            if (publishingRetailer != null) {
                Book[] books = getBooksForPublishingRetailerID(publishingRetailer.ID);
                for (Book book : books){
                    if(book != null && book.ID == bookID){
                        for(Countries c : publishingRetailer.countries){
                            if(c != null){
                                countries[i] = c;
                                i++;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return countries;
    }
    public Book[] getCommonBooksForRetailerIDs(int retailerID1, int retailerID2){
        Book[] books_ret1 = getBooksForPublishingRetailerID(retailerID1);
        Book[] books_ret2 = getBooksForPublishingRetailerID(retailerID2);
        Book[] books = new Book[books_ret1.length + books_ret2.length];
        int i = 0;

        for(Book book1 : books_ret1){
            if(book1 == null)
                break;
            for(Book book2 : books_ret2){
                if(book2 == null)
                    break;
                if(book1.ID == book2.ID){
                    books[i] = book1;
                    i++;
                }
            }
        }
        return books;
    }
    public Book[] getAllBooksForRetailerIDs (int retailerID1, int retailerID2){
        Book[] books_ret1 = getBooksForPublishingRetailerID(retailerID1);
        Book[] books_ret2 = getBooksForPublishingRetailerID(retailerID2);
        Book[] books = new Book[books_ret1.length + books_ret2.length];
        int i = 0;
        for(Book b1 : books_ret1){
            books[i] = b1;
            i++;
        }
        for(Book b2 : books_ret2){
            if(b2 != null) {
                int ok = 0;
                for (Book book : books) {
                    if (book != null && b2.ID == book.ID) {
                        ok = 1;
                        break;
                    }
                }
                if (ok == 0) {
                    books[i] = b2;
                    i++;
                }
            }
        }
        return books;
    }
    public void printbooks(Book[] books){
        for(Book book : books){
            if(book != null)
                System.out.println(book.name + " " + book.ID + " ");
        }
    }
    public void printLanguages(Language[] languages){
        for(Language language : languages){
            if(language != null)
                System.out.println(language.ID + " " + language.code+ " " + language.name);
        }
    }
    public void printCountries(Countries[] countries){
        for(Countries country : countries){
            if(country != null)
                System.out.println(country.ID + " " + country.countryCode);
        }
    }
}