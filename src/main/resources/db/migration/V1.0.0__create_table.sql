create table if not exists accounts (
    id int,
    account_no varchar(16),
    country varchar(2),
    currency varchar(3),
    branch_code varchar(8),
    status varchar(10),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    delay float
    );
