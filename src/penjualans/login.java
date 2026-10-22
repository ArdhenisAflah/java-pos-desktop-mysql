package penjualans;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Modern Classic Login Window for POS Desktop Application.
 * Author: Ardhenis Muhammad Aflah
 */
public class login extends javax.swing.JFrame {

    public login() {
        initComponents();
        setupModernLayout();
    }

    /**
     * Initializes components with NetBeans compatibility.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - Sistem Kasir & Penjualan");
        setBackground(UITheme.BG_CANVAS);
        setResizable(false);

        jButton1.setText("Masuk ke Sistem");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Constructs the Modern Classic White layout programmatically.
     */
    private void setupModernLayout() {
        getContentPane().removeAll();
        getContentPane().setBackground(UITheme.BG_CANVAS);
        setLayout(new GridBagLayout());

        // Card Container
        JPanel card = UITheme.createCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(380, 440));
        card.setMaximumSize(new Dimension(380, 440));
        card.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.LineBorder(UITheme.BORDER_COLOR, 1, true),
                new EmptyBorder(32, 28, 32, 28)
        ));

        // System Badge / Header
        JLabel badge = new JLabel("POS SYSTEM");
        badge.setFont(new Font("Segoe UI", Font.BOLD, 11));
        badge.setForeground(UITheme.ACCENT);
        badge.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Title
        jLabel1.setText("Selamat Datang");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 22));
        jLabel1.setForeground(UITheme.PRIMARY);
        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel subtitle = new JLabel("Silakan masuk untuk melanjutkan transaksi");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(UITheme.TEXT_MUTED);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username Label & Field
        jLabel2.setText("Username");
        jLabel2.setFont(UITheme.FONT_LABEL);
        jLabel2.setForeground(UITheme.TEXT_DARK);
        jLabel2.setAlignmentX(Component.LEFT_ALIGNMENT);

        UITheme.styleTextField(username);
        username.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        username.setPreferredSize(new Dimension(320, 38));
        username.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Password Label & Field
        jLabel3.setText("Kata Sandi");
        jLabel3.setFont(UITheme.FONT_LABEL);
        jLabel3.setForeground(UITheme.TEXT_DARK);
        jLabel3.setAlignmentX(Component.LEFT_ALIGNMENT);

        UITheme.stylePasswordField(password);
        password.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        password.setPreferredSize(new Dimension(320, 38));
        password.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Login Button
        UITheme.styleButton(jButton1, UITheme.PRIMARY, Color.WHITE);
        jButton1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        jButton1.setPreferredSize(new Dimension(320, 40));
        jButton1.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Enter key submits login
        getRootPane().setDefaultButton(jButton1);

        // Footer Hint
        JLabel hint = new JLabel("Akun default: admin / admin");
        hint.setFont(UITheme.FONT_SMALL);
        hint.setForeground(UITheme.TEXT_MUTED);
        hint.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Assemble into Card
        card.add(badge);
        card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(jLabel1);
        card.add(Box.createRigidArea(new Dimension(0, 4)));
        card.add(subtitle);
        card.add(Box.createRigidArea(new Dimension(0, 24)));

        card.add(jLabel2);
        card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(username);
        card.add(Box.createRigidArea(new Dimension(0, 14)));

        card.add(jLabel3);
        card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(password);
        card.add(Box.createRigidArea(new Dimension(0, 22)));

        card.add(jButton1);
        card.add(Box.createRigidArea(new Dimension(0, 16)));
        card.add(hint);

        add(card);
        setSize(480, 560);
        setLocationRelativeTo(null);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String userVal = username.getText().trim();
        String passVal = new String(password.getPassword()).trim();

        if (userVal.isEmpty() || passVal.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Silakan masukkan username dan kata sandi.",
                    "Input Belum Lengkap",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/program_aplikasi", "root", "");
            java.sql.PreparedStatement ps = koneksi.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
            ps.setString(1, userVal);
            ps.setString(2, passVal);
            ResultSet as = ps.executeQuery();

            if (as.next()) {
                menu ad = new menu();
                ad.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Username atau kata sandi yang Anda masukkan salah.",
                        "Autentikasi Gagal",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Koneksi ke database gagal:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // fallback to default look and feel
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
