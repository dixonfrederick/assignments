package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class DetailRingkasanMataKuliahGUI {
    public DetailRingkasanMataKuliahGUI(JFrame frame, MataKuliah mataKuliah, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // Judul dan Komponen Label
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Detail Ringkasan Mata Kuliah");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitleR);

        JLabel nama = new JLabel();
        nama.setText("Nama mata kuliah: " + mataKuliah.getNama());
        nama.setHorizontalAlignment(JLabel.CENTER);
        nama.setFont(SistemAkademikGUI.fontRingkasan);
        
        JLabel kode = new JLabel();
        kode.setText("Kode: " + mataKuliah.getKode());
        kode.setHorizontalAlignment(JLabel.CENTER);
        kode.setFont(SistemAkademikGUI.fontRingkasan);

        JLabel sks = new JLabel();
        sks.setText("SKS: " + mataKuliah.getSKS());
        sks.setHorizontalAlignment(JLabel.CENTER);
        sks.setFont(SistemAkademikGUI.fontRingkasan);

        JLabel jumlahMhs = new JLabel();
        jumlahMhs.setText("Jumlah mahasiswa: " + mataKuliah.getJumlahMahasiswa());
        jumlahMhs.setHorizontalAlignment(JLabel.CENTER);
        jumlahMhs.setFont(SistemAkademikGUI.fontRingkasan);

        JLabel kapasitas = new JLabel();
        kapasitas.setText("Kapasitas: " + mataKuliah.getKapasitas());
        kapasitas.setHorizontalAlignment(JLabel.CENTER);
        kapasitas.setFont(SistemAkademikGUI.fontRingkasan);

        JLabel daftar = new JLabel();
        daftar.setText("Daftar Mahasiswa:");
        daftar.setHorizontalAlignment(JLabel.CENTER);
        daftar.setFont(SistemAkademikGUI.fontRingkasan);
        // Button
        JButton jbtReturn = new JButton("Selesai");
        color(jbtReturn);
        // Panel
        int ukuranGrid = mataKuliah.getJumlahMahasiswa() + 9;
        JPanel p = new JPanel(new GridLayout(ukuranGrid,1,0,12));
        JPanel pMain = new JPanel(new BorderLayout());

        // Menambahkan komponen pada panel
        p.add(titleLabel);
        p.add(nama);
        p.add(kode);
        p.add(sks);
        p.add(jumlahMhs);
        p.add(kapasitas);
        p.add(daftar);
        daftarAmbil(mataKuliah, p);
        p.add(jbtReturn);

        jbtReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setSize(500,500);
                frame.setLocationRelativeTo(null);
                frame.remove(pMain);
                new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
                frame.revalidate();
            }
        }
        );
        frame.setSize(500,750);
        frame.setLocationRelativeTo(null);
        // Border
        pMain.add(new JLabel("      "), BorderLayout.NORTH);
        pMain.add(new JLabel("              "), BorderLayout.WEST);
        pMain.add(new JLabel("              "), BorderLayout.EAST);
        pMain.add(new JLabel("      "), BorderLayout.SOUTH);
        pMain.add(p, BorderLayout.CENTER);
         
        frame.add(pMain);
    }
    // Method untuk mencetak daftar Mahasiswa yang mengambil mataKuliah
    public void daftarAmbil(MataKuliah mataKuliah, JPanel p){
        Mahasiswa[] arr = mataKuliah.getDaftarMahasiswa();

        if (mataKuliah.getJumlahMahasiswa() == 0){
            JLabel ambil = new JLabel();
            ambil.setHorizontalAlignment(JLabel.CENTER);
            ambil.setFont(new Font("Century Gothic", Font.BOLD, 12));
            ambil.setText("Belum ada mahasiswa yang mengambil mata kuliah ini.");
            p.add(ambil);
            return;
        }
        for (int i = 0; i < mataKuliah.getJumlahMahasiswa(); i++){
            JLabel ambil = new JLabel();
            ambil.setHorizontalAlignment(JLabel.CENTER);
            ambil.setFont(new Font("Century Gothic", Font.BOLD, 12));
            ambil.setText((i+1) + ". " + arr[i].getNama());
            p.add(ambil);
        }
    }
    // Method set color
    public void color(JButton b){
        b.setBackground(Color.BLUE);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
    }
}
