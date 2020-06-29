package com.mna.photogallery.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mna.photogallery.model.Photo;
import com.mna.photogallery.model.Photos;

import java.lang.reflect.Type;

/**
 * Cutom Deserilaizer will help us to connect to different backend server
 * Each 3rd Party can have their own implementations
 */
public class JsonPhotosDeserializer implements JsonDeserializer<Photos> {
    /**
     *
     * @param json
     * @param typeOfT
     * @param context
     * @return
     * @throws JsonParseException
     */
    @Override
    public Photos deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Photos photos = new Photos();
        if (json.isJsonArray()) {
            JsonArray jsonArray = json.getAsJsonArray();
            for(JsonElement element:jsonArray) {
                Photo photo = getPhoto(element);
                photos.addPhoto(photo);
            }
        }
        else  if (json.isJsonObject()) {
            JsonElement element = json;
            photos.setPhotoDetail(getPhoto(element));
        }
        return photos;
    }

    /**
     *
     * @param element
     * @return
     */
    public Photo getPhoto(JsonElement element)
    {
        Photo photo = new Photo();
        String id = element.getAsJsonObject().get("id").getAsString();
        photo.setId(id);
        String description = "";
        if(!element.getAsJsonObject().get("description").isJsonNull())
            description = element.getAsJsonObject().get("description").getAsString();
        photo.setDescription(description);
        String created_at = "";
        if(!element.getAsJsonObject().get("created_at").isJsonNull())
            created_at = element.getAsJsonObject().get("created_at").getAsString();
        photo.setCreated_at(created_at);
        String likes = "";
        if(!element.getAsJsonObject().get("likes").isJsonNull())
            likes = element.getAsJsonObject().get("likes").getAsString();
        photo.setLikes(likes);
        String small = element.getAsJsonObject().get("urls").getAsJsonObject().get("small").getAsString();
        photo.setPhotoURL(small);
        String name = "";
        String location = "";
        if(element.getAsJsonObject().get("user") != null
                && element.getAsJsonObject().get("user") instanceof JsonObject
                && element.getAsJsonObject().get("user").getAsJsonObject() != null) {
            JsonObject user = element.getAsJsonObject().get("user").getAsJsonObject();
            if(user.get("name") != null && !user.get("name").isJsonNull() && user.get("name").getAsString() != null) {
                name = user.get("name").getAsString();
            }
            if(user.get("location") != null && !user.get("location").isJsonNull() && user.get("location").getAsString() != null) {
                location = user.get("location").getAsString();
            }
        }
        photo.setName(name);
        photo.setLocation(location);

        return photo;
    }
}
