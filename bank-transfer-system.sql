-- public.accounts definition

-- Drop table

-- DROP TABLE accounts;

CREATE TABLE accounts (
	account_number varchar(20) NOT NULL,
	user_id int4 NOT NULL,
	account_type varchar(50) NOT NULL,
	balance numeric(15, 2) NOT NULL DEFAULT 0,
	open_date date NOT NULL,
	CONSTRAINT accounts_pkey PRIMARY KEY (account_number)
);


-- public.accounts foreign keys

ALTER TABLE public.accounts ADD CONSTRAINT accounts_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(user_id);

INSERT INTO accounts
(account_number, user_id, account_type, balance, open_date)
VALUES('9876543210', 2, 'Checking', 500.00, '2024-06-16');
INSERT INTO accounts
(account_number, user_id, account_type, balance, open_date)
VALUES('6543219870', 1, 'Investment', 20000.00, '2024-06-18');
INSERT INTO accounts
(account_number, user_id, account_type, balance, open_date)
VALUES('7891234560', 4, 'Checking', 3000.00, '2024-06-19');
INSERT INTO accounts
(account_number, user_id, account_type, balance, open_date)
VALUES('3219876540', 2, 'Savings', 2500.00, '2024-06-20');
INSERT INTO accounts
(account_number, user_id, account_type, balance, open_date)
VALUES('6789123450', 3, 'Investment', 100000.00, '2024-06-22');
INSERT INTO accounts
(account_number, user_id, account_type, balance, open_date)
VALUES('3456789120', 4, 'Savings', 5000.00, '2024-06-23');
INSERT INTO accounts
(account_number, user_id, account_type, balance, open_date)
VALUES('2345678910', 5, 'Savings', 10000000.00, '2024-06-24');
INSERT INTO accounts
(account_number, user_id, account_type, balance, open_date)
VALUES('4567891230', 3, 'Savings', 10000000.00, '2024-06-17');
INSERT INTO accounts
(account_number, user_id, account_type, balance, open_date)
VALUES('1234567890', 1, 'Savings', 9600000.00, '2024-06-15');
INSERT INTO accounts
(account_number, user_id, account_type, balance, open_date)
VALUES('5678912340', 5, 'Checking', 150000.00, '2024-06-21');


-- public.bank_details definition

-- Drop table

-- DROP TABLE bank_details;

CREATE TABLE bank_details (
	id serial4 NOT NULL,
	account_number varchar(20) NULL,
	bank_code varchar(5) NOT NULL,
	bank_name varchar(100) NOT NULL,
	account_holder_name varchar(100) NOT NULL,
	account_holder_address varchar(255) NULL,
	CONSTRAINT bank_details_pkey PRIMARY KEY (id)
);


-- public.bank_details foreign keys

ALTER TABLE public.bank_details ADD CONSTRAINT bank_details_account_number_fkey FOREIGN KEY (account_number) REFERENCES accounts(account_number);

INSERT INTO bank_details
(account_number, bank_code, bank_name, account_holder_name, account_holder_address)
VALUES('1234567890', '123', 'Bank Mandiri', 'John Doe', 'Bintaro');
INSERT INTO bank_details
(account_number, bank_code, bank_name, account_holder_name, account_holder_address)
VALUES('9876543210', '987', 'Bank Central Asia (BCA)', 'Jane Smith', 'Kuningan');
INSERT INTO bank_details
(account_number, bank_code, bank_name, account_holder_name, account_holder_address)
VALUES('4567891230', '456', 'Bank Rakyat Indonesia (BRI)', 'Mike Jones', 'Pluit');
INSERT INTO bank_details
(account_number, bank_code, bank_name, account_holder_name, account_holder_address)
VALUES('6543219870', '654', 'Bank Negara Indonesia (BNI)', 'Sara Wilson', 'Tebet');
INSERT INTO bank_details
(account_number, bank_code, bank_name, account_holder_name, account_holder_address)
VALUES('7891234560', '789', 'Bank Tabungan Negara (BTN)', 'Chris Brown', 'Medan');
INSERT INTO bank_details
(account_number, bank_code, bank_name, account_holder_name, account_holder_address)
VALUES('3219876540', '321', 'Bank CIMB Niaga', 'Emily Taylor', 'Palembang');
INSERT INTO bank_details
(account_number, bank_code, bank_name, account_holder_name, account_holder_address)
VALUES('5678912340', '567', 'Bank Danamon', 'Alex Jackson', 'Lampung');
INSERT INTO bank_details
(account_number, bank_code, bank_name, account_holder_name, account_holder_address)
VALUES('6789123450', '678', 'Bank Permata', 'Olivia Miller', 'Sudirman');
INSERT INTO bank_details
(account_number, bank_code, bank_name, account_holder_name, account_holder_address)
VALUES('3456789120', '345', 'Bank BTPN', 'Ryan Thomas', 'Karet Kuningan');
INSERT INTO bank_details
(account_number, bank_code, bank_name, account_holder_name, account_holder_address)
VALUES('2345678910', '234', 'Bank OCBC NISP', 'Ashley White', 'Gatot Subroto');

