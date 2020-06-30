/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author cendy
 */
public class barang {
    private pegawai pegawai;
    private Integer id_barang;
    private String nama_barang;
    private Integer harga_satuan;
    private Integer stok;
    
    public pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(pegawai pegawai) {
        this.pegawai = pegawai;
    }
    
    public Integer getId_barang() {
        return id_barang;
    }

    public void setId_barang(Integer id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public Integer getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(Integer harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }    
}
