/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class detail_transaksi {
    private barang barang;
    private transaksi transaksi;
    private Integer jumlah;

    public barang getBarang() {
        return barang;
    }

    public void setBarang(barang barang) {
        this.barang = barang;
    }

    public transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }   
    
}
