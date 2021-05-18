package assignments.assignment3;

import java.util.Scanner;

public class Main {

    public static ElemenFasilkom[] daftarElemenFasilkom = new ElemenFasilkom[100];

    public static MataKuliah[] daftarMataKuliah = new MataKuliah[100];

    public static int totalMataKuliah = 0;

    public static int totalElemenFasilkom = 0; 

    // Method untuk menambahkan Mahasiswa ke dalam daftarElemenFasilkom
    public static void addMahasiswa(String nama, long npm) {
        daftarElemenFasilkom[totalElemenFasilkom] = new Mahasiswa(nama,npm);
        totalElemenFasilkom++;
        System.out.println(nama + " berhasil ditambahkan");
    }
    // Method untuk menambahkan Dosen ke dalam daftarElemenFasilkom
    public static void addDosen(String nama) {
        daftarElemenFasilkom[totalElemenFasilkom] = new Dosen(nama);
        totalElemenFasilkom++;
        System.out.println(nama + " berhasil ditambahkan");
    }
    // Method untuk menambahkan ElemenKantin ke dalam daftarElemenFasilkom
    public static void addElemenKantin(String nama) {
        daftarElemenFasilkom[totalElemenFasilkom] = new ElemenKantin(nama);
        totalElemenFasilkom++;
        System.out.println(nama + " berhasil ditambahkan");
    }
    
    // Method menyapa antara objek1 dan objek2 (berlaku 2 arah)
    public static void menyapa(String objek1, String objek2) {
        ElemenFasilkom e1 = null;
        ElemenFasilkom e2 = null;
        if (objek1.equals(objek2)){
            System.out.println("[DITOLAK] Objek yang sama tidak bisa saling menyapa");
            return;
        }
        for (int i = 0; i < totalElemenFasilkom; i++){
            if (daftarElemenFasilkom[i].getNama().equals(objek1)){
                e1 = daftarElemenFasilkom[i];
            }
            else if (daftarElemenFasilkom[i].getNama().equals(objek2)){
                e2 = daftarElemenFasilkom[i];
            }
        }
        /* 
        Jika objek1 adalah Mahasiswa dan objek2 adalah Dosen yang terhubung
        dalam 1 matkul yang sama, friendship keduanya bertambah 2, dan sebaliknya
        */
        if (e1.getTipe().equals("Mahasiswa") && e2.getTipe().equals("Dosen")){
            if (((Mahasiswa) e1).tesMatkul((Dosen) e2)){
                e1.addFriendship(2);
                e2.addFriendship(2);
            }
        }
        else if (e1.getTipe().equals("Dosen") && e2.getTipe().equals("Mahasiswa")){
            if (((Mahasiswa) e2).tesMatkul((Dosen) e1)){
                e1.addFriendship(2);
                e2.addFriendship(2);
            }
        }
        for (int i = 0; i < e1.getTotalSapa(); i++){
            if (e1.getMenyapa()[i].getNama().equals(e2.getNama())){
                System.out.println("[DITOLAK] " +  objek1 + " telah menyapa " + objek2 + " hari ini");
                return;
            }
        }
        e1.menyapa(e2);
        e2.menyapa(e1);
        System.out.println(objek1 + " menyapa dengan " + objek2);
    }

    // Method menambahkan makanan dalam daftarMakanan milik objek
    public static void addMakanan(String objek, String namaMakanan, long harga) {
        for (int i = 0; i < totalElemenFasilkom; i++){
            if (daftarElemenFasilkom[i].getNama().equals(objek)){
                if (daftarElemenFasilkom[i].getTipe().equals("ElemenKantin")){
                    ((ElemenKantin) daftarElemenFasilkom[i]).setMakanan(namaMakanan,harga);
                }
                else{
                    System.out.println("[DITOLAK] " + objek + " bukan merupakan elemen kantin");
                }
            }
        }
    }

    // Method membeli makanan (objek1 membeli makanan dari objek2)
    public static void membeliMakanan(String objek1, String objek2, String namaMakanan) {
        ElemenFasilkom e1 = null;
        ElemenFasilkom e2 = null;
        if (objek1.equals(objek2)){
            System.out.println("[DITOLAK] Elemen kantin tidak bisa membeli makanan sendiri");
            return;
        }
        for (int i = 0; i < totalElemenFasilkom; i++){
            if (daftarElemenFasilkom[i].getNama().equals(objek1)){
                e1 = daftarElemenFasilkom[i];
            }
            else if (daftarElemenFasilkom[i].getNama().equals(objek2)){
                e2 = daftarElemenFasilkom[i];
            }
        }
        if (!e2.getTipe().equals("ElemenKantin")){
            System.out.println("[DITOLAK] Hanya elemen kantin yang dapat menjual makanan");
        }
        else{
            e2.membeliMakanan(e1, e2, namaMakanan);
        }
    }

