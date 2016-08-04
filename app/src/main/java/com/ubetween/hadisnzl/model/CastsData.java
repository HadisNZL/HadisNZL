package com.ubetween.hadisnzl.model;

import java.io.Serializable;

/**
 * @author hadis on 16.7.28.
 */
public class CastsData implements Serializable {


    /**
     * alt : https://movie.douban.com/celebrity/1054521/
     * avatars : {}
     * name : 蒂姆·罗宾斯
     * id : 1054521
     */

    private String alt;
    private String name;
    private String id;
    private AvatarsData avatars;

    public AvatarsData getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsData avatars) {
        this.avatars = avatars;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
