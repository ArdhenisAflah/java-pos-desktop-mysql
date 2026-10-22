package penjualans;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * UITheme - Modern Classic Design System for Java POS Desktop Application.
 * Author: Ardhenis Muhammad Aflah
 */
public class UITheme {

    // Modern Classic Color Palette
    public static final Color BG_CANVAS      = new Color(248, 250, 252); // #F8FAFC
    public static final Color BG_CARD        = new Color(255, 255, 255); // Pure White
    public static final Color PRIMARY        = new Color(30, 41, 59);    // #1E293B Classic Slate
    public static final Color PRIMARY_HOVER  = new Color(51, 65, 85);    // #334155
    public static final Color ACCENT         = new Color(37, 99, 235);   // #2563EB Royal Blue
    public static final Color ACCENT_HOVER   = new Color(29, 78, 216);   // #1D4ED8
    public static final Color SUCCESS        = new Color(22, 163, 74);   // #16A34A Emerald Green
    public static final Color SUCCESS_HOVER  = new Color(34, 197, 94);   // #22C55E
    public static final Color DANGER         = new Color(220, 38, 38);   // #DC2626 Crimson
    public static final Color DANGER_HOVER   = new Color(239, 68, 68);   // #EF4444
    public static final Color WARNING        = new Color(217, 119, 6);   // #D97706 Amber
    public static final Color TEXT_DARK      = new Color(15, 23, 42);    // #0F172A Deep Charcoal
    public static final Color TEXT_MUTED     = new Color(100, 116, 139); // #64748B Slate Muted
    public static final Color BORDER_COLOR   = new Color(226, 232, 240); // #E2E8F0 Soft Border
    public static final Color BORDER_ACTIVE  = new Color(148, 163, 184); // #94A3B8 Focus Border
    public static final Color TABLE_ALT_ROW  = new Color(248, 250, 252); // #F8FAFC
    public static final Color TABLE_HOVER    = new Color(241, 245, 249); // #F1F5F9
    public static final Color TABLE_SELECTED = new Color(224, 231, 255); // #E0E7FF

    // Typography
    public static final Font FONT_TITLE    = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_HEADER   = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FONT_SECTION = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_LABEL   = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FONT_INPUT   = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_BODY    = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_BUTTON  = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FONT_SMALL   = new Font("Segoe UI", Font.PLAIN, 11);

