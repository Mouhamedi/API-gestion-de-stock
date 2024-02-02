package com.gestionstock.Gestion.de.stock.services.impl;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.gestionstock.Gestion.de.stock.services.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class FilckrServiceImpl implements FlickrService {

    private Flickr flickr;

    @Autowired
    public FilckrServiceImpl(Flickr flickr) {
        this.flickr = flickr;
    }

    // methode pour sauvegarser les photos
    @Override
    public String savePhoto(InputStream photo, String titre) throws FlickrException {

        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(titre);

        String photoId = flickr.getUploader().upload(photo, uploadMetaData);

        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();

    }
}
