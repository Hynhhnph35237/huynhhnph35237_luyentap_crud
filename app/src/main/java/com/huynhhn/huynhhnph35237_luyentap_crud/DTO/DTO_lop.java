package com.huynhhn.huynhhnph35237_luyentap_crud.DTO;

public class DTO_lop {
    private int id ;
    private String tenLop;
    private int siSo;
    private int idKhoa;
    private String tenKhoa;

    public DTO_lop() {
    }

    public DTO_lop(int id, String tenLop, int siSo, int idKhoa, String tenKhoa) {
        this.id = id;
        this.tenLop = tenLop;
        this.siSo = siSo;
        this.idKhoa = idKhoa;
        this.tenKhoa = tenKhoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public int getSiSo() {
        return siSo;
    }

    public void setSiSo(int siSo) {
        this.siSo = siSo;
    }

    public int getIdKhoa() {
        return idKhoa;
    }

    public void setIdKhoa(int idKhoa) {
        this.idKhoa = idKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }
}
