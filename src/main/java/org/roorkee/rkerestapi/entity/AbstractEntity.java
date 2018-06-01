package org.roorkee.rkerestapi.entity;


import com.google.appengine.api.datastore.Entity;
import lombok.Data;
import org.roorkee.rkerestapi.util.RkeException;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

@Data
public abstract class AbstractEntity {

    @GStoreAttr
    private Long id;

    @GStoreAttr
    private Date created;

    @GStoreAttr
    private String ownerUserId;

    @GStoreAttr
    private String status;

    public Entity toGoogleDatastoreEntity(){

        Entity gDtaEntity = null;

        gDtaEntity = new Entity(getKeyKind(), this.getId());

        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field: fields){
            if (field.isAnnotationPresent(GStoreAttr.class)){
                Annotation annotation = field.getAnnotation(GStoreAttr.class);
                GStoreAttr gStoreAttr = (GStoreAttr) annotation;
                if (gStoreAttr.type().equals(Object.class)){
                    try {
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), this.getClass());
                        Object variableValue = propertyDescriptor.getReadMethod().invoke(this);
                        if (variableValue != null) gDtaEntity.setProperty(field.getName(), variableValue);
                    }
                    catch (IllegalAccessException e){
                        throw new RkeException(e);
                    }
                    catch (IntrospectionException e){
                        throw new RkeException(e);
                    }
                    catch (InvocationTargetException e){
                        throw new RkeException(e);
                    }
                }
                else{

                }
            }
        }

        return gDtaEntity;
    }

    public void setGEntity(Entity entity){

    }

    public abstract String getKeyKind();

}
