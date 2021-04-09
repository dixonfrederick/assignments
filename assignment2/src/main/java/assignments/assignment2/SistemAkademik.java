package assignments.assignment2;

import java.util.Scanner;

public class SistemAkademik {
    private static final int ADD_MATKUL = 1;
    private static final int DROP_MATKUL = 2;
    private static final int RINGKASAN_MAHASISWA = 3;
    private static final int RINGKASAN_MATAKULIAH = 4;
    private static final int KELUAR = 5;
    private static Mahasiswa[] daftarMahasiswa = new Mahasiswa[100];
    private static MataKuliah[] daftarMataKuliah = new MataKuliah[100];

    private int totalMahasiswa = 0;
    private int totalMatkul = 0;
    
    private Scanner input = new Scanner(System.in);

    private Mahasiswa getMahasiswa(long npm) {
        for (int i = 0; i <= totalMahasiswa; i++){
            if (daftarMahasiswa[i].getNpm() == npm){
                return daftarMahasiswa[i];
            }
        }
        return null;
    }

    private MataKuliah getMataKuliah(String namaMataKuliah) {
        for (int i = 0; i < totalMatkul; i++){
            if (daftarMataKuliah[i].getNama().equals(namaMataKuliah)){
                return daftarMataKuliah[i];
            }
        }
        return null;
    }

    private void addMatkul(){
        System.out.println("\n--------------------------ADD MATKUL--------------------------\n");

        System.out.print("Masukkan NPM Mahasiswa yang akan melakukan ADD MATKUL : ");
        long npm = Long.parseLong(input.nextLine());

        System.out.print("Banyaknya Matkul yang Ditambah: ");
        int banyakMatkul = Integer.parseInt(input.nextLine());
        System.out.println("Masukkan nama matkul yang ditambah");
        for(int i=0; i<banyakMatkul; i++){
            System.out.print("Nama matakuliah " + i+1 + " : ");
            String namaMataKuliah = input.nextLine();

            // Method untuk addMatkul diimplementasikan pada Class Mahasiswa dan MataKuliah
            getMahasiswa(npm).addMatkul(getMataKuliah(namaMataKuliah));
            getMataKuliah(namaMataKuliah).addMahasiswa(getMahasiswa(npm));
        }
        System.out.println("\nSilakan cek rekap untuk melihat hasil pengecekan IRS.\n");
    }

    private void dropMatkul(){
        System.out.println("\n--------------------------DROP MATKUL--------------------------\n");

        System.out.print("Masukkan NPM Mahasiswa yang akan melakukan DROP MATKUL : ");
        long npm = Long.parseLong(input.nextLine());

        // Jika mahasiswa belum mengambil mata kuliah apapun
        if (getMahasiswa(npm).getTotalMatkul() == 0){
            System.out.println("[DITOLAK] Belum ada mata kuliah yang diambil.");
            return;
        }

        System.out.print("Banyaknya Matkul yang Di-drop: ");
        int banyakMatkul = Integer.parseInt(input.nextLine());
        System.out.println("Masukkan nama matkul yang di-drop:");
        for(int i=0; i<banyakMatkul; i++){
            System.out.print("Nama matakuliah " + i+1 + " : ");
            String namaMataKuliah = input.nextLine();

            // Method untuk dropMatkul diimplementasikan pada Class Mahasiswa dan MataKuliah
            getMahasiswa(npm).dropMatkul(getMataKuliah(namaMataKuliah));
            getMataKuliah(namaMataKuliah).dropMahasiswa(getMahasiswa(npm));
        }
        System.out.println("\nSilakan cek rekap untuk melihat hasil pengecekan IRS.\n");
    }

    private void ringkasanMahasiswa(){
        System.out.print("Masukkan npm mahasiswa yang akan ditunjukkan ringkasannya : ");
        long npm = Long.parseLong(input.nextLine());

        System.out.println("\n--------------------------RINGKASAN--------------------------\n");
        System.out.println("Nama: " + getMahasiswa(npm).getNama());
        System.out.println("NPM: " + npm);
        System.out.println("Jurusan: " + getMahasiswa(npm).getNamaJurusan());
        System.out.println("Daftar Mata Kuliah: ");

        // Mencetak daftar mata kuliah yang diambil
        if (getMahasiswa(npm).getTotalMatkul() == 0){
            System.out.println("Belum ada mata kuliah yang diambil");
        }
        else{
            for (int i = 0; i < getMahasiswa(npm).getTotalMatkul(); i++){
                System.out.println(i+1 + ". " + getMahasiswa(npm).getDaftarMatkul()[i]);
            } 
        }

        System.out.println("Total SKS: " + getMahasiswa(npm).getSks());
        
        System.out.println("Hasil Pengecekan IRS:");

        // Implementasi method Cek IRS mahasiswa
        getMahasiswa(npm).cekIRS();
        if (getMahasiswa(npm).getTotalMasalah() == 0){
            System.out.println("IRS tidak bermasalah");
        }
        else{
            for (int i = 0; i < getMahasiswa(npm).getTotalMasalah(); i++){
                System.out.println(i+1 + ". " + getMahasiswa(npm).getMasalah()[i]);
            } 
        }
    }

