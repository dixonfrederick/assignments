package assignments.assignment3;

class ElemenKantin extends ElemenFasilkom {

    private Makanan[] daftarMakanan = new Makanan[10];
    // Counter makanan
    private int countMakanan = 0;
    // Constructor
    public ElemenKantin(String nama) {
        super();
        setNama(nama);
        setTipe("ElemenKantin");
    }
    
    // Getter
    public Makanan[] getDaftarMakanan(){
        return daftarMakanan;
    }
    
    // Method untuk menambahkan makanan yang dijual
    public void setMakanan(String nama, long harga) {
        for (int i = 0; i < countMakanan; i++){
            if (daftarMakanan[i].getNama().equals(nama)){
                System.out.println("[DITOLAK] " + nama + " sudah pernah terdaftar");
                return;
            }
        }
        daftarMakanan[countMakanan] = new Makanan(nama,harga);
        countMakanan++;
        System.out.println(getNama() + " telah mendaftarkan makanan " + nama + " dengan harga " + harga);
    }
    
    // Override abstract method membeliMakanan
    @Override
    public void membeliMakanan(ElemenFasilkom pembeli, ElemenFasilkom penjual, String namaMakanan) {
        for (int i = 0; i < countMakanan; i++){
            if (daftarMakanan[i].getNama().equals(namaMakanan)){
                long harga = daftarMakanan[i].getHarga();
                System.out.println(pembeli.getNama() + " berhasil membeli " + namaMakanan +  " seharga " + harga);
                pembeli.addFriendship(1);
                penjual.addFriendship(1);
                return;
            }
        }
        System.out.println("[DITOLAK] " + penjual.getNama() + " tidak menjual " + namaMakanan);
    }
}