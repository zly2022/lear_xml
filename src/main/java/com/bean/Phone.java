package com.bean;

public class Phone {
    private Integer id;
    private String name;
    private Double price;
    private String ms;

    public Phone() {
    }

    public Phone(Integer id, String name, Double price, String ms) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ms = ms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", ms='" + ms + '\'' +
                '}';
    }
}
