CREATE TABLESPACE cendy_06960
datafile 'F:\KULIAH\Praktikum\cendy_06960.dbf'
size 30M;

CREATE USER cendy_06960
IDENTIFIED BY cendy
DEFAULT TABLESPACE cendy_06960
QUOTA 30M ON cendy_06960;

GRANT ALL PRIVILEGES TO cendy_06960;

conn cendy_06960/cendy

create table PEGAWAI
(
   ID_PEGAWAI           INTEGER              not null,
   USERNAME             VARCHAR2(20),
   PASSWORD             VARCHAR2(20),
   NAMA                 VARCHAR2(30),
   ALAMAT               VARCHAR2(50),
   NO_TELP              NUMBER(12),
   ROLE                 INTEGER,
   constraint PK_PEGAWAI primary key (ID_PEGAWAI)
);


create table BARANG
(
   ID_BARANG            INTEGER              not null,
   ID_PEGAWAI           INTEGER,
   NAMA_BARANG          VARCHAR2(20),
   HARGA_SATUAN         NUMBER(5),
   STOK_BARANG          NUMBER(3),
   constraint PK_BARANG primary key (ID_BARANG)
);

create table TRANSAKSI
(
   ID_TRANSAKSI         INTEGER              not null,
   ID_PEGAWAI           INTEGER,
   TANGGAL_TRANSAKSI    DATE,
   HARGA_TOTAL          NUMBER(5),
   constraint PK_TRANSAKSI primary key (ID_TRANSAKSI)
);

create table DETAIL_TRANSAKSI
(
   ID_TRANSAKSI      INTEGER,
   ID_BARANG         INTEGER,
   JUMLAH            NUMBER(5)
);

alter table BARANG
add constraint FK_BARANG_DIINPUT_PEGAWAI foreign key (ID_PEGAWAI)
references PEGAWAI (ID_PEGAWAI);

alter table TRANSAKSI
add constraint FK_TRANSAKS_DIDATA_PEGAWAI foreign key (ID_PEGAWAI)
references PEGAWAI (ID_PEGAWAI);

alter table DETAIL_TRANSAKSI
add constraint FK_DETAIL_T_DETAIL_TR_BARANG foreign key (ID_BARANG)
references BARANG (ID_BARANG);

alter table DETAIL_TRANSAKSI
add constraint FK_DETAIL_T_DETAIL_TR_TRANSAKS foreign key (ID_TRANSAKSI)
references TRANSAKSI (ID_TRANSAKSI);

create sequence id_pegawai
  minvalue 1
  maxvalue 9999
  start with 1
  increment by 1
  cache 20;

create sequence id_barang
  minvalue 1
  maxvalue 9999
  start with 1
  increment by 1
  cache 20;

create sequence id_transaksi
  minvalue 1
  maxvalue 9999
  start with 1
  increment by 1
  cache 20;

create view data_barang
as SELECT PEGAWAI.USERNAME AS USERNAME_PEGAWAI,
PEGAWAI.PASSWORD AS PASSWORD_PEGAWAI,
PEGAWAI.NAMA AS NAMA_PEGAWAI,
PEGAWAI.ALAMAT AS ALAMAT_PEGAWAI,
PEGAWAI.NO_TELP AS NO_TELP_PEGAWAI,
PEGAWAI.ROLE AS ROLE_PEGAWAI,
BARANG.* FROM PEGAWAI
JOIN BARANG ON
PEGAWAI.ID_PEGAWAI = BARANG.ID_PEGAWAI
ORDER BY BARANG.ID_BARANG ASC;

create view data_transaksi
as SELECT PEGAWAI.USERNAME AS USERNAME_PEGAWAI,
PEGAWAI.PASSWORD AS PASSWORD_PEGAWAI,
PEGAWAI.NAMA AS NAMA_PEGAWAI,
PEGAWAI.ALAMAT AS ALAMAT_PEGAWAI,
PEGAWAI.NO_TELP AS NO_TELP_PEGAWAI,
PEGAWAI.ROLE AS ROLE_PEGAWAI,
TRANSAKSI.* FROM TRANSAKSI
JOIN PEGAWAI
ON TRANSAKSI.ID_PEGAWAI = PEGAWAI.ID_PEGAWAI
ORDER BY ID_TRANSAKSI DESC;

insert into PEGAWAI(ID_PEGAWAI, USERNAME, PASSWORD, NAMA, ALAMAT, NO_TELP, ROLE)
values (id_pegawai.nextval, 'Rudi', 'rudi123', 'Rudi Jayadi', 'Rungkut', '083883878', 1);

insert into PEGAWAI(ID_PEGAWAI, USERNAME, PASSWORD, NAMA, ALAMAT, NO_TELP, ROLE)
values (id_pegawai.nextval, 'Budi', 'budi123', 'Budi Subakti', 'Semolo', '083821238', 1);

insert into PEGAWAI(ID_PEGAWAI, USERNAME, PASSWORD, NAMA, ALAMAT, NO_TELP, ROLE)
values (id_pegawai.nextval, 'Adi', 'adi123', 'Adi Rochman', 'Semampir', '081982367', 1);

insert into PEGAWAI(ID_PEGAWAI, USERNAME, PASSWORD, NAMA, ALAMAT, NO_TELP, ROLE)
values (id_pegawai.nextval, 'Ali', 'ali123', 'Ali Rahmat', 'Gubeng', '082873761', 1);

insert into PEGAWAI(ID_PEGAWAI, USERNAME, PASSWORD, NAMA, ALAMAT, NO_TELP, ROLE)
values (id_pegawai.nextval, 'Gofur', 'gofur123', 'Gofur Jaya', 'Keputih', '085283711', 1);

insert into PEGAWAI(ID_PEGAWAI, USERNAME, PASSWORD, NAMA, ALAMAT, NO_TELP, ROLE)
values (id_pegawai.nextval, 'Beni', 'beni123', 'Beni Pratama', 'Wonokromr', '0812345664', 1);
