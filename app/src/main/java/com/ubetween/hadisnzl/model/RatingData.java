package com.ubetween.hadisnzl.model;

import java.io.Serializable;

/**
 * @author hadis on 16.7.28.
 */
public class RatingData implements Serializable {
    /**
     * max : 10
     * average : 9.2
     * stars : 45
     * min : 0
     */

    private int max;
    private double average;
    private String stars;
    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }


//    "rating": {
//        "max": 10,
//                "average": 9.2,
//                "stars": "45",
//                "min": 0
//    }


}
