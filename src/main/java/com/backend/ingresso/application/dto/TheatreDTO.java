package com.backend.ingresso.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TheatreDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("title")
    private String Title;
    @JsonProperty("description")
    private String Description;
    @JsonProperty("data")
    private Timestamp Data;
    @JsonProperty("location")
    private String Location;
    @JsonProperty("typeOfAttraction")
    private String TypeOfAttraction;
    @JsonProperty("category")
    private String Category;
    @JsonProperty("publicId")
    private String PublicId;
    @JsonProperty("imgUrl")
    private String ImgUrl;

    public TheatreDTO(UUID id, String title, String description,
                      Timestamp data, String location, String typeOfAttraction,
                      String category, String publicId, String imgUrl) {
        Id = id;
        Title = title;
        Description = description;
        Data = data;
        Location = location;
        TypeOfAttraction = typeOfAttraction;
        Category = category;
        PublicId = publicId;
        ImgUrl = imgUrl;
    }

    public TheatreDTO(UUID id, String title) {
        Id = id;
        Title = title;
    }

    public UUID getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public Timestamp getData() {
        return Data;
    }

    public String getLocation() {
        return Location;
    }

    public String getTypeOfAttraction() {
        return TypeOfAttraction;
    }

    public String getCategory() {
        return Category;
    }

    public String getPublicId() {
        return PublicId;
    }

    public String getImgUrl() {
        return ImgUrl;
    }
}
