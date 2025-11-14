-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 13, 2025 at 08:39 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbstore`
--
CREATE DATABASE IF NOT EXISTS `dbstore` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `dbstore`;

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `created_at`, `user_id`) VALUES
(1, '2025-11-12 16:20:06.000000', 1),
(2, '2025-11-12 17:11:14.000000', 2);

-- --------------------------------------------------------

--
-- Table structure for table `cart_item`
--

CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `cart_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart_item`
--

INSERT INTO `cart_item` (`id`, `quantity`, `cart_id`, `product_id`) VALUES
(22, 2, 2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `parent_id`) VALUES
(1, 'Áo', NULL),
(2, 'Quần', NULL),
(3, 'Balo', NULL),
(4, 'Nón', NULL),
(5, 'Áo Thun', 1),
(6, 'Áo Sơ Mi', 1),
(7, 'Áo Polo', 1),
(8, 'Áo Khoác', 1),
(9, 'Áo Nỉ', 1),
(10, 'Quần Jeans', 2),
(11, 'Quần Short', 2),
(12, 'Quần Kaki', 2),
(13, 'Quần Jogger', 2),
(14, 'Quần Tây', 2),
(15, 'Balo Thời Trang', 3),
(16, 'Túi Đeo chéo', 3),
(17, 'Nón Lưỡi Trai', 4),
(18, 'Nón Bucket', 4);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `shipping_address` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `customer_name`, `order_date`, `shipping_address`, `user_id`, `status`) VALUES
(1, 'admin', '2025-11-12 09:32:02.000000', '14 doãn uẩn khuê mỹ đà nẵng', NULL, NULL),
(10, 'user', '2025-11-13 06:02:10.000000', '14 doãn uẩn khuê mỹ đà nẵng', 2, NULL),
(11, 'user', '2025-11-13 07:54:22.000000', '14 doãn uẩn khuê mỹ đà nẵng', 2, NULL),
(12, 'user', '2025-11-13 08:20:38.000000', '14 doãn uẩn khuê mỹ đà nẵng', 2, NULL),
(13, 'user', '2025-11-13 09:10:14.000000', 'đăk nông', 2, NULL),
(14, 'user', '2025-11-13 09:13:22.000000', 'dak nong', 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` (`id`, `price`, `quantity`, `order_id`, `product_id`) VALUES
(1, 200000, 5, 1, 3),
(15, 280000, 1, 10, 2),
(16, 280000, 1, 11, 2),
(17, 290000, 2, 12, 4),
(18, 199000, 2, 13, 1),
(19, 290000, 1, 14, 4);

-- --------------------------------------------------------

--
-- Table structure for table `password_reset_token`
--

