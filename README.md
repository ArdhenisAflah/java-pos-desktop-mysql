# Java Point of Sales (POS) Desktop App with MySQL

A modern classic Desktop Point of Sales (Kasir & Penjualan) application built with Java Swing and MySQL, featuring clean typography, structured card layouts, and complete CRUD workflows.

## Modern Classic UI Highlights

- **Pure White & Slate Color Palette**: Crisp white cards (`#FFFFFF`) on a soft canvas (`#F8FAFC`) with classic slate headers (`#1E293B`) and subtle borders (`#E2E8F0`).
- **Standardized Typography**: Clean font scaling and hierarchy (`Segoe UI` / SansSerif) with bold section titles, descriptive field labels, and formatted badges.
- **Enhanced Login Card**: Centered modern card with masked password entry (`JPasswordField`), informative subheaders, and Enter-key submission.
- **Executive Navigation Dashboard (`menu.java`)**:
  - Live status header with active operator badge.
  - Interactive quick-action navigation cards for all modules.
- **Refined Data Forms (`barang.java` & `user.java`)**:
  - Side-by-side cards separating input controls from table listings.
  - Aligned inputs, placeholder hints, action buttons (Tambah, Perbarui, Hapus, Reset).
  - Live item count badges.
- **Cashier Transaction Workspace (`penjualan.java`)**:
  - High-visibility total billing banner displaying formatted Rupiah totals in real-time.
  - Automatic product pricing lookup, date chooser, and receipt printing.
- **Comprehensive Sales Reporting (`laporan.java`)**:
  - Integrated KPI summary metrics (Total Transaksi, Total Omset Pendapatan, Total Unit Terjual).
  - JasperReports report preview and export integration.
- **Consistent Navigation**: Seamless `← Kembali ke Menu Utama` return button across all child windows.

---

## Architecture & Tech Stack

- **Language**: Java (JDK 8 / 11 / 17 / 21)
- **GUI Framework**: Java Swing (AWT / Swing Component Hierarchy)
- **Design System**: Built-in `UITheme` utility class
- **Database**: MySQL (`program_aplikasi`) via JDBC
- **Calendar Picker**: JCalendar 1.4
- **Reporting Engine**: JasperReports 6.x

---

## Database Setup

1. Start your local MySQL / MariaDB server (via XAMPP, Laragon, standalone service, or Docker):
   ```bash
   # Quick Docker setup:
   docker run --name pos-mysql -d -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=program_aplikasi -p 3306:3306 mysql:8.0
   ```
2. Import the provided schema file [`database.sql`](database.sql):
   ```bash
   mysql -u root -p program_aplikasi < database.sql
   ```
3. Default credentials:
   - **Username**: `admin`
   - **Password**: `admin`

---

## Project Structure

```
.
├── database.sql               # Database schema & initial seed data
├── lib/                       # Portable dependencies
│   ├── mysql-connector-j-8.0.31.jar
│   ├── jcalendar-1.4.jar
│   └── jasperreports.jar
├── src/
│   └── penjualans/
│       ├── UITheme.java       # Centralized modern classic design system
│       ├── login.java         # Centered modern login card
│       ├── menu.java          # Executive POS dashboard & card grid
│       ├── barang.java        # Inventory & stock management
│       ├── user.java          # Cashier & admin account management
│       ├── penjualan.java     # Cashier checkout & transaction workspace
│       ├── laporan.java       # Sales KPI summary & report viewer
│       ├── reportPenjualan.jrxml  # JasperReport template
│       └── reportPenjualan.jasper # Compiled JasperReport binary
├── nbproject/                 # NetBeans project configuration
└── build.xml                  # Ant build script
```

---

## Running the Application

### Option 1: Direct Java Execution (Any OS)
Compile and launch right from the command line:
```bash
# Compile with all bundled libraries
javac -cp "lib/*:src" -d build/classes $(find src -name "*.java")

# Copy report assets
cp src/penjualans/*.jpg src/penjualans/*.jasper src/penjualans/*.jrxml build/classes/penjualans/ 2>/dev/null || true

# Run the Login window
java -cp "lib/*:build/classes" penjualans.login
```

### Option 2: NetBeans IDE
1. Open **NetBeans IDE**.
2. Select **File > Open Project** and choose this repository directory.
3. The project is already configured with relative library references in `lib/`.
4. Right-click `login.java` and click **Run File** (`Shift + F6`).

---

## License
Open source for educational and portfolio reference.
