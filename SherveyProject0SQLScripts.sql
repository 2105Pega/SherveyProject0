create table clients (
	client_id serial primary key,
	username varchar(256) unique,
	password varchar(256),
	firstname varchar(256),
	lastname varchar(256),
	address varchar(512) 
);

account_id serial primary key,
owner_id integer references clients(client_id),
account_name varchar(256),
balance float,
current_status status
);

create table co_owners (
	co_owner_id integer references clients(client_id),
	account_id integer references accounts(account_id)
);


CREATE TYPE transaction_type as ENUM('Withdrawl', 'Deposit', 'Transfer');
create table transactions (
	transaction_id serial primary key,
	creator_id integer references clients(client_id),
	account_id integer references accounts(account_id),
	target_id integer references accounts(account_id),
	ammount float,
	t_type transaction_type
);