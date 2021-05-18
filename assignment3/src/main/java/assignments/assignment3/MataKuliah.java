package assignments.assignment3;

class MataKuliah {

    private String nama;
    private int kapasitas;
    private Dosen dosen;
    private Mahasiswa[] daftarMahasiswa;
    // Counter mahasiswa
    private int countMhs = 0;
    // Constructor
    public MataKuliah(String nama, int kapasitas) {
        this.nama = nama;
        this.kapasitas = kapasitas;
        daftarMahasiswa = new Mahasiswa[kapasitas];
    }

    // Getter
    public String getNama(){
        return this.nama;
    }
    public int getKapasitas(){
        return this.kapasitas;
    }
    public Dosen getDosen(){
        return this.dosen;
    }
    public Mahasiswa[] getMahasiswa(){
        return this.daftarMahasiswa;
    }
    public int getTotalMhs(){
        return this.countMhs;
    }

    // Method untuk cek kapasitas
    public String cekKapasitas(){
        if (countMhs == this.kapasitas){
            return "full";
        }
        else{
            return "ok";
        }
    }

    // Method untuk menambahkan mahasiswa
    public void addMahasiswa(Mahasiswa mahasiswa) {
        daftarMahasiswa[countMhs] = mahasiswa;
        countMhs++;
    }

    // Method untuk drop mahasiswa
    public void dropMahasiswa(Mahasiswa mahasiswa) {
        for (int i = 0; i < countMhs; i++){
            if (daftarMahasiswa[i].getNama().equals(mahasiswa.getNama())){
                for (int j = i; j < countMhs - 1; j++){
                    daftarMahasiswa[j] = daftarMahasiswa[j + 1];
                }
                countMhs--;
            }
        }
    }

    // Method untuk menambahkan dosen
    public void addDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    // Method untuk drop dosen
    public void dropDosen() {
        this.dosen = null;
    }

    public String toString() {
        return nama;
    }
}