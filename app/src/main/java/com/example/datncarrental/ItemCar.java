package com.example.datncarrental;

public class ItemCar {
    private String name;
    private Boolean rate;
    private String status;
    private int seat;
    private int gate;
    private String shift;
    private String imagecar;
    private int price;

    public ItemCar(String name, Boolean rate, String status, int seat, int gate, String shift, String imagecar, int price) {
        this.name = name;
        this.rate = rate;
        this.status = status;
        this.seat = seat;
        this.gate = gate;
        this.shift = shift;
        this.imagecar = imagecar;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRate() {
        return rate;
    }

    public void setRate(Boolean rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getGate() {
        return gate;
    }

    public void setGate(int gate) {
        this.gate = gate;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getImagecar() {
        return imagecar;
    }

    public void setImagecar(String imagecar) {
        this.imagecar = imagecar;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
