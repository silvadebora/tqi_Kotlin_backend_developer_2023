CREATE TABLE category (
  id BIGINT AUTO_INCREMENT NOT NULL,
   `description` VARCHAR(255) NOT NULL,
   CONSTRAINT pk_category PRIMARY KEY (id)
);

ALTER TABLE category ADD CONSTRAINT uc_category_description UNIQUE (`description`);

CREATE TABLE product (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NOT NULL,
   price DECIMAL NOT NULL,
   stock_level INT NULL,
   category_id BIGINT NOT NULL,
   measurement VARCHAR(255) NOT NULL,
   bar_code VARCHAR(255) NOT NULL,
   image VARCHAR(255) NULL,
   CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product ADD CONSTRAINT uc_product_name UNIQUE (name);

ALTER TABLE product ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

CREATE TABLE shopping_cart (
  id BIGINT AUTO_INCREMENT NOT NULL,
   cpf VARCHAR(255) NULL,
   timestamp datetime NOT NULL,
   CONSTRAINT pk_shopping_cart PRIMARY KEY (id)
);

CREATE TABLE shopping_cart_item (
  id BIGINT AUTO_INCREMENT NOT NULL,
   product_id BIGINT NULL,
   quantity INT NOT NULL,
   shopping_cart_id BIGINT NULL,
   CONSTRAINT pk_shopping_cart_item PRIMARY KEY (id)
);

ALTER TABLE shopping_cart_item ADD CONSTRAINT FK_SHOPPING_CART_ITEM_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE shopping_cart_item ADD CONSTRAINT FK_SHOPPING_CART_ITEM_ON_SHOPPINGCART FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart (id);

CREATE TABLE transaction (
  id BIGINT AUTO_INCREMENT NOT NULL,
   shopping_cart_id BIGINT NULL,
   status INT NULL,
   payment_method INT NULL,
   amount DECIMAL NOT NULL,
   CONSTRAINT pk_transaction PRIMARY KEY (id)
);

ALTER TABLE transaction ADD CONSTRAINT FK_TRANSACTION_ON_SHOPPINGCART FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart (id);