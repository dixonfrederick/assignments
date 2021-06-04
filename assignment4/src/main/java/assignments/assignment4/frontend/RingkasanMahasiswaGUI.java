package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class RingkasanMahasiswaGUI {

    public RingkasanMahasiswaGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // Judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Ringkasan Mahasiswa");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        // Label dan ComboBox npm
        JLabel npm = new JLabel();
        npm.setText("Pilih NPM");
        npm.setHorizontalAlignment(JLabel.CENTER);
        npm.setFont(SistemAkademikGUI.fontGeneral);
        JComboBox jcbNpm = new JComboBox(sortNPM(daftarMahasiswa));
        // Button
        JButton jbtDetail = new JButton("Lihat");
        JButton jbtReturn = new JButton("Kembali");
        // Panel
        JPanel p = new JPanel(new GridLayout(5,1,0,17));
        JPanel pMain = new JPanel(new BorderLayout());

        // Menambahkan komponen pada panel
        p.add(titleLabel);
        p.add(npm);
        p.add(jcbNpm);
        p.add(jbtDetail);
        p.add(jbtReturn);
        
        // Listener
        jbtDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle untuk ComboBox kosong
                if (jcbNpm.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(
                        null,
                        "Mohon isi seluruh Field",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                long npmMhs = (long)jcbNpm.getSelectedItem();
                Mahasiswa mhs = getMahasiswa(npmMhs, daftarMahasiswa);
            
                frame.remove(pMain);
                new DetailRingkasanMahasiswaGUI(frame, mhs, daftarMahasiswa, daftarMataKuliah);
                frame.revalidate();
            }
        }
        );

        jbtReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(pMain);
                new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
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

    public Long[] sortNPM(ArrayList<Mahasiswa> arr){
        Long[] res = new Long[arr.size()];
        for (int i = 0; i < arr.size(); i++){
            res[i] = arr.get(i).getNpm();
        }    
        for (int i = 0; i < arr.size(); i++){
            for (int j = i+1; j < arr.size(); j++){
                if (res[i] > res[j]){
                    Long temp = res[i];
                    res[i] = res[j];
                    res[j] = temp;
                }
            }
        }
        return res;
    }

    private Mahasiswa getMahasiswa(long npm, ArrayList<Mahasiswa> daftarMahasiswa) {

        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            if (mahasiswa.getNpm() == npm){
                return mahasiswa;
            }
        }
        return null;
    }
    
}
