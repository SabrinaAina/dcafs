-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2023 at 05:29 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dcafs`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`name`, `email`, `password`) VALUES
('Aina Khaliesa', 'aina@gmail.com', 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryID` int(2) NOT NULL,
  `categoryType` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryID`, `categoryType`) VALUES
(1, 'Classroom'),
(2, 'Teacher'),
(3, 'Subject'),
(4, 'Cleanliness'),
(5, 'Food'),
(6, 'Other');

-- --------------------------------------------------------

--
-- Table structure for table `complaint`
--

CREATE TABLE `complaint` (
  `complaintID` int(3) NOT NULL,
  `categoryID` int(2) NOT NULL,
  `ICNumber` varchar(12) NOT NULL,
  `complaintDescription` varchar(255) DEFAULT NULL,
  `complaintDate` varchar(10) NOT NULL,
  `complaintTime` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complaint`
--

INSERT INTO `complaint` (`complaintID`, `categoryID`, `ICNumber`, `complaintDescription`, `complaintDate`, `complaintTime`) VALUES
(10, 2, '011018010092', 'Guru perlu mengajar dengan lebih menarik', '10/06/2022', '16:47:46'),
(11, 4, '010918010061', 'Semak-samun tidak dibersihkan di belakang tadika', '10/06/2022', '16:47:54'),
(12, 1, '010419011614', 'Kelas kekurangan kerusi dan meja', '15/06/2022', '23:00:54'),
(13, 4, '011018010092', 'Lantai di kantin licin ', '15/06/2022', '23:02:24'),
(14, 6, '010918010061', 'Tempat parking tidak cukup', '15/06/2022', '23:02:48'),
(15, 1, '980119018776', 'Suasana di kelas panas', '15/06/2022', '23:03:00'),
(19, 6, '010419011614', 'Parking sempit', '16/06/2022', '22:05:48'),
(20, 6, '720731015958', 'Parking sempit', '17/06/2022', '16:19:24');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedbackID` int(3) NOT NULL,
  `categoryID` int(2) NOT NULL,
  `ICNumber` varchar(12) NOT NULL,
  `feedbackDescription` varchar(255) NOT NULL,
  `feedbackRating` int(2) NOT NULL,
  `feedbackDate` varchar(10) NOT NULL,
  `feedbackTime` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedbackID`, `categoryID`, `ICNumber`, `feedbackDescription`, `feedbackRating`, `feedbackDate`, `feedbackTime`) VALUES
(11, 1, '011018010092', 'Kelas tidak cukup meja dan kerusi ', 3, '09/06/2022', '01:07:27'),
(12, 1, '010419011614', 'Keadaan kelas tidak bersih', 3, '10/06/2022', '21:07:47'),
(13, 4, '011018010092', 'Kebersihan di kawasan sekolah sangat teruk', 2, '12/06/2022', '23:10:09'),
(14, 4, '010812018284', 'Kawasan parking terdapat banyak sampah', 6, '14/06/2022', '22:46:27'),
(15, 2, '010812018284', 'Cara pembelajaran yang diajar mudah difahami', 9, '15/06/2022', '22:57:46'),
(16, 3, '010918010061', 'Silibus subjek tidak bersesuaian', 5, '15/06/2022', '22:57:58'),
(17, 1, '010918010061', 'Kelas kotor', 6, '15/06/2022', '22:58:15'),
(18, 6, '980119018776', 'Latihan diberikan terlalu banyak', 6, '15/06/2022', '22:58:35'),
(19, 4, '980119018776', 'Masa rehat pelajar amat singkat', 5, '15/06/2022', '22:58:43'),
(21, 2, '011018010092', 'Cikgu amat mesra bersama pelajar', 8, '15/06/2022', '22:59:11'),
(22, 5, '011018010092', 'Makanan yang disediakan cukup mengikut masa', 9, '16/06/2022', '05:24:21'),
(26, 6, '010419011614', 'Kurang penganjuran program bersama ibu bapa', 6, '16/06/2022', '17:06:02'),
(27, 1, '010419011614', 'Kelas kurang bersih', 6, '16/06/2022', '22:04:20'),
(28, 4, '720731015958', 'Toilet tidak dibersihkan dengan berkala', 7, '17/06/2022', '16:16:51');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `name` varchar(30) NOT NULL,
  `ICNumber` varchar(12) NOT NULL,
  `phoneNumber` varchar(14) NOT NULL,
  `password` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`name`, `ICNumber`, `phoneNumber`, `password`) VALUES
('Aina Khaliesa', '010419011614', '0146716298', 'aina1234'),
('Sadiah Mahat', '010918010061', '0198764321', '1234'),
('Moslee Nawawi', '680131015117', '0157116912', 'moslee68'),
('Aqilah Najeeha', '720731015958', '0196743216', 'aqilah1'),
('Aliah Batrisyia', '980119018776', '0165432155', '90901'),
('Aidil Asyraf', '990124010091', '0187153421', 'aidil99');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`email`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryID`);

--
-- Indexes for table `complaint`
--
ALTER TABLE `complaint`
  ADD PRIMARY KEY (`complaintID`),
  ADD KEY `categoryID` (`categoryID`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedbackID`),
  ADD KEY `categoryID` (`categoryID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ICNumber`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `categoryID` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `complaint`
--
ALTER TABLE `complaint`
  MODIFY `complaintID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedbackID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `complaint`
--
ALTER TABLE `complaint`
  ADD CONSTRAINT `complaint_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `categoryID` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
