package org.roorkee.rkerestapi.entity;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

import java.util.Map;

public class Content implements IEntity{

    private long id;
    private String imageURL;
    private String description;
    private String fullText;

    public Content() {
    }

    public Content(long id, String imageURL, String description, String fullText) {
        this.id = id;
        this.imageURL = imageURL;
        this.description = description;
        this.fullText = fullText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    @Override
    public String getKeyKind() {
        return "Content";
    }

    @Override
    public Entity toGoogleDatastoreEntity() {
        Entity gDtaEntity = new Entity(getKeyKind());
        gDtaEntity.setProperty("imageURL", this.imageURL);
        gDtaEntity.setProperty("description", this.description);
        gDtaEntity.setProperty("fullText", new Text(this.fullText));
        return gDtaEntity;
    }

    @Override
    public void setGEntity(Entity entity) {
        this.id = entity.getKey().getId();
        Map<String, Object> entityProperties = entity.getProperties();
        this.imageURL = (String) entityProperties.get("imageURL");
        this.description = (String) entityProperties.get("description");
        this.fullText = ((Text) entityProperties.get("fullText")).toString();
    }

    @Override
    public String toString() {
        return "Content{" +
                "id='" + id + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", description='" + description + '\'' +
                ", fullText='" + fullText + '\'' +
                '}';
    }
}