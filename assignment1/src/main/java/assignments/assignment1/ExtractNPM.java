package assignments.assignment1;

import java.util.Scanner;

public class ExtractNPM {
    public static long getTahunMasuk(long npm){ // Method untuk mendapatkan tahun masuk
        String tahunMasukString = String.valueOf(npm).substring(0,2); // angka ke 1 - 2
        long tahunMasuk = java.lang.Long.parseLong(tahunMasukString);
        return tahunMasuk + 2000; // Kode tahun masuk ditambah 2000 untuk mendapatkan tahun sebenarnya
    }

    public static long getKode(long npm){ // Method untuk mendapatkan kode jurusan
        String kodeString = String.valueOf(npm).substring(2,4); // angka ke 3 - 4
        long kodeJurusan = java.lang.Long.parseLong(kodeString);
        return kodeJurusan;
    }

    public static String getJurusan(long npm){ // Method untuk mendapatkan jurusan berdasarkan kodenya
        if (getKode(npm) == 1){
            return "Ilmu Komputer";
        }
        else if (getKode(npm) == 2){
            return "Sistem Informasi";
        }
        else if (getKode(npm) == 3){
            return "Teknologi Informasi";
        }
        else if (getKode(npm) == 11){
            return "Teknik Telekomunikasi";
        }
        else if (getKode(npm) == 12){
            return "Teknik Elektro";
        }
        else{
            return "invalid"; // Jika kode tidak terdapat pada daftar, NPM tersebut invalid
        }
    }

    public static long getTahun(long npm){ // Method untuk mendapatkan tahun kelahiran
        String tahunString = String.valueOf(npm).substring(8,12); // angka ke 9 - 12
        long tahun = java.lang.Long.parseLong(tahunString);
        return tahun;
    }

    public static String getTanggalLahir(long npm){ // Method untuk mendapatkan tanggal lahir
        String tanggalLahir = String.valueOf(npm).substring(4,6) + "-" + String.valueOf(npm).substring(6,8) 
                                + "-" + String.valueOf(getTahun(npm)); 
                                // Tanggal pada angka ke 5 - 6, bulan angka ke 7 - 8, tahun angka ke 9 - 12
        return tanggalLahir;
    }

    public static long adder(long npm){ // Method untuk penjumlahan pada method validasi NPM
        long total = 0;
        for (int i = 0; i < String.valueOf(npm).length(); i++){
            total += java.lang.Long.parseLong(String.valueOf(npm).substring(i,i + 1)); 
        } // Menjumlahkan semua angka dalam satu String
        return total;
    }

    public static boolean validate(long npm) { // Method validasi
        int npmLength = String.valueOf(npm).length();
        /* 
        /Jika panjang NPM bukan 14 / Tahun masuk sebelum 2010 
        / Umur mahasiswa di bawah 15 tahun / Jurusan tidak valid
        / NPM tersebut tidak valid
        */
        if (npmLength != 14 || getTahunMasuk(npm) < 2010 || getTahunMasuk(npm) - getTahun(npm) < 15 
            || getJurusan(npm).equals("invalid")){
            return false;
        }
        long res = 0;
        for (int i = 0, j = npmLength - 2; i < npmLength - 8 || j > 6; i++, j--){
            long start = java.lang.Long.parseLong(String.valueOf(npm).substring(i,i + 1));
            long end = java.lang.Long.parseLong(String.valueOf(npm).substring(j,j + 1));
            res += start*end; // hasil penjumlahan angka pada indeks 0 - 12 dengan pola (i+1, j-1)
        }
        res += java.lang.Long.parseLong(String.valueOf(npm).substring(6,7)); // ditambah angka ke-7
        while (res >= 10){
            res = adder(res);
        } // Jika hasilnya masih lebih dari 10, lakukan implementasi method adder
        if (res == java.lang.Long.parseLong(String.valueOf(npm).substring(13))){
            return true; 
        } // Jika hasilnya sama dengan angka terakhir, NPM tersebut valid
        else{
            return false;
        }
    }

    public static String extract(long npm) { // Method ekstraksi
        if (validate(npm) == true){
            return "Tahun masuk: " + getTahunMasuk(npm) + "\n" 
                    + "Jurusan: " + getJurusan(npm) + "\n"
                    + "Tanggal Lahir: " + getTanggalLahir(npm);
        } // Jika NPM valid, program akan mencetak tahun masuk, jurusan, dan tanggal lahir
        else{
            return "NPM tidak valid!";
        }
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        boolean exitFlag = false;
        while (!exitFlag) {
            long npm = input.nextLong();
            if (npm < 0) {
                exitFlag = true; // Program akan keluar (exit) bila inputnya -1
                break;
            }
            System.out.println();
            System.out.println(extract(npm));
            System.out.println();
        }
        input.close();
    }
} 