-- public.transactions definition

-- Drop table

-- DROP TABLE transactions;

CREATE TABLE transactions (
	transaction_id varchar(50) NOT NULL,
	account_number varchar(20) NULL,
	transaction_type varchar(50) NOT NULL,
	amount numeric(15, 2) NOT NULL,
	transaction_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	status varchar(25) NULL,
	account_number_destination varchar(25) NULL,
	CONSTRAINT transactions_pkey PRIMARY KEY (transaction_id)
);


-- public.transactions foreign keys

ALTER TABLE public.transactions ADD CONSTRAINT transactions_account_number_fkey FOREIGN KEY (account_number) REFERENCES accounts(account_number);


INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('9876543210', 'Withdrawal', 50.00, '2024-06-16 10:00:00.000', 'Success', '7891234560');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('4567891230', 'Deposit', 200.00, '2024-06-17 11:00:00.000', 'Success', '6789123450');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('6543219870', 'Withdrawal', 300.00, '2024-06-18 12:00:00.000', 'Success', '4567891230');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('7891234560', 'Deposit', 400.00, '2024-06-19 13:00:00.000', 'Success', '6789123450');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('3219876540', 'Withdrawal', 100.00, '2024-06-20 14:00:00.000', 'Success', '4567891230');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('5678912340', 'Deposit', 150.00, '2024-06-21 15:00:00.000', 'Failed', '7891234560');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('6789123450', 'Withdrawal', 200.00, '2024-06-22 16:00:00.000', 'Success', '4567891230');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('3456789120', 'Deposit', 250.00, '2024-06-23 17:00:00.000', 'Success', '7891234560');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('2345678910', 'Withdrawal', 50.00, '2024-06-24 18:00:00.000', 'Failed', '4567891230');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('1234567890', 'Deposit', 100.00, '2024-06-15 09:00:00.000', 'Failed', '4567891230');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('1234567890', 'CASH_TRANSFER', 50000.00, '2024-06-13 14:51:48.366', 'Success', '2345678910');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('1234567890', 'CASH_TRANSFER', 50000.00, '2024-06-13 14:53:24.902', 'Success', '2345678910');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('1234567890', 'CASH_TRANSFER', 50000.00, '2024-06-13 14:55:35.122', 'Success', '2345678910');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('1234567890', 'CASH_TRANSFER', 50000.00, '2024-06-13 14:56:33.706', 'Success', '2345678910');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('1234567890', 'CASH_TRANSFER', 50000.00, '2024-06-13 15:05:31.044', 'Success', '5678912340');
INSERT INTO transactions
(account_number, transaction_type, amount, transaction_date, status, account_number_destination)
VALUES('1234567890', 'CASH_TRANSFER', 50000.00, '2024-06-13 15:11:11.709', 'Success', '5678912340');

-- public.users definition

-- Drop table

-- DROP TABLE users;

CREATE TABLE users (
	user_id serial4 NOT NULL,
	username varchar(50) NOT NULL,
	"password" varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	full_name varchar(100) NOT NULL,
	date_of_birth date NULL,
	address varchar(255) NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT users_email_key UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (user_id),
	CONSTRAINT users_username_key UNIQUE (username)
);

INSERT INTO users
(username, "password", email, full_name, date_of_birth, address, created_at, updated_at)
VALUES('john_doe', 'password123', 'john@example.com', 'John Doe', '1990-01-01', 'Bintaro', '2024-06-11 20:43:28.275', '2024-06-11 20:43:28.275');
INSERT INTO users
(username, "password", email, full_name, date_of_birth, address, created_at, updated_at)
VALUES('jane_smith', 'pass456', 'jane@example.com', 'Jane Smith', '1995-05-15', 'Kuningan', '2024-06-11 20:43:28.275', '2024-06-11 20:43:28.275');
INSERT INTO users
(username, "password", email, full_name, date_of_birth, address, created_at, updated_at)
VALUES('mike_jones', 'qwerty', 'mike@example.com', 'Mike Jones', '1988-09-20', 'Pluit', '2024-06-11 20:43:28.275', '2024-06-11 20:43:28.275');
INSERT INTO users
(username, "password", email, full_name, date_of_birth, address, created_at, updated_at)
VALUES('sara_wilson', 'abc123', 'sara@example.com', 'Sara Wilson', '1987-03-10', 'Tebet', '2024-06-11 20:43:28.275', '2024-06-11 20:43:28.275');
INSERT INTO users
(username, "password", email, full_name, date_of_birth, address, created_at, updated_at)
VALUES('chris_brown', 'password', 'chris@example.com', 'Chris Brown', '1992-11-28', 'Medan', '2024-06-11 20:43:28.275', '2024-06-11 20:43:28.275');
INSERT INTO users
(username, "password", email, full_name, date_of_birth, address, created_at, updated_at)
VALUES('emily_taylor', 'letmein', 'emily@example.com', 'Emily Taylor', '1998-07-03', 'Palembang', '2024-06-11 20:43:28.275', '2024-06-11 20:43:28.275');

