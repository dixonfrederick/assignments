package assignments.assignment3;

class Mahasiswa extends ElemenFasilkom {

    private MataKuliah[] daftarMataKuliah = new MataKuliah[10];
    private long npm;
    private String tanggalLahir;
    private String jurusan;
    // Counter mata kuliah
    private int countMatkul = 0;
    // Constructor
    public Mahasiswa(String nama, long npm) {
        super();
        setNama(nama);
        setTipe("Mahasiswa");
        this.npm = npm;
    }

    // Getter
    public MataKuliah[] getMataKuliah(){
        return this.daftarMataKuliah;
    }
    public long getNpm(){
        return this.npm;
    }
    public int getTotalMatkul(){
        return this.countMatkul;
    }

    // Method untuk menambahkan mataKuliah yang diambil
    public void addMatkul(MataKuliah mataKuliah) {
        for (int i = 0; i < countMatkul; i++){ // Jika Matkul sudah diambil
            if (daftarMataKuliah[i].getNama().equals(mataKuliah.getNama())){
                System.out.println("[DITOLAK] " + mataKuliah.getNama() + " telah diambil sebelumnya");
                return;
            }
        }
        if (mataKuliah.cekKapasitas().equals("full")){ // Jika Kapasitas Matkul penuh
            System.out.println("[DITOLAK] " + mataKuliah.getNama() + " telah penuh kapasitasnya");
        }
        else{
            mataKuliah.addMahasiswa(new Mahasiswa(getNama(),npm));
            daftarMataKuliah[countMatkul] = mataKuliah;
            countMatkul++;
            System.out.println(getNama() + " berhasil menambahkan mata kuliah " + mataKuliah.getNama());
        }
    }

    // Method untuk drop mataKuliah yang sudah diambil
    public void dropMatkul(MataKuliah mataKuliah) {
        if (countMatkul > 0){
            for (int i = 0; i < countMatkul; i++){
                if (daftarMataKuliah[i].getNama().equals(mataKuliah.getNama())){
                    for (int j = i; j < countMatkul - 1; j++){
                        daftarMataKuliah[j] = daftarMataKuliah[j + 1];
                    }
                    mataKuliah.dropMahasiswa(new Mahasiswa(getNama(),npm));
                    countMatkul--;
                    System.out.println(getNama() + " berhasil drop mata kuliah " + mataKuliah.getNama());
                    return;
                }
            }
        }
        System.out.println("[DITOLAK] " + mataKuliah.getNama() + " belum pernah diambil");
    }

    // Method untuk mencetak tanggalLahir sesuai NPM
    public String extractTanggalLahir(long npm) {
        String tanggal;
        String bulan;
        if (String.valueOf(npm).substring(4,5).equals("0")){
            tanggal = String.valueOf(npm).substring(5,6);
        }
        else{
            tanggal = String.valueOf(npm).substring(4,6);
        }
        if (String.valueOf(npm).substring(6,7).equals("0")){
            bulan = String.valueOf(npm).substring(7,8);
        }
        else{
            bulan = String.valueOf(npm).substring(6,8);
        }
        String tahun = String.valueOf(npm).substring(8,12);
        tanggalLahir = tanggal + "-" + bulan + "-" + tahun;
        return tanggalLahir;
    }

    // Method untuk mencetak jurusan sesuai NPM
    public String extractJurusan(long npm) {
        if (String.valueOf(npm).substring(2,4).equals("01")){
            jurusan = "Ilmu Komputer";
            return jurusan;
        }
        else if (String.valueOf(npm).substring(2,4).equals("02")){
            jurusan = "Sistem Informasi";
            return jurusan;
        }
        else{
            return "";
        }
    }

    // Method untuk mengecek apakah mataKuliah yang diambil == mataKuliah yang diajar dosen tertentu
    public boolean tesMatkul(Dosen dosen){
        if (daftarMataKuliah[0] == null || dosen.getMataKuliah() == null){
            return false;
        }
        for (int i = 0; i < countMatkul; i++){
            if (daftarMataKuliah[i].getNama().equals(dosen.getMataKuliah().getNama())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void membeliMakanan(ElemenFasilkom pembeli, ElemenFasilkom penjual, String namaMakanan) {
    }
}