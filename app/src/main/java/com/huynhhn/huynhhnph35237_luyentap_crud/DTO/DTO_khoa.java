package com.huynhhn.huynhhnph35237_luyentap_crud.DTO;

public class DTO_khoa {
    private int id;
    private String tenKhoa;

    public DTO_khoa() {
    }

    public DTO_khoa(int id, String tenKhoa) {
        this.id = id;
        this.tenKhoa = tenKhoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }
}
