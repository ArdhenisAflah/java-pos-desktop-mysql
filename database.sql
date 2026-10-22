-- Database schema for Point of Sales Application (program_aplikasi)
CREATE DATABASE IF NOT EXISTS `program_aplikasi`;
USE `program_aplikasi`;

-- Table structure for `user`
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` varchar(50) NOT NULL,
  `nama_user` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Sample data for `user` (Default login)
INSERT INTO `user` (`id_user`, `nama_user`, `username`, `password`) VALUES
('USR001', 'Administrator', 'admin', 'admin')
ON DUPLICATE KEY UPDATE `nama_user`=VALUES(`nama_user`);

-- Table structure for `barang`
CREATE TABLE IF NOT EXISTS `barang` (
  `id_barang` varchar(50) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `harga` varchar(50) NOT NULL,
  `stok` varchar(50) NOT NULL,
  PRIMARY KEY (`id_barang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Sample data for `barang`
INSERT INTO `barang` (`id_barang`, `nama_barang`, `harga`, `stok`) VALUES
('BRG001', 'Buku Tulis', '5000', '100'),
('BRG002', 'Pulpen Gel', '3500', '150'),
('BRG003', 'Pensil 2B', '2500', '80')
ON DUPLICATE KEY UPDATE `nama_barang`=VALUES(`nama_barang`);

-- Table structure for `penjualan`
CREATE TABLE IF NOT EXISTS `penjualan` (
  `id_penjualan` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
  `id_user` varchar(100) NOT NULL,
  `id_barang` varchar(100) NOT NULL,
  `jumlah` varchar(50) NOT NULL,
  `total` varchar(50) NOT NULL,
  PRIMARY KEY (`id_penjualan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
