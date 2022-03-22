package com.company;

import java.util.Arrays;

public class PublishingRetailer {
    int ID;
    String name;
    IPublishingArtifact[] publishingArtifact;
    Countries[] countries;
    int dim = 0;

    public PublishingRetailer(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.publishingArtifact = new IPublishingArtifact[2];
    }


    public void setCountries(Countries[] countries) {
        this.countries = countries;
    }
    public void addPublishingArtifact(IPublishingArtifact publishingArtifact){
        if(dim == this.publishingArtifact.length){
            this.publishingArtifact = Arrays.copyOf(this.publishingArtifact,2 * this.publishingArtifact.length);
        }
        this.publishingArtifact[dim] = publishingArtifact;
        this.dim++;
    }
}
