-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 06, 2026 at 11:30 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `teacher_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `semester` int(11) NOT NULL,
  `hours` int(11) NOT NULL,
  `teacher_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`id`, `title`, `semester`, `hours`, `teacher_id`) VALUES
(1, 'Introduction to Programming', 1, 4, NULL),
(2, 'Systems Analysis', 1, 3, 5),
(3, 'Mathematics I', 1, 3, NULL),
(4, 'Object-Oriented Programming', 2, 4, NULL),
(5, 'Databases I', 2, 4, 5),
(6, 'Human - Computer Interaction', 2, 3, 4),
(7, 'Application Development', 3, 4, NULL),
(8, 'Introduction to Web Development', 3, 4, 5),
(9, 'Mathematics II', 3, 3, NULL),
(10, 'Computer Networks and Security', 4, 4, 6),
(11, 'Data Structures and Algorithms', 4, 4, 7),
(12, 'Software Technology', 5, 4, NULL),
(13, 'Operating Systems and Network Programming', 5, 4, NULL),
(14, 'Number Theory and Cryptography', 5, 4, NULL),
(15, 'Discrete Mathematics', 5, 3, 8),
(16, 'Advanced Programming', 6, 4, NULL),
(17, 'Advanced Databases', 6, 4, NULL),
(18, 'Dissertation', 6, 0, 8);

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `id` int(11) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `availability` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`id`, `firstname`, `lastname`, `availability`) VALUES
(2, 'Stavros', 'Papadakis', 7),
(3, 'Maria ', 'Kanaki', 8),
(4, 'Nikos', 'Pagonis', 10),
(5, 'Anna', 'Georgiou', 8),
(6, 'Sofia', 'Antoniou', 7),
(7, 'Kostas ', 'Nikolaou', 7),
(8, 'Ioannis', 'Pavlou', 7),
(10, 'Ειρήνη ', 'Λιατσου', 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `teacher_id` (`teacher_id`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
