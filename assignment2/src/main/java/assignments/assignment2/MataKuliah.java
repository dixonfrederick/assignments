package assignments.assignment2;

public class MataKuliah {
    private String kode;
    private String nama;
    private int sks;
    private int kapasitas;
    private Mahasiswa[] daftarMahasiswa = new Mahasiswa[100];
    // Counter mahasiswa
    private int totalMahasiswa = 0;

    // Constructor
    public MataKuliah(String kode, String nama, int sks, int kapasitas){
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
        this.kapasitas = kapasitas;
    }

    // Getter
    public String getKode(){
        return this.kode;
    }
    public String getNama(){
        return this.nama;
    }
    public int getSks(){
        return this.sks;
    }
    public int getKapasitas(){
        return this.kapasitas;
    }

    public Mahasiswa[] getDaftarMahasiswa(){
        return this.daftarMahasiswa;
    }
    public int getJumlahMahasiswa(){
        return this.totalMahasiswa;
    }

    // Setter
    public void setKode(String kode){
        this.kode = kode;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public void setSks(int sks){
        this.sks = sks;
    }
    public void setKapasitas(int kapasitas){
        this.kapasitas = kapasitas;
    }

    // Method untuk cek kapasitas matkul
    public String cekKapasitas(){
        if (totalMahasiswa == this.kapasitas){
            return "full";
        }
        else{
            return "ok";
        }
    }

    // Method untuk addMahasiswa
    public void addMahasiswa(Mahasiswa mahasiswa) {
        for (int i = 0; i < totalMahasiswa; i++){
            if (this.daftarMahasiswa[i].getNama() == (mahasiswa.getNama())){
                return;
            }
        }
        if (cekKapasitas().equals("ok")){
            this.daftarMahasiswa[totalMahasiswa] = mahasiswa;
            totalMahasiswa++;
        }
    }

    // Method untuk dropMahasiswa
    public void dropMahasiswa(Mahasiswa mahasiswa) {
        for (int i = 0; i < totalMahasiswa; i++){
            if (this.daftarMahasiswa[i].getNama().equals(mahasiswa.getNama())){
                for (int j = i; j < totalMahasiswa - 1; j++){
                    this.daftarMahasiswa[j] = this.daftarMahasiswa[j + 1];
                }
                totalMahasiswa--;
            }
        }
    }

    public String toString() {
        return this.nama;
    }

}
