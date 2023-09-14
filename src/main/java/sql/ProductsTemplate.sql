
--
-- Database: `scrapper`
--
drop database if exists scrapper;
create database if not exists scrapper;

use scrapper;


--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` varchar(250) NOT NULL,
  `Name` varchar(250) NOT NULL,
  `Brand` varchar(250) NOT NULL,
  `Tags` varchar(250) DEFAULT NULL,
  `Description` varchar(250) NOT NULL,
  `Category` varchar(255) NOT NULL,
  `MRP` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);
