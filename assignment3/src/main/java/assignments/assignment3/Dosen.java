package assignments.assignment3;

class Dosen extends ElemenFasilkom {

    private MataKuliah mataKuliah;
    // Constructor
    public Dosen(String nama) {
        super();
        setNama(nama);
        setTipe("Dosen");
    }

    // Getter
    public MataKuliah getMataKuliah(){
        return mataKuliah;
    }

    // Method untuk mengajar mataKuliah
    // Hanya akan dijalankan jika dosen belum mengajar dan mataKuliah belum memiliki dosen
    public void mengajarMataKuliah(MataKuliah mataKuliah) {
        if (this.mataKuliah != null){
            System.out.println("[DITOLAK] " + getNama() + " sudah mengajar mata kuliah " + this.mataKuliah.getNama());
        }
        else if (mataKuliah.getDosen() != null){
            System.out.println("[DITOLAK] " + mataKuliah.getNama() + " sudah memiliki dosen pengajar");
        }
        else{
            this.mataKuliah = mataKuliah;
            mataKuliah.addDosen(new Dosen(getNama()));
            System.out.println(getNama() + " mengajar mata kuliah " + mataKuliah.getNama());
        }
    }

    // Method untuk berhenti mengajar
    // Hanya akan dijalankan jika dosen sudah mengajar mataKuliah
    public void dropMataKuliah() {
        if (this.mataKuliah == null){
            System.out.println("[DITOLAK] " + getNama() + " sedang tidak mengajar mata kuliah apapun");
        }
        else{
            System.out.println(getNama() + " berhenti mengajar " + this.mataKuliah.getNama());
            mataKuliah.dropDosen();
            this.mataKuliah = null;
        }
    }

    @Override
    public void membeliMakanan(ElemenFasilkom pembeli, ElemenFasilkom penjual, String namaMakanan) {
    }
}