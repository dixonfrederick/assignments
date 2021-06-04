package assignments.assignment4.frontend;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class HomeGUI {
    
    public HomeGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Selamat datang di Sistem Akademik");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        // Button
        JButton jbtAddMahasiswa = new JButton("Tambah Mahasiswa");
        color(jbtAddMahasiswa);
        JButton jbtAddMataKuliah = new JButton("Tambah Mata Kuliah");
        color(jbtAddMataKuliah);
        JButton jbtAddIrs = new JButton("Tambah IRS");
        color(jbtAddIrs);
        JButton jbtDropIrs = new JButton("Hapus IRS");
        color(jbtDropIrs);
        JButton jbtRingkasanMahasiswa = new JButton("Lihat Ringkasan Mahasiswa");
        color(jbtRingkasanMahasiswa);
        JButton jbtRingkasanMataKuliah = new JButton("Lihat Ringkasan Mata Kuliah");
        color(jbtRingkasanMataKuliah);
        // Panel
        JPanel p = new JPanel(new GridLayout(7,1,0,17));
        JPanel pMain = new JPanel(new BorderLayout());

        // Menambahkan komponen pada panel
        p.add(titleLabel);
        p.add(jbtAddMahasiswa);
        p.add(jbtAddMataKuliah);
        p.add(jbtAddIrs);
        p.add(jbtDropIrs);
        p.add(jbtRingkasanMahasiswa);
        p.add(jbtRingkasanMataKuliah);
        
        // Listener
        jbtAddMahasiswa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(pMain);
                new TambahMahasiswaGUI(frame, daftarMahasiswa, daftarMataKuliah);
                frame.revalidate();
            }
        }
        );
        jbtAddMataKuliah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(pMain);
                new TambahMataKuliahGUI(frame, daftarMahasiswa, daftarMataKuliah);
                frame.revalidate();
            }
        }
        );
        jbtAddIrs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(pMain);
                new TambahIRSGUI(frame, daftarMahasiswa, daftarMataKuliah);
                frame.revalidate();
            }
        }
        );
        jbtDropIrs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(pMain);
                new HapusIRSGUI(frame, daftarMahasiswa, daftarMataKuliah);
                frame.revalidate();
            }
        }
        );
        jbtRingkasanMahasiswa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(pMain);
                new RingkasanMahasiswaGUI(frame, daftarMahasiswa, daftarMataKuliah);
                frame.revalidate();
            }
        }
        );
        jbtRingkasanMataKuliah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(pMain);
                new RingkasanMataKuliahGUI(frame, daftarMahasiswa, daftarMataKuliah);
                frame.revalidate();
            }
        }
        );
        
        // Border
        pMain.add(new JLabel("      "), BorderLayout.NORTH);
        pMain.add(new JLabel("              "), BorderLayout.WEST);
        pMain.add(new JLabel("              "), BorderLayout.EAST);
        pMain.add(new JLabel("      "), BorderLayout.SOUTH);
        pMain.add(p, BorderLayout.CENTER);
        
        frame.add(pMain);
    }
    // Method set color
    public void color(JButton b){
        b.setBackground(Color.BLUE);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
    }
}
