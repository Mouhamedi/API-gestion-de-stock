package com.gestionstock.Gestion.de.stock.services;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface FlickrService {

    String savePhoto(InputStream photo, String titre) throws FlickrException;
}
