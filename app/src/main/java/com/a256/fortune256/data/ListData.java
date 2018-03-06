package com.a256.fortune256.data;

/**
 * Created by de165 on 2018/2/5.
 */

public class ListData {
    private int image_index = 0;
    private String text = "";

    public ListData(int image_index, String text) {
        this.image_index = image_index;
        this.text = text;
    }

    public int getImage_index() {
        return image_index;
    }

    public void setImage_index(int image_index) {
        this.image_index = image_index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "data:{" +
                "image_index=" + image_index +
                ", text='" + text + '\'' +
                '}';
    }
}
