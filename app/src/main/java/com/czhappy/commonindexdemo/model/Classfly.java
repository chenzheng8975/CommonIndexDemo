package com.czhappy.commonindexdemo.model;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/9/12 0012
 * Time: 16:09
 */
public class Classfly {

    public Classfly(String id, String name, int drawable) {
        this.id = id;
        this.name = name;
        this.drawable = drawable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    private String id;
    private String name;
    private int drawable;




}
