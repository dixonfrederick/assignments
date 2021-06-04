package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class RingkasanMataKuliahGUI {

    public RingkasanMataKuliahGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // Judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Ringkasan Mata Kuliah");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        // Label dan ComboBox matkul
        JLabel matkul = new JLabel();
        matkul.setText("Pilih Nama Matkul");
        matkul.setHorizontalAlignment(JLabel.CENTER);
        matkul.setFont(SistemAkademikGUI.fontGeneral);
        JComboBox jcbName = new JComboBox(sortNama(daftarMataKuliah));
        // Button
        JButton jbtDetail = new JButton("Lihat");
        JButton jbtReturn = new JButton("Kembali");
        // Panel
        JPanel p = new JPanel(new GridLayout(5,1,0,17));
        JPanel pMain = new JPanel(new BorderLayout());

        // Menambahkan komponen pada panel
        p.add(titleLabel);
        p.add(matkul);
        p.add(jcbName);
        p.add(jbtDetail);
        p.add(jbtReturn);

        // Listener
        jbtDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle untuk ComboBox kosong
                if (jcbName.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(
                        null,
                        "Mohon isi seluruh Field",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String namaMatkul = (String)jcbName.getSelectedItem();
                MataKuliah matkul = getMataKuliah(namaMatkul, daftarMataKuliah);
            
                frame.remove(pMain);
                new DetailRingkasanMataKuliahGUI(frame, matkul, daftarMahasiswa, daftarMataKuliah);
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

    public String[] sortNama(ArrayList<MataKuliah> arr){
        String[] res = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++){
            res[i] = arr.get(i).getNama();
        }    
        for (int i = 0; i < arr.size(); i++){
            for (int j = i+1; j < arr.size(); j++){
                if (res[i].compareToIgnoreCase(res[j]) > 0){
                    String temp = res[i];
                    res[i] = res[j];
                    res[j] = temp;
                }
            }
        }
        return res;
    }

    private MataKuliah getMataKuliah(String nama, ArrayList<MataKuliah> daftarMataKuliah) {

        for (MataKuliah mataKuliah : daftarMataKuliah) {
            if (mataKuliah.getNama().equals(nama)){
                return mataKuliah;
            }
        }
        return null;
    }

}
