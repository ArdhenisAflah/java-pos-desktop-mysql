package penjualans;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Modern Classic User Management Window for POS Desktop Application.
 * Author: Ardhenis Muhammad Aflah
 */
public class user extends javax.swing.JFrame {

    DefaultTableModel model;
    private JLabel userCountBadge;

    public user() {
        initComponents();
        setupModernLayout();

        String[] headers = {"ID Pengguna", "Nama Petugas", "Username Akun", "Kata Sandi"};
        model = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabel.setModel(model);
        UITheme.styleTable(tabel, jScrollPane2);
        int[] colWidths = {100, 180, 140, 120};
        for (int i = 0; i < colWidths.length && i < tabel.getColumnCount(); i++) {
            tabel.getColumnModel().getColumn(i).setPreferredWidth(colWidths[i]);
        }
        tampilkan();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        iduser = new javax.swing.JTextField();
        namauser = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        ubah = new javax.swing.JButton();
        hapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Kelola Pengguna & Kasir - Sistem Kasir");

        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel);

        jButton1.setText("Tambah User");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ubah.setText("Perbarui");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void setupModernLayout() {
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(UITheme.BG_CANVAS);

        // Header
        JPanel header = UITheme.createTopHeader(
                "Manajemen Akun Pengguna",
                "Kelola akun administrator dan operator kasir yang berwenang di sistem",
                this,
                true
        );
        add(header, BorderLayout.NORTH);

        // Content Area: Left Form Card + Right Table Card
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(UITheme.BG_CANVAS);
        content.setBorder(new EmptyBorder(20, 24, 20, 24));
        GridBagConstraints gbc = new GridBagConstraints();

        // --- LEFT CARD: Input Form ---
        JPanel formCard = UITheme.createCard();
        formCard.setLayout(new GridBagLayout());
        GridBagConstraints fcGbc = new GridBagConstraints();
        fcGbc.fill = GridBagConstraints.HORIZONTAL;
        fcGbc.insets = new Insets(0, 0, 10, 0);
        fcGbc.gridx = 0;
        int row = 0;

        JLabel formTitle = new JLabel("Form Data Pengguna");
        formTitle.setFont(UITheme.FONT_SECTION);
        formTitle.setForeground(UITheme.PRIMARY);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 16, 0);
        formCard.add(formTitle, fcGbc);

