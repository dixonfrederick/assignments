package assignments.assignment2;

import java.util.Arrays;

public class Mahasiswa {
    private MataKuliah[] mataKuliah = new MataKuliah[10];
    private String[] masalahIRS = new String[20];
    private int totalSKS;
    private String nama;
    private String jurusan;
    private long npm;
    // Counter mata kuliah
    private int totalMatkul = 0;
    // Counter masalah IRS
    private int totalMasalah = 0;

    // Constructor
    public Mahasiswa(String nama, long npm){
        this.nama = nama;
        this.npm = npm;
    }

    // Getter
    public String getNama(){
        return this.nama;
    }
    public long getNpm(){
        return this.npm;
    }

    public String getNamaJurusan(){
        getJurusan(this.npm);
        if (this.jurusan.equals("IK")){
            return "Ilmu Komputer";
        }
        else if (this.jurusan.equals("SI")){
            return "Sistem Informasi";
        }
        else{
            return "";
        }
    }
    public int getSks(){
        return this.totalSKS;
    }
    public MataKuliah[] getDaftarMatkul(){
        return this.mataKuliah;
    }
    public int getTotalMatkul(){
        return this.totalMatkul;
    }
    public String[] getMasalah(){
        return this.masalahIRS;
    }
    public int getTotalMasalah(){
        return this.totalMasalah;
    }

    // Setter
    public void setNama(String nama){
        this.nama = nama;
    }
    public void setNpm(long npm){
        this.npm = npm;
    }

    // Method untuk mendapatkan jurusan berdasarkan kodenya
    public void getJurusan(long npm){ 
        String kodeString = String.valueOf(npm).substring(2,4); // angka ke 3 - 4 pada NPM
        long kodeJurusan = java.lang.Long.parseLong(kodeString);
        if (kodeJurusan == 1){
            this.jurusan = "IK";
        }
        else if (kodeJurusan == 2){
            this.jurusan = "SI";
        }
        else{
            this.jurusan = "invalid"; // Jika kode bukan 1 atau 2, NPM tersebut invalid
        }
    }
    
    // Method untuk addMatkul
    public void addMatkul(MataKuliah mataKuliah){
        for (int i = 0; i < totalMatkul; i++){ // Jika Matkul sudah diambil
            if (this.mataKuliah[i].getNama() == (mataKuliah.getNama())){
                System.out.println("[DITOLAK] " + mataKuliah.getNama() + " telah diambil sebelumnya.");
                return;
            }
        }
        if (mataKuliah.cekKapasitas().equals("full")){ // Jika Kapasitas Matkul penuh
            System.out.println("[DITOLAK] " + mataKuliah.getNama() + " telah penuh kapasitasnya.");
        }
        else if (totalMatkul > 9){ // Jika Matkul yang diambil melebihi batas
            System.out.println("[DITOLAK] Maksimal mata kuliah yang diambil hanya 10.");
        }
        else{
            this.mataKuliah[totalMatkul] = mataKuliah;
            totalMatkul++;
            totalSKS += mataKuliah.getSks();
        }
    }

    // Method untuk dropMatkul
    public void dropMatkul(MataKuliah mataKuliah){
        if (totalMatkul > 0){
            for (int i = 0; i < totalMatkul; i++){
                if (this.mataKuliah[i].getNama() == mataKuliah.getNama()){
                    for (int j = i; j < totalMatkul - 1; j++){
                        this.mataKuliah[j] = this.mataKuliah[j + 1];
                    }
                    totalMatkul--;
                    totalSKS -= mataKuliah.getSks();
                    return;
                }
            }
        }
        System.out.println("[DITOLAK] " + mataKuliah.getNama() + " belum pernah diambil.");;
    }

    // Method cek IRS
    // Method Arrays.stream... memastikan tidak ada masalah yang sama dalam array masalahIRS
    public void cekIRS(){
        if (totalSKS > 24){
            String problem1 = "SKS yang Anda ambil lebih dari 24";
            if (Arrays.stream(masalahIRS).anyMatch(problem1::equals) == false){
                masalahIRS[totalMasalah] = problem1;
                totalMasalah++;
            }    
        }
        for (int i = 0; i < totalMatkul; i++){
            if (!this.mataKuliah[i].getKode().equals(this.jurusan) && !this.mataKuliah[i].getKode().equals("CS")){
                String problem2 = "Mata Kuliah " + mataKuliah[i].getNama() + " tidak dapat diambil jurusan " + this.jurusan;
                if (Arrays.stream(masalahIRS).anyMatch(problem2::equals) == false){
                    masalahIRS[totalMasalah] = problem2;
                    totalMasalah++;
                }
            }
        }
    }

    public String toString() {
        return this.nama;
    }

}
