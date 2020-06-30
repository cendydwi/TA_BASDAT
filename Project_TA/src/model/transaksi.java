/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author cendy
 */
public class transaksi {
    private Integer id_transaksi;
    private pegawai pegawai;
    private Date tanggal_transaksi;
    private Double harga_total;
    private ArrayList<detail_transaksi> arrDetail_Transaksi;
 
    public Integer getId_transaksi() {
        return id_transaksi;
    }

    public ArrayList<detail_transaksi> getArrDetail_Transaksi() {
        return arrDetail_Transaksi;
    }

    public void setArrDetail_Transaksi(ArrayList<detail_transaksi> arrDetail_Transaksi) {
        this.arrDetail_Transaksi = arrDetail_Transaksi;
    }

    public void setId_transaksi(Integer id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public Date getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(Date tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }
    
    public Double getHarga_total() {
        return harga_total;
    }

    public void setHarga_total(Double harga_total) {
        this.harga_total = harga_total;
    }    
}
