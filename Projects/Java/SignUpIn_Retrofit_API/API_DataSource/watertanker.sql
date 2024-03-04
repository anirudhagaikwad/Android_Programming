-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 12, 2019 at 06:59 AM
-- Server version: 5.6.45
-- PHP Version: 7.0.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `watertanker`
--

-- --------------------------------------------------------

--
-- Table structure for table `cneworder`
--

CREATE TABLE `cneworder` (
  `idorder` int(11) NOT NULL,
  `daddress` varchar(60) DEFAULT NULL,
  `ddate` varchar(15) DEFAULT NULL,
  `waterquantity` varchar(20) DEFAULT NULL,
  `suppliername` varchar(30) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customerregistration`
--

CREATE TABLE `customerregistration` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `mobile` varchar(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `passwd` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customerregistration`
--

INSERT INTO `customerregistration` (`id`, `username`, `mobile`, `email`, `address`, `passwd`) VALUES
(28, 'Anirudha Anil Gaikwad', '1234567890', 'qqqwrtihfa@hjuyegk.com', 'ojaswi technologies,midc,larur', '202cb962ac59075b964b07152d234b70'),
(30, 'amit', '7337517271', 'naikprasad365@gmail.com', 'kani', '202cb962ac59075b964b07152d234b70'),
(31, 'deepak', '9970663315', 'deepakmashalkar@gmail.com', 'ggf', '81dc9bdb52d04dc20036dbd8313ed055');

-- --------------------------------------------------------

--
-- Table structure for table `driverlocation`
--

CREATE TABLE `driverlocation` (
  `idlocation` int(11) NOT NULL,
  `latitudesdata` varchar(255) DEFAULT NULL,
  `longitudesdata` varchar(255) DEFAULT NULL,
  `driverid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `driverlocation`
--

INSERT INTO `driverlocation` (`idlocation`, `latitudesdata`, `longitudesdata`, `driverid`) VALUES
(4, '18.4087243', '76.543189', 19);

-- --------------------------------------------------------

--
-- Table structure for table `driverregistration`
--

CREATE TABLE `driverregistration` (
  `iddriver` int(11) NOT NULL,
  `usernamedriver` varchar(50) NOT NULL,
  `mobiledriver` varchar(11) NOT NULL,
  `emaildriver` varchar(50) NOT NULL,
  `suppliernamedriver` varchar(50) NOT NULL,
  `passwddriver` varchar(255) NOT NULL,
  `dstatus` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `driverregistration`
--

INSERT INTO `driverregistration` (`iddriver`, `usernamedriver`, `mobiledriver`, `emaildriver`, `suppliernamedriver`, `passwddriver`, `dstatus`) VALUES
(0, 'NA', '0', 'NA', 'NA', '', 0),
(18, 'anirudha', '9007915552', 'ab@ab.com', 'Jagtap Water Suppliers', '202cb962ac59075b964b07152d234b70', 0),
(19, 'mydriver2', '123456789', 'ry@dg.mh', 'Jagtap Water Suppliers', '202cb962ac59075b964b07152d234b70', 0),
(23, 'driver4', '123456', 'sjdk@dk.com', 'jagtap', '123', 0);

-- --------------------------------------------------------

--
-- Table structure for table `placedorders`
--

CREATE TABLE `placedorders` (
  `idorder` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `daddress` varchar(100) DEFAULT NULL,
  `ddate` varchar(15) DEFAULT NULL,
  `waterquantity` varchar(20) DEFAULT NULL,
  `suppliername` varchar(60) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  `Orderstatus` int(4) NOT NULL DEFAULT '0',
  `keyiddriver` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `placedorders`
--

INSERT INTO `placedorders` (`idorder`, `username`, `mobile`, `email`, `address`, `daddress`, `ddate`, `waterquantity`, `suppliername`, `iduser`, `Orderstatus`, `keyiddriver`) VALUES
(56, 'Anirudha Anil Gaikwad', '1234567890', 'qqqwrtihfa@hjuyegk.com', 'ojaswi technologies,midc,larur', 'à¤²à¤¾à¤¤à¥‚à¤°', '26/12/2019', '3000 Liter', 'Jagtap Water Suppliers', 28, 1, 19),
(57, 'Anirudha Anil Gaikwad', '1234567890', 'qqqwrtihfa@hjuyegk.com', 'ojaswi technologies,midc,larur', 'MIDC Police Station', '19/12/2019', '5000 Liter', 'Jagtap Water Suppliers', 28, 1, 19),
(58, 'Anirudha Anil Gaikwad', '1234567890', 'qqqwrtihfa@hjuyegk.com', 'ojaswi technologies,midc,larur', 'Ojaswi Tech Latur', '18/12/2019', '10000 Liter', 'Jagtap Water Suppliers', 28, 1, 19),
(59, 'Anirudha Anil Gaikwad', '1234567890', 'qqqwrtihfa@hjuyegk.com', 'ojaswi technologies,midc,larur', 'hfdgj', '11/12/2019', '2000 Liter', 'Jagtap Water Suppliers', 28, 0, 19),
(60, 'Anirudha Anil Gaikwad', '1234567890', 'qqqwrtihfa@hjuyegk.com', 'ojaswi technologies,midc,larur', 'outf', '19/12/2019', '500 Liter', 'Jagtap Water Suppliers', 28, 0, 0),
(61, 'Anirudha Anil Gaikwad', '1234567890', 'qqqwrtihfa@hjuyegk.com', 'ojaswi technologies,midc,larur', 'kgds', '11/12/2019', '5000 Liter', 'Jagtap Water Suppliers', 28, 0, 19),
(62, 'Anirudha Anil Gaikwad', '1234567890', 'qqqwrtihfa@hjuyegk.com', 'ojaswi technologies,midc,larur', 'ufu', '11/12/2019', '3000 Liter', 'Jagtap Water Suppliers', 28, 1, 18),
(64, 'Anirudha Anil Gaikwad', '1234567890', 'qqqwrtihfa@hjuyegk.com', 'ojaswi technologies,midc,larur', 'police station', '18/12/2019', '1000 Liter', 'Jagtap Water Suppliers', 28, 0, 18),
(65, 'amit', '7337517271', 'naikprasad365@gmail.com', 'kani', 'kani', '10/12/2019', '500 Liter', 'Jagtap Water Suppliers', 30, 1, 18),
(66, 'Anirudha Anil Gaikwad', '1234567890', 'qqqwrtihfa@hjuyegk.com', 'ojaswi technologies,midc,larur', 'à¤²à¤¾à¤¤à¥‚à¤°', '18/12/2019', '2000 Liter', 'Jagtap Water Suppliers', 28, 0, 18),
(67, 'deepak', '9970663315', 'deepakmashalkar@gmail.com', 'ggf', 'ss', '12/12/2019', '3000 Liter', 'Jagtap Water Suppliers', 31, 0, 23);

-- --------------------------------------------------------

--
-- Table structure for table `supplierregistration`
--

CREATE TABLE `supplierregistration` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `mobile` varchar(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `passwd` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplierregistration`
--

INSERT INTO `supplierregistration` (`id`, `username`, `mobile`, `email`, `passwd`) VALUES
(5, '8007915554', '8007915554', 'ajg@qh.zj', '202cb962ac59075b964b07152d234b70');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cneworder`
--
ALTER TABLE `cneworder`
  ADD PRIMARY KEY (`idorder`),
  ADD KEY `iduser` (`iduser`);

--
-- Indexes for table `customerregistration`
--
ALTER TABLE `customerregistration`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `driverlocation`
--
ALTER TABLE `driverlocation`
  ADD PRIMARY KEY (`idlocation`),
  ADD KEY `driverid` (`driverid`);

--
-- Indexes for table `driverregistration`
--
ALTER TABLE `driverregistration`
  ADD PRIMARY KEY (`iddriver`);

--
-- Indexes for table `placedorders`
--
ALTER TABLE `placedorders`
  ADD PRIMARY KEY (`idorder`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `order_Approverd` (`keyiddriver`);

--
-- Indexes for table `supplierregistration`
--
ALTER TABLE `supplierregistration`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cneworder`
--
ALTER TABLE `cneworder`
  MODIFY `idorder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `customerregistration`
--
ALTER TABLE `customerregistration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `driverlocation`
--
ALTER TABLE `driverlocation`
  MODIFY `idlocation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `driverregistration`
--
ALTER TABLE `driverregistration`
  MODIFY `iddriver` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `placedorders`
--
ALTER TABLE `placedorders`
  MODIFY `idorder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT for table `supplierregistration`
--
ALTER TABLE `supplierregistration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cneworder`
--
ALTER TABLE `cneworder`
  ADD CONSTRAINT `cneworder_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `customerregistration` (`id`);

--
-- Constraints for table `driverlocation`
--
ALTER TABLE `driverlocation`
  ADD CONSTRAINT `driverlocation_ibfk_1` FOREIGN KEY (`driverid`) REFERENCES `driverregistration` (`iddriver`);

--
-- Constraints for table `placedorders`
--
ALTER TABLE `placedorders`
  ADD CONSTRAINT `order_Approverd` FOREIGN KEY (`keyiddriver`) REFERENCES `driverregistration` (`iddriver`),
  ADD CONSTRAINT `placedorders_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `customerregistration` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