    // Method untuk menambahkan Matkul ke dalam daftarMataKuliah
    public static void createMatkul(String nama, int kapasitas) {
        daftarMataKuliah[totalMataKuliah] = new MataKuliah(nama,kapasitas);
        totalMataKuliah++;
        System.out.println(nama + " berhasil ditambahkan dengan kapasitas " + kapasitas);
    }

    // Method untuk menambahkan Matkul yang akan diambil seorang mahasiswa
    public static void addMatkul(String objek, String namaMataKuliah) {
        MataKuliah m = null;
        for (int j = 0; j < totalMataKuliah; j++){
            if (daftarMataKuliah[j].getNama().equals(namaMataKuliah)){
                m = daftarMataKuliah[j];
            }
        }
        for (int i = 0; i < totalElemenFasilkom; i++){
            if (daftarElemenFasilkom[i].getNama().equals(objek)){
                if (daftarElemenFasilkom[i].getTipe().equals("Mahasiswa")){
                    ((Mahasiswa) daftarElemenFasilkom[i]).addMatkul(m);
                }
                else{
                    System.out.println("[DITOLAK] Hanya mahasiswa yang dapat menambahkan matkul");
                }
            }
        }
    }

    // Method untuk drop Matkul yang telah diambil seorang mahasiswa
    public static void dropMatkul(String objek, String namaMataKuliah) {
        MataKuliah m = null;
        for (int j = 0; j < totalMataKuliah; j++){
            if (daftarMataKuliah[j].getNama().equals(namaMataKuliah)){
                m = daftarMataKuliah[j];
            }
        }
        for (int i = 0; i < totalElemenFasilkom; i++){
            if (daftarElemenFasilkom[i].getNama().equals(objek)){
                if (daftarElemenFasilkom[i].getTipe().equals("Mahasiswa")){
                    ((Mahasiswa) daftarElemenFasilkom[i]).dropMatkul(m);
                }
                else{
                    System.out.println("[DITOLAK] Hanya mahasiswa yang dapat drop matkul");
                }
            }
        }
    }

    // Method untuk menambahkan Matkul yang akan diajar seorang dosen
    public static void mengajarMatkul(String objek, String namaMataKuliah) {
        MataKuliah m = null;
        for (int j = 0; j < totalMataKuliah; j++){
            if (daftarMataKuliah[j].getNama().equals(namaMataKuliah)){
                m = daftarMataKuliah[j];
            }
        }
        for (int i = 0; i < totalElemenFasilkom; i++){
            if (daftarElemenFasilkom[i].getNama().equals(objek)){
                if (daftarElemenFasilkom[i].getTipe().equals("Dosen")){
                    ((Dosen) daftarElemenFasilkom[i]).mengajarMataKuliah(m);
                }
                else{
                    System.out.println("[DITOLAK] Hanya dosen yang dapat mengajar matkul");
                }
            }
        }
    }

    // Method untuk dosen berhenti mengajar sebuah Matkul
    public static void berhentiMengajar(String objek) {
        for (int i = 0; i < totalElemenFasilkom; i++){
            if (daftarElemenFasilkom[i].getNama().equals(objek)){
                if (daftarElemenFasilkom[i].getTipe().equals("Dosen")){
                    ((Dosen) daftarElemenFasilkom[i]).dropMataKuliah();
                }
                else{
                    System.out.println("[DITOLAK] Hanya dosen yang dapat berhenti mengajar");
                }
            }
        }
    }

    // Method yang mencetak ringkasan umum seorang mahasiswa
    public static void ringkasanMahasiswa(String objek) {
        for (int i = 0; i < totalElemenFasilkom; i++){
            if (daftarElemenFasilkom[i].getNama().equals(objek)){
                if (daftarElemenFasilkom[i].getTipe().equals("Mahasiswa")){
                    Mahasiswa m = (Mahasiswa) daftarElemenFasilkom[i];
                    System.out.println("Nama: " + objek + "\n"
                                    + "Tanggal lahir: " + m.extractTanggalLahir(m.getNpm()) + "\n"
                                    + "Jurusan: " + m.extractJurusan(m.getNpm()) + "\n"
                                    + "Daftar Mata Kuliah:");
                    if (m.getTotalMatkul() == 0){
                        System.out.println("Belum ada mata kuliah yang diambil");
                        return;
                    }
                    for (int j = 0; j < m.getTotalMatkul(); j++){
                        System.out.println((j+1) + ". " + m.getMataKuliah()[j]);
                    }
                }
                else{
                    System.out.println("[DITOLAK] " + objek + " bukan merupakan seorang mahasiswa");
                }
            }
        }
    }

