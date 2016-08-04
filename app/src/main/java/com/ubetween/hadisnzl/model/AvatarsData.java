package com.ubetween.hadisnzl.model;

import java.io.Serializable;

/**
 * @author hadis on 16.7.28.
 */
public class AvatarsData implements Serializable {


    /**
     * small : https://img3.doubanio.com/img/celebrity/small/17525.jpg
     * large : https://img3.doubanio.com/img/celebrity/large/17525.jpg
     * medium : https://img3.doubanio.com/img/celebrity/medium/17525.jpg
     */

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
