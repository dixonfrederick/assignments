package assignments.assignment3;

abstract class ElemenFasilkom {

    private String tipe;
    private String nama;
    private int friendship;
    private ElemenFasilkom[] telahMenyapa = new ElemenFasilkom[100];
    // Counter sapaan
    private int countSapa = 0;

    // Getter
    public String getTipe(){
        return this.tipe;
    }
    public String getNama(){
        return this.nama;
    }
    public int getFriendship(){
        return this.friendship;
    }
    public ElemenFasilkom[] getMenyapa(){
        return this.telahMenyapa;
    }
    public int getTotalSapa(){
        return this.countSapa;
    }
    // Setter
    public void setTipe(String tipe){
        this.tipe = tipe;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public void setFriendship(int friendship){
        this.friendship = friendship;
    }

    // Method menambahkan friendship
    public void addFriendship(int friendship){
        this.friendship += friendship;
    }

    // Method menyapa yang menambahkan objek yang disapa ke array telahMenyapa
    public void menyapa(ElemenFasilkom elemenFasilkom) {
        telahMenyapa[countSapa] = elemenFasilkom;
        countSapa++;
    }

    // Method reset array telahMenyapa
    public void resetMenyapa() {
        telahMenyapa = new ElemenFasilkom[100];
        countSapa = 0;
    }

    // Abstract method membeliMakanan
    public abstract void membeliMakanan(ElemenFasilkom pembeli, ElemenFasilkom penjual, String namaMakanan);

    public String toString() {
        return nama;
    }
}