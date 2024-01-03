package com.example.cityswift.dto;

import java.io.Serial;
import java.io.Serializable;

public class CreatePackageDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int height;
    private int width;
    private int depth;
    private int weight;

    public CreatePackageDTO(int height, int width, int depth, int weight) {
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.weight = weight;
    }

    public CreatePackageDTO() {
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
