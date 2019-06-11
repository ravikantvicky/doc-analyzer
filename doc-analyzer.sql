-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 11, 2019 at 07:42 PM
-- Server version: 5.7.26-0ubuntu0.19.04.1
-- PHP Version: 7.2.19-0ubuntu0.19.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `doc_analyzer`
--

-- --------------------------------------------------------

--
-- Table structure for table `templates`
--

CREATE TABLE `templates` (
  `id` int(11) NOT NULL,
  `type_name` varchar(60) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `template_data`
--

CREATE TABLE `template_data` (
  `id` int(11) NOT NULL,
  `template_id` int(11) NOT NULL,
  `label` varchar(255) NOT NULL,
  `field_key` varchar(255) NOT NULL COMMENT 'JSON key/DB column name',
  `page_id` int(11) NOT NULL,
  `start_x` double NOT NULL,
  `start_y` double NOT NULL,
  `end_x` double NOT NULL,
  `end_y` double NOT NULL,
  `type` varchar(20) NOT NULL,
  `validation_rule` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `template_pages`
--

CREATE TABLE `template_pages` (
  `id` int(11) NOT NULL,
  `template_id` int(11) NOT NULL,
  `page_no` int(11) NOT NULL,
  `width` double NOT NULL,
  `height` double NOT NULL,
  `image` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `templates`
--
ALTER TABLE `templates`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `template_data`
--
ALTER TABLE `template_data`
  ADD PRIMARY KEY (`id`),
  ADD KEY `temp_data_id` (`template_id`),
  ADD KEY `temp_page_id` (`page_id`);

--
-- Indexes for table `template_pages`
--
ALTER TABLE `template_pages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `template_pages_temp_id` (`template_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `templates`
--
ALTER TABLE `templates`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `template_data`
--
ALTER TABLE `template_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `template_pages`
--
ALTER TABLE `template_pages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `template_data`
--
ALTER TABLE `template_data`
  ADD CONSTRAINT `temp_data_id` FOREIGN KEY (`template_id`) REFERENCES `templates` (`id`),
  ADD CONSTRAINT `temp_page_id` FOREIGN KEY (`page_id`) REFERENCES `template_pages` (`id`);

--
-- Constraints for table `template_pages`
--
ALTER TABLE `template_pages`
  ADD CONSTRAINT `template_pages_temp_id` FOREIGN KEY (`template_id`) REFERENCES `templates` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
