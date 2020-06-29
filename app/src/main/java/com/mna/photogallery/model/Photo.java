package com.mna.photogallery.model;

import java.io.Serializable;


public class Photo implements Serializable {

    private String id;

    private String description;

    private String name;

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String location;

    /**
     *
     * @param location
     */
    public void setLocation(String location)
    {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    private String photoURL;

    /**
     *
     * @param photoURL
     */
    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getPhotoURL() {
        return this.photoURL;
    }

    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    private String likes;

    public String getLikes() {
        return likes;
    }

    /**
     *
     * @param likes
     */
    public void setLikes(String likes) {
        this.likes = likes;
    }

    private String created_at;

    public String getCreated_at() {
        return created_at;
    }

    /**
     *
     * @param created_at
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDate()
    {
        if(this.created_at != null)
        {
            return this.created_at;
        }

        return "";
    }
}
