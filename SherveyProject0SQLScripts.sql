create schema project1;

create table project1.clients (
	client_id serial primary key,
	username varchar(256) unique,
	password varchar(256),
	firstname varchar(256),
	lastname varchar(256),
	address varchar(512) 
);

CREATE TYPE status as ENUM('APPROVED', 'DENIED', 'PENDING', 'CANCELED');

create table project1.accounts (
account_id serial primary key,
owner_id integer references clients(client_id),
account_name varchar(256),
balance float,
current_status status
);

create table project1.co_owners (
	co_owner_id integer references clients(client_id),
	account_id integer references accounts(account_id)
);


CREATE TYPE transaction_type as ENUM('Withdrawl', 'Deposit', 'Transfer');

create table project1.transactions (
	transaction_id serial primary key,
	creator_id integer references clients(client_id),
	account_id integer references accounts(account_id),
	target_id integer references accounts(account_id),
	ammount float,
	t_type transaction_type
);