package com.company;

public class ShortUrlOutput {

    MapFiller mapFiller = new MapFiller();
    Shortener shortener = new Shortener();

    public String printShortUrl(String lUrl){
        StringBuilder sb = new StringBuilder(mapFiller.getUrlPrefix());
        sb.append(shortener.encode(lUrl));
        return sb.toString();
    }
}