    // Method yang mencetak ringkasan umum sebuah matkul
    public static void ringkasanMataKuliah(String namaMataKuliah) {
        for (int i = 0; i < totalMataKuliah; i++){
            if (daftarMataKuliah[i].getNama().equals(namaMataKuliah)){
                MataKuliah m = daftarMataKuliah[i];
                System.out.print("Nama mata kuliah: " + namaMataKuliah + "\n"
                                + "Jumlah mahasiswa: " + m.getTotalMhs() + "\n"
                                + "Kapasitas: " + m.getKapasitas() + "\n"
                                + "Dosen pengajar: ");
                if (m.getDosen() != null){
                    System.out.println(m.getDosen());
                }
                else{
                    System.out.println("Belum ada");
                }
                System.out.println("Daftar mahasiswa yang mengambil mata kuliah ini:");
                if (m.getTotalMhs() == 0){
                    System.out.println("Belum ada mahasiswa yang mengambil mata kuliah ini");
                }
                else{
                    for (int j = 0; j < m.getTotalMhs(); j++){
                        System.out.println((j+1) + ". " + m.getMahasiswa()[j]);
                    }
                }
            }
        }
    }

    /*
    Method pergantian hari sesuai ketentuan
    Jika sebuah objek ElemenFasilkom telah menyapa lebih dari setengah total elemen yang ada,
    tidak termasuk dirinya sendiri, maka tingkat keramahannya bertambah 10
    Jika terjadi sebaliknya, maka tingkat keramahannya berkurang 5
    */
    public static void nextDay() {
        for (int i = 0; i < totalElemenFasilkom; i++){
            if (daftarElemenFasilkom[i].getTotalSapa() >= (totalElemenFasilkom-1)/2){
                daftarElemenFasilkom[i].addFriendship(10);
            }
            else{
                daftarElemenFasilkom[i].addFriendship(-5);
            }
            daftarElemenFasilkom[i].resetMenyapa();
            if (daftarElemenFasilkom[i].getFriendship() > 100){
                daftarElemenFasilkom[i].setFriendship(100);
            }
            else if (daftarElemenFasilkom[i].getFriendship() < 0){
                daftarElemenFasilkom[i].setFriendship(0);
            }
        }
        System.out.println("Hari telah berakhir dan nilai friendship telah diupdate");
        friendshipRanking();
    }

    // Method untuk mencetak ranking keramahan berdasarkan nilai dan abjad (jika nilainya sama)
    public static void friendshipRanking() {
        ElemenFasilkom[] arr = new ElemenFasilkom[totalElemenFasilkom];
        for (int u = 0; u < arr.length; u++){
            arr[u] = daftarElemenFasilkom[u];
        }
        for (int i = 0; i < arr.length; i++){
            for (int j = i+1; j < arr.length; j++){
                if (arr[i].getFriendship() < arr[j].getFriendship()){
                    ElemenFasilkom temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
                else if (arr[i].getFriendship() == arr[j].getFriendship()){
                    if (arr[i].getNama().compareToIgnoreCase(arr[j].getNama()) > 0){
                        ElemenFasilkom temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
        for (int k = 0; k < arr.length; k++){
            ElemenFasilkom e = arr[k];
            System.out.println((k+1) + ". " + e.getNama() + "(" + e.getFriendship() + ")");
        }    
    }

    // Menutup program dan mencetak ranking
    public static void programEnd() {
        System.out.println("Program telah berakhir. Berikut nilai terakhir dari friendship pada Fasilkom :");
        friendshipRanking();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        while (true) {
            String in = input.nextLine();
            if (in.split(" ")[0].equals("ADD_MAHASISWA")) {
                addMahasiswa(in.split(" ")[1], Long.parseLong(in.split(" ")[2]));
            } else if (in.split(" ")[0].equals("ADD_DOSEN")) {
                addDosen(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("ADD_ELEMEN_KANTIN")) {
                addElemenKantin(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("MENYAPA")) {
                menyapa(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("ADD_MAKANAN")) {
                addMakanan(in.split(" ")[1], in.split(" ")[2], Long.parseLong(in.split(" ")[3]));
            } else if (in.split(" ")[0].equals("MEMBELI_MAKANAN")) {
                membeliMakanan(in.split(" ")[1], in.split(" ")[2], in.split(" ")[3]);
            } else if (in.split(" ")[0].equals("CREATE_MATKUL")) {
                createMatkul(in.split(" ")[1], Integer.parseInt(in.split(" ")[2]));
            } else if (in.split(" ")[0].equals("ADD_MATKUL")) {
                addMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("DROP_MATKUL")) {
                dropMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("MENGAJAR_MATKUL")) {
                mengajarMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("BERHENTI_MENGAJAR")) {
                berhentiMengajar(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("RINGKASAN_MAHASISWA")) {
                ringkasanMahasiswa(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("RINGKASAN_MATKUL")) {
                ringkasanMataKuliah(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("NEXT_DAY")) {
                nextDay();
            } else if (in.split(" ")[0].equals("PROGRAM_END")) {
                programEnd();
                break;
            }
        }

        input.close();
    }
}