CREATE TABLE `password_reset_token` (
  `id` bigint(20) NOT NULL,
  `expiry_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `password_reset_token`
--

INSERT INTO `password_reset_token` (`id`, `expiry_date`, `token`, `user_id`) VALUES
(1, '2025-11-13 14:22:39.000000', 'b35ee276-6d50-42ab-82be-94953ffe71f3', 1);

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `id` bigint(20) NOT NULL,
  `amount` double DEFAULT NULL,
  `payment_date` datetime(6) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `description_product` varchar(255) DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  `name_product` varchar(255) DEFAULT NULL,
  `price_product` double DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `brand`, `description_product`, `images`, `name_product`, `price_product`, `quantity`, `category_id`) VALUES
(1, 'ICONDENIM', 'Áo Thun Họa Tiết', 'https://cdn.hstatic.net/products/1000360022/untitled-1_e2951e1705f74be29097c62a33a628c6_1024x1024.jpg', 'Áo Thun Họa Tiết', 199000, 50, 5),
(2, 'ICONDENIM', 'Áo Sơ Mi Stripped', 'https://cdn.hstatic.net/products/1000360022/untitled-1_d31ecd9224e844fa8fa3bddc9db3a433_1024x1024.jpg', 'Áo Sơ Mi Stripped', 280000, 30, 6),
(3, 'ICONDENIM', 'Áo Polo Heroic', 'https://cdn.hstatic.net/products/1000360022/ao-polo-nam-cotton-panelx-form-regular_05744b5c4d774613b4107e9c949f8e38_1024x1024.jpg', 'Áo Polo Finest', 200000, 50, 7),
(4, 'ICONDENIM', 'Áo Khoác Denim', 'https://cdn.hstatic.net/products/1000360022/ao-khoac-nam-hoodie-zip-orgnls-form-regular52_c046178e4dad4ea291b063546f20ab7a_1024x1024.jpg', 'Áo Khoác Denim', 290000, 35, 8),
(5, 'ICONDENIM', 'Áo Sweatshirt', 'https://cdn.hstatic.net/products/1000360022/untitled-2_2fd0daf2d5cc40a896c2628cec1376a9_1024x1024.jpg', 'Áo Sweatshirt', 299000, 50, 9),
(6, 'ICONDENIM', 'Quần Jean Edge', 'https://cdn.hstatic.net/products/1000360022/ean-nam-sieu-nhe-ong-suong-icon105-lightweight-off-white-form-straight_4b4ae3696c864b3387d470322d5a75b6_1024x1024.jpg', 'Quần Jean Nam Edge', 350000, 40, 10),
(7, 'ICONDENIM', 'Quần Short Field', 'https://cdn.hstatic.net/products/1000360022/quan-short-nam-denim-cotton-star-form-loose_1_8d9b9a2efa884eb781704866131924bc_1024x1024.jpg', 'Quần Short Field', 270000, 50, 11),
(8, 'ICONDENIM', 'Quần Kaki Garment', 'https://cdn.hstatic.net/products/1000360022/video-quan-kaki-nam-ong-om-strecthband-form-slim_a184ecfb21374784a37f3648ac367725_1024x1024.jpg', 'Quần Kaki Garment', 230000, 30, 12),
(9, 'ICONDENIM', 'Quần Jogger Dashfield', 'https://cdn.hstatic.net/products/1000360022/c_-_q_a4d21bb6c5cf4740a39390aab53897cb_1024x1024.jpg', 'Quần Jogger Dashfield', 370000, 40, 13),
(10, 'ICONDENIM', 'Quần Tây Hidden', 'https://cdn.hstatic.net/products/1000360022/quan-tay-nam-urban-trousers-form-slim-crop_3ac14417afac40c1875cdd6589b3e295_1024x1024.jpg', 'Quần Tây Hidden', 250000, 20, 14),
(11, 'ICONDENIM', 'Balo Unisex', 'https://www.dimteam.vn/cdn/shop/files/Den-1_6b075b2b-dcd9-4f25-80ad-c268cff30d54_950x.jpg?v=1751438286', 'Balo Unisex', 450000, 30, 15),
(12, 'ICONDENIM', 'Túi Tote Canvas', 'https://cdn.hstatic.net/products/1000360022/ct-tui_60734ecc68f542a5b070a4d6b072b101_1024x1024.jpg', 'Túi Tote Canvas', 490000, 30, 16),
(13, 'ICONDENIM', 'Nón Lưỡi Trai Retro', 'https://cdn.hstatic.net/products/1000360022/img_0582_baa76c776a794140a016d03c1986ca3d_1024x1024.jpg', 'Nón Lưỡi Trai Retro', 230000, 30, 17),
(14, 'ICONDENIM', 'Mũ Bucket Denim', 'https://product.hstatic.net/1000360022/product/id-2534_9fee0a5e34d84497ab923b492c87061a_1024x1024.jpg', 'Mũ Bucket Denim', 250000, 30, 18);

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `rating` int(11) NOT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`id`, `comment`, `created_at`, `rating`, `product_id`) VALUES
(1, 'tốt', '2025-11-12 09:32:55.000000', 5, 1),
(2, 'chất lượng', '2025-11-13 09:09:17.000000', 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('ROLE_ADMIN','ROLE_USER') DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `role`, `username`) VALUES
(1, 'admin@gmail.com', '$2a$10$Ty90lfwz8rbLrsEpHIe2WujfYN8ArmgI4NGKRJAEy6AhU0G1V2njO', 'ROLE_ADMIN', 'admin'),
(2, 'user@gmail.com', '$2a$10$ea.HzZ.8sLtFK4sJWJFsiuBY6qdPQB25cH3FP.RXk9vnedzMnD5zC', 'ROLE_USER', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK9emlp6m95v5er2bcqkjsw48he` (`user_id`);

--
-- Indexes for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  ADD KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2y94svpmqttx80mshyny85wqr` (`parent_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  ADD KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`);

--
-- Indexes for table `password_reset_token`
--
ALTER TABLE `password_reset_token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5lwtbncug84d4ero33v3cfxvl` (`user_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK8vo36cen604as7etdfwmyjsxt` (`order_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9yqmlf28ges8c30nj4v4hva7t` (`product_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cart_item`
--
ALTER TABLE `cart_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `order_item`
--
ALTER TABLE `order_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `password_reset_token`
--
ALTER TABLE `password_reset_token`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  ADD CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FK2y94svpmqttx80mshyny85wqr` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `password_reset_token`
--
ALTER TABLE `password_reset_token`
  ADD CONSTRAINT `FK5lwtbncug84d4ero33v3cfxvl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `FK81gagumt0r8y3rmudcgpbk42l` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `FK9yqmlf28ges8c30nj4v4hva7t` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
