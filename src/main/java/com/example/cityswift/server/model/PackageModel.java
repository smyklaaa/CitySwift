package com.example.cityswift.server.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PackageModel implements Serializable {
    private Integer id;
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal depth;
    private BigDecimal weight;

    public PackageModel(BigDecimal height, BigDecimal width, BigDecimal depth, BigDecimal weight) {
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.weight = weight;
    }

    public PackageModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getDepth() {
        return depth;
    }

    public void setDepth(BigDecimal depth) {
        this.depth = depth;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