        // ID User
        jLabel2.setText("ID Pengguna");
        jLabel2.setFont(UITheme.FONT_LABEL);
        jLabel2.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel2, fcGbc);

        UITheme.styleTextField(iduser);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 12, 0);
        formCard.add(iduser, fcGbc);

        // Nama Lengkap
        jLabel3.setText("Nama Lengkap Petugas");
        jLabel3.setFont(UITheme.FONT_LABEL);
        jLabel3.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel3, fcGbc);

        UITheme.styleTextField(namauser);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 12, 0);
        formCard.add(namauser, fcGbc);

        // Username
        jLabel4.setText("Username Login");
        jLabel4.setFont(UITheme.FONT_LABEL);
        jLabel4.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel4, fcGbc);

        UITheme.styleTextField(username);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 12, 0);
        formCard.add(username, fcGbc);

        // Password
        jLabel5.setText("Kata Sandi (Password)");
        jLabel5.setFont(UITheme.FONT_LABEL);
        jLabel5.setForeground(UITheme.TEXT_DARK);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(jLabel5, fcGbc);

        UITheme.styleTextField(password);
        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 18, 0);
        formCard.add(password, fcGbc);

        // Action Buttons Grid
        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        btnPanel.setBackground(UITheme.BG_CARD);

        UITheme.styleButton(jButton1, UITheme.SUCCESS, Color.WHITE);
        UITheme.styleButton(ubah, UITheme.PRIMARY, Color.WHITE);
        UITheme.styleButton(hapus, UITheme.DANGER, Color.WHITE);

        JButton resetBtn = new JButton("Reset");
        UITheme.styleOutlineButton(resetBtn, UITheme.BORDER_COLOR, UITheme.TEXT_MUTED);
        resetBtn.addActionListener(e -> resetForm());

        btnPanel.add(jButton1);
        btnPanel.add(ubah);
        btnPanel.add(hapus);
        btnPanel.add(resetBtn);

        fcGbc.gridy = row++;
        fcGbc.insets = new Insets(0, 0, 0, 0);
        formCard.add(btnPanel, fcGbc);

        // Left Card constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.35;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 18);
        content.add(formCard, gbc);

        // --- RIGHT CARD: User Table ---
        JPanel tableCard = UITheme.createCard();
        tableCard.setLayout(new BorderLayout(0, 12));

        JPanel tableHeader = new JPanel(new BorderLayout());
        tableHeader.setBackground(UITheme.BG_CARD);

        JLabel tableTitle = new JLabel("Daftar Pengguna Aktif");
        tableTitle.setFont(UITheme.FONT_SECTION);
        tableTitle.setForeground(UITheme.PRIMARY);

        userCountBadge = new JLabel("Total: 0 user");
        userCountBadge.setFont(UITheme.FONT_LABEL);
        userCountBadge.setForeground(UITheme.ACCENT);

        tableHeader.add(tableTitle, BorderLayout.WEST);
        tableHeader.add(userCountBadge, BorderLayout.EAST);
        tableCard.add(tableHeader, BorderLayout.NORTH);

        tableCard.add(jScrollPane2, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.weightx = 0.65;
        gbc.insets = new Insets(0, 0, 0, 0);
        content.add(tableCard, gbc);

        // Wrap content inside smooth scroll pane
        add(UITheme.createPageScrollPane(content), BorderLayout.CENTER);

        setSize(980, 620);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    private void resetForm() {
        iduser.setText("");
        namauser.setText("");
        username.setText("");
        password.setText("");
        tabel.clearSelection();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (iduser.getText().trim().isEmpty() || username.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan lengkapi ID, Nama, Username, dan Kata Sandi.", "Input Belum Lengkap", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection sql = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            PreparedStatement ps = sql.prepareStatement("INSERT INTO user (id_user, nama_user, username, password) VALUES (?, ?, ?, ?)");
            ps.setString(1, iduser.getText().trim());
            ps.setString(2, namauser.getText().trim());
            ps.setString(3, username.getText().trim());
            ps.setString(4, password.getText().trim());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Akun pengguna baru berhasil ditambahkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            tampilkan();
            resetForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan user:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        if (iduser.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih akun pengguna yang ingin diperbarui dari tabel.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            PreparedStatement ps = koneksi.prepareStatement("UPDATE user SET nama_user=?, username=?, password=? WHERE id_user=?");
            ps.setString(1, namauser.getText().trim());
            ps.setString(2, username.getText().trim());
            ps.setString(3, password.getText().trim());
            ps.setString(4, iduser.getText().trim());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data pengguna berhasil diperbarui.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            tampilkan();
            resetForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui pengguna: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ubahActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        int i = tabel.getSelectedRow();
        if (i > -1) {
            iduser.setText(model.getValueAt(i, 0).toString());
            namauser.setText(model.getValueAt(i, 1).toString());
            username.setText(model.getValueAt(i, 2).toString());
            password.setText(model.getValueAt(i, 3).toString());
        }
    }//GEN-LAST:event_tabelMouseClicked

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        if (iduser.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih akun pengguna yang ingin dihapus dari tabel.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus akun '" + username.getText() + "'?",
                "Konfirmasi Hapus Akun",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection sql = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
                PreparedStatement ps = sql.prepareStatement("DELETE FROM user WHERE id_user=?");
                ps.setString(1, iduser.getText().trim());
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Akun pengguna berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                tampilkan();
                resetForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void tampilkan() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            ResultSet res = con.createStatement().executeQuery("SELECT id_user, nama_user, username, password FROM user ORDER BY id_user ASC");
            model.setRowCount(0);
            int count = 0;
            while (res.next()) {
                String data[] = {res.getString(1), res.getString(2), res.getString(3), res.getString(4)};
                model.addRow(data);
                count++;
            }
            if (userCountBadge != null) {
                userCountBadge.setText("Total: " + count + " akun terdaftar");
            }
        } catch (SQLException e) {
            // Error handling
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // fallback
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new user().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hapus;
    private javax.swing.JTextField iduser;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField namauser;
    private javax.swing.JTextField password;
    private javax.swing.JTable tabel;
    private javax.swing.JButton ubah;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
