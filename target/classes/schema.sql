-- VVT Computer Shop Seed SQL (Lite Extended)
-- Generated at 2025-09-10T17:13:36.907235Z

CREATE DATABASE IF NOT EXISTS vvt_shop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE vvt_shop;

CREATE TABLE IF NOT EXISTS categories (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(120) NOT NULL,
  slug VARCHAR(150) UNIQUE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS products (
  id INT PRIMARY KEY AUTO_INCREMENT,
  category_id INT NOT NULL,
  name VARCHAR(200) NOT NULL,
  slug VARCHAR(220) UNIQUE NOT NULL,
  description TEXT,
  price DECIMAL(12,2) NOT NULL,
  stock INT NOT NULL DEFAULT 0,
  image_url VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(150) NOT NULL,
  email VARCHAR(180) UNIQUE NOT NULL,
  password_hash VARCHAR(100) NOT NULL,
  role ENUM('USER','ADMIN') NOT NULL DEFAULT 'USER',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS orders (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  total DECIMAL(12,2) NOT NULL,
  status ENUM('NEW','PAID','SHIPPED','CANCELLED') DEFAULT 'NEW',
  shipping_name VARCHAR(150) NOT NULL,
  shipping_phone VARCHAR(40) NOT NULL,
  shipping_address VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS order_items (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  unit_price DECIMAL(12,2) NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);

DELETE FROM order_items; DELETE FROM orders; DELETE FROM products; DELETE FROM categories;

INSERT INTO categories (id, name, slug) VALUES
(1, 'CPU', 'cpu'),
(2, 'Mainboard', 'mainboard'),
(3, 'RAM', 'ram'),
(4, 'SSD', 'ssd'),
(5, 'GPU', 'gpu'),
(6, 'PSU', 'psu'),
(7, 'Case', 'case'),
(8, 'Cooler', 'cooler'),
(9, 'Monitor', 'monitor'),
(10, 'Peripheral', 'peripheral')
ON DUPLICATE KEY UPDATE name=VALUES(name), slug=VALUES(slug);

INSERT INTO products (category_id, name, slug, description, price, stock, image_url) VALUES
(1, 'Intel Core i5-12400F', 'intel-core-i5-12400f', 'CPU 6C/12T, 4.4GHz.', 3890000.00, 25, 'assets/img/intel-core-i5-12400f.png'),
(1, 'AMD Ryzen 5 5600', 'amd-ryzen-5-5600', 'CPU 6C/12T Zen3.', 2990000.00, 30, 'assets/img/amd-ryzen-5-5600.png'),
(1, 'Intel Core i7-13700K', 'intel-core-i7-13700k', 'CPU 16C/24T, OC.', 9990000.00, 15, 'assets/img/intel-core-i7-13700k.png'),
(2, 'ASUS TUF B660M-PLUS', 'asus-tuf-b660m-plus', 'mATX Intel Gen12/13.', 3190000.00, 20, 'assets/img/asus-tuf-b660m-plus.png'),
(2, 'MSI B550 Tomahawk', 'msi-b550-tomahawk', 'ATX AMD Ryzen.', 3590000.00, 18, 'assets/img/msi-b550-tomahawk.png'),
(3, 'Corsair Vengeance 16GB DDR5-5600', 'corsair-vengeance-16gb-ddr5-5600', 'Kit 16GB 5600MHz.', 1690000.00, 50, 'assets/img/corsair-vengeance-16gb-ddr5-5600.png'),
(3, 'G.Skill Trident Z5 32GB DDR5-6000', 'gskill-tridentz5-32gb-ddr5-6000', 'Kit 32GB 6000MHz.', 2390000.00, 35, 'assets/img/gskill-tridentz5-32gb-ddr5-6000.png'),
(4, 'Samsung 980 PRO 1TB', 'samsung-980-pro-1tb', 'NVMe PCIe 4.0.', 2490000.00, 40, 'assets/img/samsung-980-pro-1tb.png'),
(4, 'WD Black SN850X 1TB', 'wd-black-sn850x-1tb', 'Gaming PCIe 4.0.', 2690000.00, 32, 'assets/img/wd-black-sn850x-1tb.png'),
(5, 'RTX 4060 Ti 8GB', 'nvidia-rtx-4060-ti-8gb', 'DLSS 3, 1080p/1440p.', 8990000.00, 14, 'assets/img/nvidia-rtx-4060-ti-8gb.png'),
(5, 'RTX 4070 SUPER 12GB', 'nvidia-rtx-4070-super-12gb', '1440p/4K nhẹ.', 14990000.00, 10, 'assets/img/nvidia-rtx-4070-super-12gb.png'),
(6, 'Corsair RM750x 750W', 'corsair-rm750x-750w', '80+ Gold.', 2390000.00, 22, 'assets/img/corsair-rm750x-750w.png'),
(7, 'Lian Li Lancool 215', 'lianli-lancool-215', 'Airflow tốt.', 1490000.00, 28, 'assets/img/lianli-lancool-215.png'),
(8, 'DeepCool AK400', 'deepcool-ak400', 'Tản khí êm.', 790000.00, 45, 'assets/img/deepcool-ak400.png'),
(9, 'LG 27GN850-B 27" 144Hz', 'lg-27gn850-b', 'Nano IPS 1440p.', 7990000.00, 12, 'assets/img/lg-27gn850-b.png'),
(10, 'Keychron K2 V2', 'keychron-k2-v2', 'Phím 75% BT/USB-C.', 1890000.00, 33, 'assets/img/keychron-k2-v2.png'),
(10, 'Logitech G502 HERO', 'logitech-g502-hero', 'HERO 25K.', 990000.00, 40, 'assets/img/logitech-g502-hero.png');
