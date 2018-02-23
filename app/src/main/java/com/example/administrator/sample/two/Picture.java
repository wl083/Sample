package com.example.administrator.sample.two;

/**
 * Created by shizi on 2016/10/23 0023.
 */

public class Picture {
    private String path;
    private String name;

    public Picture(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
