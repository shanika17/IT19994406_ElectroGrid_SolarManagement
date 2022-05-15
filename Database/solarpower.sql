-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307:8111
-- Generation Time: May 15, 2022 at 07:06 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `solarpower`
--

CREATE TABLE `solarpower` (
  `solarID` int(10) NOT NULL,
  `customerName` varchar(50) NOT NULL,
  `customerAddress` varchar(70) NOT NULL,
  `capacity` decimal(5,2) NOT NULL,
  `noOfSolarPanels` int(10) NOT NULL,
  `type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `solarpower`
--

INSERT INTO `solarpower` (`solarID`, `customerName`, `customerAddress`, `capacity`, `noOfSolarPanels`, `type`) VALUES
(1, 'Shanika Basnayake', 'No 17,Vihara Lane, Negombo', '8.50', 3, 'Panasonic'),
(2, 'Thamaru Mahela', 'No 45, Temple Road, Kandy', '6.00', 2, 'LG');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `solarpower`
--
ALTER TABLE `solarpower`
  ADD PRIMARY KEY (`solarID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `solarpower`
--
ALTER TABLE `solarpower`
  MODIFY `solarID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
