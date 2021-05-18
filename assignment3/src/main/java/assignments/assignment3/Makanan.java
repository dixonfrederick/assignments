package assignments.assignment3;

class Makanan {

    private String nama;
    private long harga;
    // Contructor
    public Makanan(String nama, long harga) {
        this.nama = nama;
        this.harga = harga;
    }
    // Getter
    public String getNama(){
        return this.nama;
    }
    public long getHarga(){
        return this.harga;
    }

    public String toString() {
        return nama;
    }
}