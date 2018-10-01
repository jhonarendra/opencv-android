package com.example.jhonarendra.myapplication;

/**
 * Created by Jhonarendra on 4/10/2018.
 */

public class Mahasiswa {
    private int id;
    private String nama , alamat, nim;

    public Mahasiswa(int id, String nama, String alamat, String nim) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.nim = nim;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return nama;
    }
    public String getDesc() {
        return alamat;
    }
    public String getHarga() {
        return nim;
    }
}