    /**
     * Styles a standard JButton with modern flat colors, hover effect, and clean border.
     */
    public static void styleButton(JButton btn, Color bgColor, Color fgColor) {
        btn.setFont(FONT_BUTTON);
        btn.setBackground(bgColor);
        btn.setForeground(fgColor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(9, 16, 9, 16));

        final Color normalColor = bgColor;
        final Color hoverColor = (bgColor.equals(PRIMARY)) ? PRIMARY_HOVER
                : (bgColor.equals(SUCCESS)) ? SUCCESS_HOVER
                : (bgColor.equals(DANGER)) ? DANGER_HOVER
                : (bgColor.equals(ACCENT)) ? ACCENT_HOVER
                : normalColor.darker();

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hoverColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(normalColor);
            }
        });
    }

    /**
     * Styles an outline / secondary button.
     */
    public static void styleOutlineButton(JButton btn, Color borderColor, Color textColor) {
        btn.setFont(FONT_BUTTON);
        btn.setBackground(BG_CARD);
        btn.setForeground(textColor);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new CompoundBorder(
                new LineBorder(borderColor, 1, true),
                new EmptyBorder(8, 14, 8, 14)
        ));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(TABLE_HOVER);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(BG_CARD);
            }
        });
    }

    /**
     * Styles a JTextField with clean borders, padding, and subtle focus state.
     */
    public static void styleTextField(JTextField tf) {
        tf.setFont(FONT_INPUT);
        tf.setForeground(TEXT_DARK);
        tf.setBackground(BG_CARD);
        tf.setCaretColor(PRIMARY);
        Border normalBorder = new CompoundBorder(
                new LineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(7, 10, 7, 10)
        );
        Border focusBorder = new CompoundBorder(
                new LineBorder(ACCENT, 1, true),
                new EmptyBorder(7, 10, 7, 10)
        );
        tf.setBorder(normalBorder);
        tf.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf.setBorder(focusBorder);
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf.setBorder(normalBorder);
            }
        });
    }

    /**
     * Styles a JPasswordField with clean padding and focus state.
     */
    public static void stylePasswordField(JPasswordField pf) {
        pf.setFont(FONT_INPUT);
        pf.setForeground(TEXT_DARK);
        pf.setBackground(BG_CARD);
        pf.setCaretColor(PRIMARY);
        Border normalBorder = new CompoundBorder(
                new LineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(7, 10, 7, 10)
        );
        Border focusBorder = new CompoundBorder(
                new LineBorder(ACCENT, 1, true),
                new EmptyBorder(7, 10, 7, 10)
        );
        pf.setBorder(normalBorder);
        pf.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                pf.setBorder(focusBorder);
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                pf.setBorder(normalBorder);
            }
        });
    }

    /**
     * Styles JComboBox with standard typography and clean borders.
     */
    public static void styleComboBox(JComboBox<?> cb) {
        cb.setFont(FONT_INPUT);
        cb.setBackground(BG_CARD);
        cb.setForeground(TEXT_DARK);
        cb.setBorder(new CompoundBorder(
                new LineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(4, 6, 4, 6)
        ));
    }

    /**
     * Formats a JTable with generous row height, customized header, alternating rows, and selection colors.
     */
    public static void styleTable(JTable table, JScrollPane scrollPane) {
        table.setFont(FONT_BODY);
        table.setRowHeight(32);
        table.setGridColor(BORDER_COLOR);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setSelectionBackground(TABLE_SELECTED);
        table.setSelectionForeground(TEXT_DARK);

        // Header
        JTableHeader header = table.getTableHeader();
        header.setFont(FONT_LABEL);
        header.setBackground(new Color(241, 245, 249)); // #F1F5F9
        header.setForeground(PRIMARY);
        header.setPreferredSize(new Dimension(header.getWidth(), 38));
        header.setReorderingAllowed(false);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);

        // Cell Renderer for zebra striping
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? BG_CARD : TABLE_ALT_ROW);
                    c.setForeground(TEXT_DARK);
                }
                setBorder(new EmptyBorder(0, 10, 0, 10));
                return c;
            }
        });

        if (scrollPane != null) {
            scrollPane.setBorder(new LineBorder(BORDER_COLOR, 1, true));
            scrollPane.getViewport().setBackground(BG_CARD);
        }
    }

    /**
     * Creates a white Card container with subtle border and internal padding.
     */
    public static JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(BG_CARD);
        card.setBorder(new CompoundBorder(
                new LineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(18, 20, 18, 20)
        ));
        return card;
    }

    /**
     * Creates a standard top header panel with Title, Subtitle, and optional "Kembali" button.
     */
    public static JPanel createTopHeader(String titleText, String subtitleText, JFrame currentFrame, boolean showBackButton) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BG_CARD);
        headerPanel.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
                new EmptyBorder(16, 24, 16, 24)
        ));

        // Left title & subtitle
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 4));
        textPanel.setBackground(BG_CARD);

        JLabel titleLbl = new JLabel(titleText);
        titleLbl.setFont(FONT_TITLE);
        titleLbl.setForeground(PRIMARY);

        JLabel subLbl = new JLabel(subtitleText);
        subLbl.setFont(FONT_SUBTITLE);
        subLbl.setForeground(TEXT_MUTED);

        textPanel.add(titleLbl);
        textPanel.add(subLbl);
        headerPanel.add(textPanel, BorderLayout.WEST);

        // Right side (optional back button or badge)
        if (showBackButton && currentFrame != null) {
            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 4));
            rightPanel.setBackground(BG_CARD);

            JButton backBtn = new JButton("← Kembali ke Menu Utama");
            styleOutlineButton(backBtn, BORDER_COLOR, PRIMARY);
            backBtn.addActionListener(e -> {
                new menu().setVisible(true);
                currentFrame.dispose();
            });
            rightPanel.add(backBtn);
            headerPanel.add(rightPanel, BorderLayout.EAST);
        }

        return headerPanel;
    }

    /**
     * Formats integer/long currency into Indonesian Rupiah format (e.g. Rp 25.000).
     */
    public static String formatRupiah(long value) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        kursIndonesia.setMaximumFractionDigits(0);
        return kursIndonesia.format(value);
    }
}
