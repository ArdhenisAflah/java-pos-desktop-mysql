package penjualans;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Modern Classic Dashboard for POS Desktop Application.
 * Author: Ardhenis Muhammad Aflah
 */
public class menu extends javax.swing.JFrame {

    public menu() {
        initComponents();
        setupModernDashboard();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard - Sistem Kasir & Penjualan");
        setBackground(UITheme.BG_CANVAS);

        jMenu1.setText("Navigasi Menu");

        jMenuItem1.setText("Menu Pengguna (User)");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Menu Data Barang");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Menu Transaksi Penjualan");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Menu Laporan Penjualan");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Keluar / Logout");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);
        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void setupModernDashboard() {
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(UITheme.BG_CANVAS);

        // Style Menu Bar
        jMenuBar1.setBackground(UITheme.BG_CARD);
        jMenuBar1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UITheme.BORDER_COLOR));

        // Top Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UITheme.BG_CARD);
        header.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, UITheme.BORDER_COLOR),
                new EmptyBorder(20, 28, 20, 28)
        ));

        JPanel titleBlock = new JPanel(new GridLayout(2, 1, 0, 4));
        titleBlock.setBackground(UITheme.BG_CARD);

        jLabel1.setText("Point of Sales (POS) Dashboard");
        jLabel1.setFont(UITheme.FONT_TITLE);
        jLabel1.setForeground(UITheme.PRIMARY);

        jLabel2.setText("Sistem Manajemen Penjualan, Inventaris Barang, dan Pelaporan");
        jLabel2.setFont(UITheme.FONT_SUBTITLE);
        jLabel2.setForeground(UITheme.TEXT_MUTED);

        titleBlock.add(jLabel1);
        titleBlock.add(jLabel2);
        header.add(titleBlock, BorderLayout.WEST);

        // Right User Badge + Logout
        JPanel userBlock = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        userBlock.setBackground(UITheme.BG_CARD);

        JLabel statusBadge = new JLabel("● Sesi Aktif: Administrator");
        statusBadge.setFont(UITheme.FONT_LABEL);
        statusBadge.setForeground(UITheme.SUCCESS);

        JButton logoutBtn = new JButton("Logout");
        UITheme.styleOutlineButton(logoutBtn, UITheme.BORDER_COLOR, UITheme.DANGER);
        logoutBtn.addActionListener(e -> jMenuItem5ActionPerformed(null));

        userBlock.add(statusBadge);
        userBlock.add(logoutBtn);
        header.add(userBlock, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Main Content Area: 2x2 Grid of Cards
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(UITheme.BG_CANVAS);
        mainContent.setBorder(new EmptyBorder(28, 28, 28, 28));

        JLabel welcomeLabel = new JLabel("Menu Navigasi Utama");
        welcomeLabel.setFont(UITheme.FONT_HEADER);
        welcomeLabel.setForeground(UITheme.PRIMARY);
        welcomeLabel.setBorder(new EmptyBorder(0, 0, 16, 0));
        mainContent.add(welcomeLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        gridPanel.setBackground(UITheme.BG_CANVAS);

        // Card 1: Data Barang
        gridPanel.add(createDashboardCard(
                "📦 Data Barang",
                "Katalog Produk & Inventaris",
                "Kelola informasi produk, perbarui harga jual satuan, dan pantau ketersediaan stok barang.",
                "Buka Data Barang →",
                UITheme.PRIMARY,
                e -> jMenuItem2ActionPerformed(null)
        ));

        // Card 2: Transaksi Penjualan
        gridPanel.add(createDashboardCard(
                "💳 Transaksi Kasir",
                "Input Penjualan Baru",
                "Proses order belanja pelanggan, pilih kasir & produk, hitung total otomatis, dan simpan transaksi.",
                "Buka Transaksi Penjualan →",
                UITheme.ACCENT,
                e -> jMenuItem3ActionPerformed(null)
        ));

        // Card 3: Kelola User
        gridPanel.add(createDashboardCard(
                "👥 Kelola Pengguna",
                "Akun & Hak Akses",
                "Kelola akun administrator dan staf kasir toko yang memiliki hak akses transaksi ke sistem.",
                "Buka Manajemen Pengguna →",
                UITheme.PRIMARY,
                e -> jMenuItem1ActionPerformed(null)
        ));

        // Card 4: Laporan Penjualan
        gridPanel.add(createDashboardCard(
                "📊 Laporan Penjualan",
                "Rekapitulasi & Cetak Struk",
                "Lihat riwayat keseluruhan transaksi penjualan dan cetak laporan resmi via JasperReports.",
                "Buka Laporan Penjualan →",
                UITheme.SUCCESS,
                e -> jMenuItem4ActionPerformed(null)
        ));

        mainContent.add(gridPanel, BorderLayout.CENTER);
        add(UITheme.createPageScrollPane(mainContent), BorderLayout.CENTER);

        setSize(920, 620);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    private JPanel createDashboardCard(String tag, String title, String description, String buttonText, Color btnColor, java.awt.event.ActionListener action) {
        JPanel card = UITheme.createCard();
        card.setLayout(new BorderLayout(0, 12));

        JPanel top = new JPanel(new GridLayout(2, 1, 0, 4));
        top.setBackground(UITheme.BG_CARD);

        JLabel tagLbl = new JLabel(tag);
        tagLbl.setFont(UITheme.FONT_SECTION);
        tagLbl.setForeground(btnColor);

        JLabel descLbl = new JLabel("<html><body style='width: 260px; color: #64748B;'>" + description + "</body></html>");
        descLbl.setFont(UITheme.FONT_BODY);

        top.add(tagLbl);
        top.add(descLbl);
        card.add(top, BorderLayout.CENTER);

        JButton actionBtn = new JButton(buttonText);
        UITheme.styleButton(actionBtn, btnColor, Color.WHITE);
        actionBtn.addActionListener(action);
        card.add(actionBtn, BorderLayout.SOUTH);

        return card;
    }

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin keluar dari sistem kasir?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            new login().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new laporan().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new penjualan().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new barang().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new user().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    // End of variables declaration//GEN-END:variables
}