    private void ringkasanMataKuliah(){
        System.out.print("Masukkan nama mata kuliah yang akan ditunjukkan ringkasannya : ");
        String namaMataKuliah = input.nextLine();
        
        System.out.println("\n--------------------------RINGKASAN--------------------------\n");
        System.out.println("Nama mata kuliah: " + getMataKuliah(namaMataKuliah).getNama());
        System.out.println("Kode: " + getMataKuliah(namaMataKuliah).getKode());
        System.out.println("SKS: " + getMataKuliah(namaMataKuliah).getSks());
        System.out.println("Jumlah mahasiswa: " + getMataKuliah(namaMataKuliah).getJumlahMahasiswa());
        System.out.println("Kapasitas: " + getMataKuliah(namaMataKuliah).getKapasitas());
        System.out.println("Daftar mahasiswa yang mengambil mata kuliah ini: ");

        // Mencetak ringkasan mata kuliah
        if (getMataKuliah(namaMataKuliah).getJumlahMahasiswa() == 0){
            System.out.println("Belum ada mahasiswa yang mengambil mata kuliah ini.");
        }
        else{
            for (int i = 0; i < getMataKuliah(namaMataKuliah).getJumlahMahasiswa(); i++){
                System.out.println(i+1 + ". " + getMataKuliah(namaMataKuliah).getDaftarMahasiswa()[i]);
            } 
        }
    } 

    private void daftarMenu(){
        int pilihan = 0;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----------------------------MENU------------------------------\n");
            System.out.println("Silakan pilih menu:");
            System.out.println("1. Add Matkul");
            System.out.println("2. Drop Matkul");
            System.out.println("3. Ringkasan Mahasiswa");
            System.out.println("4. Ringkasan Mata Kuliah");
            System.out.println("5. Keluar");
            System.out.print("\nPilih: ");
            try {
                pilihan = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
            System.out.println();
            if (pilihan == ADD_MATKUL) {
                addMatkul();
            } else if (pilihan == DROP_MATKUL) {
                dropMatkul();
            } else if (pilihan == RINGKASAN_MAHASISWA) {
                ringkasanMahasiswa();
            } else if (pilihan == RINGKASAN_MATAKULIAH) {
                ringkasanMataKuliah();
            } else if (pilihan == KELUAR) {
                System.out.println("Sampai jumpa!");
                exit = true;
            }
        }

    }


    private void run() {
        System.out.println("====================== Sistem Akademik =======================\n");
        System.out.println("Selamat datang di Sistem Akademik Fasilkom!");
        
        System.out.print("Banyaknya Matkul di Fasilkom: ");
        int banyakMatkul = Integer.parseInt(input.nextLine());
        System.out.println("Masukkan matkul yang ditambah");
        System.out.println("format: [Kode Matkul] [Nama Matkul] [SKS] [Kapasitas]");

        for(int i=0; i<banyakMatkul; i++){
            String[] dataMatkul = input.nextLine().split(" ", 4);
            int sks = Integer.parseInt(dataMatkul[2]);
            int kapasitas = Integer.parseInt(dataMatkul[3]);

            // Menambahkan instance MataKuliah baru ke dalam daftarMataKuliah
            daftarMataKuliah[totalMatkul] = new MataKuliah(dataMatkul[0], dataMatkul[1], sks, kapasitas);
            totalMatkul++;
        }

        System.out.print("Banyaknya Mahasiswa di Fasilkom: ");
        int banyakMahasiswa = Integer.parseInt(input.nextLine());
        System.out.println("Masukkan data mahasiswa");
        System.out.println("format: [Nama] [NPM]");

        for(int i=0; i<banyakMahasiswa; i++){
            String[] dataMahasiswa = input.nextLine().split(" ", 2);
            long npm = Long.parseLong(dataMahasiswa[1]);

            // Menambahkan instance Mahasiswa baru ke dalam daftarMahasiswa
            daftarMahasiswa[totalMahasiswa] = new Mahasiswa(dataMahasiswa[0], npm);
            totalMahasiswa++;
        }

        daftarMenu();
        input.close();
    }

    public static void main(String[] args) {
        SistemAkademik program = new SistemAkademik();
        program.run();
    }

}
