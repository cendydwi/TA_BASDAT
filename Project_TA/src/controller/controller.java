/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.koneksi;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.detail_transaksi;
import model.barang;
import model.pegawai;
import model.transaksi;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class controller {

    koneksi koneksi;
    ArrayList<pegawai> arrPegawai;
    ArrayList<barang> arrBarang;
    ArrayList<transaksi> arrTransaksi;

    public controller() {
        this.koneksi = new koneksi();
        this.arrPegawai = new ArrayList<>();
        this.arrBarang = new ArrayList<>();
        this.arrTransaksi = new ArrayList<>();

    }

    public ArrayList<pegawai> getDataPegawai() throws SQLException {
        this.arrPegawai.clear();
        ResultSet rs = this.koneksi.GetData("SELECT * FROM PEGAWAI ORDER BY ID_PEGAWAI ASC");
        while (rs.next()) {
            pegawai p = new pegawai();
            p.setId_pegawai(rs.getInt("ID_PEGAWAI"));
            p.setUsername(rs.getString("USERNAME"));
            p.setPassword(rs.getString("PASSWORD"));
            p.setNama(rs.getString("NAMA"));
            p.setAlamat(rs.getString("ALAMAT"));
            p.setNo_telp(rs.getInt("NO_TELP"));
            p.setRole(rs.getInt("ROLE"));

            this.arrPegawai.add(p);
        }
        return this.arrPegawai;
    }

    public ArrayList<barang> getDataBarang() throws SQLException {
        this.arrBarang.clear();
        ResultSet rs = this.koneksi.GetData("SELECT * FROM DATA_BARANG");
        while (rs.next()) {
            pegawai pegawai = new pegawai();
            pegawai.setId_pegawai(rs.getInt("ID_PEGAWAI"));
            pegawai.setUsername(rs.getString("USERNAME_PEGAWAI"));
            pegawai.setPassword(rs.getString("PASSWORD_PEGAWAI"));
            pegawai.setNama(rs.getString("NAMA_PEGAWAI"));
            pegawai.setAlamat(rs.getString("ALAMAT_PEGAWAI"));
            pegawai.setNo_telp(rs.getInt("NO_TELP_PEGAWAI"));
            pegawai.setRole(rs.getInt("ROLE_PEGAWAI"));
            
            barang p = new barang();
            p.setPegawai(pegawai);
            p.setId_barang(rs.getInt("ID_BARANG"));
            p.setNama_barang(rs.getString("NAMA_BARANG"));
            p.setHarga_satuan(rs.getInt("HARGA_SATUAN"));
            p.setStok(rs.getInt("STOK_BARANG"));

            this.arrBarang.add(p);
        }
        return this.arrBarang;
    }
    
    public ArrayList<transaksi> getDataTransaksi() throws SQLException{
        this.arrTransaksi.clear();
        ResultSet rs = this.koneksi.GetData("SELECT * FROM DATA_TRANSAKSI");
        while (rs.next()) {
            
            pegawai pegawai = new pegawai();
            pegawai.setId_pegawai(rs.getInt("ID_PEGAWAI"));
            pegawai.setUsername(rs.getString("USERNAME_PEGAWAI"));
            pegawai.setPassword(rs.getString("PASSWORD_PEGAWAI"));
            pegawai.setNama(rs.getString("NAMA_PEGAWAI"));
            pegawai.setAlamat(rs.getString("ALAMAT_PEGAWAI"));
            pegawai.setNo_telp(rs.getInt("NO_TELP_PEGAWAI"));
            pegawai.setRole(rs.getInt("ROLE_PEGAWAI"));
            
            transaksi transaksi = new transaksi();
            transaksi.setId_transaksi(rs.getInt("ID_TRANSAKSI"));
            transaksi.setPegawai(pegawai);
            transaksi.setTanggal_transaksi(new Date(rs.getString("TANGGAL_TRANSAKSI")));
            transaksi.setHarga_total(rs.getDouble("HARGA_TOTAL"));
            
            ResultSet rsDetail = this.koneksi.GetData("SELECT * FROM DETAIL_TRANSAKSI JOIN BARANG ON DETAIL_TRANSAKSI.ID_BARANG = BARANG.ID_BARANG WHERE DETAIL_TRANSAKSI.ID_TRANSAKSI = " + rs.getString("ID_TRANSAKSI"));
            ArrayList<detail_transaksi> dp = new ArrayList<>();
            
            while(rsDetail.next()){
                
                barang barang = new barang();
                barang.setId_barang(rsDetail.getInt("ID_BARANG"));
                barang.setNama_barang(rsDetail.getString("NAMA_BARANG"));
                barang.setStok(rsDetail.getInt("STOK_BARANG"));
                barang.setHarga_satuan(rsDetail.getInt("HARGA_SATUAN"));
                
                detail_transaksi detail = new detail_transaksi();
                detail.setBarang(barang);
                detail.setJumlah(rsDetail.getInt("JUMLAH"));
                
                dp.add(detail);
            }
            transaksi.setArrDetail_Transaksi(dp);
            
            this.arrTransaksi.add(transaksi);
        }
       
        return this.arrTransaksi;
    }
    
    public void insertTransaksi(transaksi transaksi) throws SQLException{
        try {
        String dateTransaksi = new SimpleDateFormat("dd/MM/yyyy").format(transaksi.getTanggal_transaksi());
        this.koneksi.ManipulasiData("INSERT INTO TRANSAKSI VALUES(ID_TRANSAKSI.NEXTVAL, "+ transaksi.getPegawai().getId_pegawai() +",TO_DATE('"+ dateTransaksi +"','dd/MM/yyyy'), '"+ transaksi.getHarga_total().toString() +"')");
        ResultSet rsCur = this.koneksi.GetData("SELECT ID_TRANSAKSI.CURRVAL FROM DUAL");
        rsCur.next();
        int id_transaksi = rsCur.getInt("CURRVAL");
        for(detail_transaksi p : transaksi.getArrDetail_Transaksi()){
            this.koneksi.ManipulasiData("INSERT INTO DETAIL_TRANSAKSI VALUES (" + id_transaksi + ", "+ p.getBarang().getId_barang() +", "+ p.getJumlah().toString() +")");
            this.koneksi.ManipulasiData("UPDATE BARANG SET STOK_BARANG=STOK_BARANG - " + p.getJumlah().toString() +"WHERE ID_BARANG = " + p.getBarang().getId_barang());
        }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void insertBarang(barang barang) throws SQLException{
        this.koneksi.ManipulasiData("INSERT INTO BARANG VALUES(id_barang.nextval, "+ barang.getPegawai().getId_pegawai() +",'"+ barang.getNama_barang()+"','"+ barang.getHarga_satuan() +"','"+ barang.getStok() +"')");
    }
    
    public void deleteBarang(barang barang) throws SQLException{
        this.koneksi.ManipulasiData("DELETE FROM BARANG WHERE ID_BARANG = "+barang.getId_barang());
    }
    
    public void editBarang(barang barang) throws SQLException{
        this.koneksi.ManipulasiData("UPDATE BARANG SET ID_PEGAWAI = "+barang.getPegawai().getId_pegawai()+", NAMA_BARANG = '"+barang.getNama_barang()+"', HARGA_SATUAN = '"+barang.getHarga_satuan()+"', STOK_BARANG ='"+barang.getStok()+"' WHERE ID_BARANG = "+barang.getId_barang());
    }
    
    public void insertPegawai(pegawai pegawai) throws SQLException{
        this.koneksi.ManipulasiData("INSERT INTO PEGAWAI(ID_PEGAWAI, USERNAME, PASSWORD, NAMA, ALAMAT, NO_TELP, ROLE) VALUES (ID_PEGAWAI.NEXTVAL, '"+pegawai.getUsername()+"', '"+pegawai.getPassword()+"', '"+pegawai.getNama()+"', '"+pegawai.getAlamat()+"', '"+pegawai.getNo_telp()+"', "+pegawai.getRole()+")");
    }
    
    public void deletePegawai(pegawai pegawai) throws SQLException{
        this.koneksi.ManipulasiData("DELETE FROM PEGAWAI WHERE ID_PEGAWAI = "+pegawai.getId_pegawai());
    }
    
    public void editPegawai(pegawai pegawai) throws SQLException{
        this.koneksi.ManipulasiData("UPDATE PEGAWAI SET USERNAME = '"+pegawai.getUsername()+"', PASSWORD = '"+pegawai.getPassword()+"', NAMA = '"+pegawai.getNama()+"', ALAMAT = '"+pegawai.getAlamat()+"', NO_TELP = '"+pegawai.getNo_telp()+"', ROLE = '"+pegawai.getRole()+"' WHERE ID_PEGAWAI = "+pegawai.getId_pegawai());
    }

}
