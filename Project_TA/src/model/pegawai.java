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
public class pegawai {
    private Integer id_pegawai;
    private String username;
    private String password;
    private String nama;
    private String alamat;
    private Integer no_telp;
    private Integer role;

    public Integer getId_pegawai() {
        return id_pegawai;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public Integer getNo_telp() {
        return no_telp;
    }

    public Integer getRole() {
        return role;
    }

    public void setId_pegawai(Integer id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNo_telp(Integer no_telp) {
        this.no_telp = no_telp;
    }

    public void setRole(Integer role) {
        this.role = role;
    }


}
