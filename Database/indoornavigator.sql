-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 03, 2017 at 01:52 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `indoornavigator`
--

-- --------------------------------------------------------

--
-- Table structure for table `1`
--

CREATE TABLE `1` (
  `id` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  `distance` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `1`
--

INSERT INTO `1` (`id`, `rid`, `distance`) VALUES
(1, 2, 1),
(2, 3, 2),
(3, 4, 3),
(4, 5, 4);

-- --------------------------------------------------------

--
-- Table structure for table `2`
--

CREATE TABLE `2` (
  `id` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  `distance` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `2`
--

INSERT INTO `2` (`id`, `rid`, `distance`) VALUES
(1, 1, 1),
(2, 3, 1),
(3, 4, 2),
(4, 5, 3);

-- --------------------------------------------------------

--
-- Table structure for table `3`
--

CREATE TABLE `3` (
  `id` int(11) NOT NULL,
  `rid` int(11) DEFAULT NULL,
  `distance` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `3`
--

INSERT INTO `3` (`id`, `rid`, `distance`) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 4, 1),
(4, 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `4`
--

CREATE TABLE `4` (
  `id` int(11) NOT NULL,
  `rid` int(11) DEFAULT NULL,
  `distance` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `4`
--

INSERT INTO `4` (`id`, `rid`, `distance`) VALUES
(1, 1, 3),
(2, 2, 2),
(3, 3, 1),
(4, 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `5`
--

CREATE TABLE `5` (
  `id` int(11) NOT NULL,
  `rid` int(11) DEFAULT NULL,
  `distance` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `5`
--

INSERT INTO `5` (`id`, `rid`, `distance`) VALUES
(1, 1, 4),
(2, 2, 3),
(3, 3, 2),
(4, 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id`, `name`) VALUES
(1, 'Vegetable'),
(2, 'Sea Food'),
(5, 'Books'),
(6, 'Stationery items');

-- --------------------------------------------------------

--
-- Table structure for table `subitems`
--

CREATE TABLE `subitems` (
  `sid` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `iid` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subitems`
--

INSERT INTO `subitems` (`sid`, `name`, `iid`) VALUES
(1, 'Tomato', 1),
(2, 'Potato', 1),
(3, 'Ladyfingers', 1),
(4, 'Onion', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `firstname`, `lastname`) VALUES
(1, 'a', 'a', 'a', 'a'),
(9, 'c', 'c', 'a', 'c');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `1`
--
ALTER TABLE `1`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `2`
--
ALTER TABLE `2`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `3`
--
ALTER TABLE `3`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `4`
--
ALTER TABLE `4`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `5`
--
ALTER TABLE `5`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subitems`
--
ALTER TABLE `subitems`
  ADD PRIMARY KEY (`sid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `1`
--
ALTER TABLE `1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `2`
--
ALTER TABLE `2`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `3`
--
ALTER TABLE `3`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `4`
--
ALTER TABLE `4`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `5`
--
ALTER TABLE `5`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `subitems`
--
ALTER TABLE `subitems`
  MODIFY `sid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
