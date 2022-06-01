CREATE TABLE Account
(
    id_account bigint AUTO_INCREMENT,
    id NVARCHAR(20) NOT NULL,
    account_type NVARCHAR(10) NOT NULL,
    client_id NVARCHAR(20) not null,
    balance DOUBLE not null ,
    withdrawal_allowed BIT,
    CONSTRAINT PK_Account PRIMARY KEY  (id_account)
);
CREATE TABLE Transactions(
    id_transaction bigint AUTO_INCREMENT,
    id NVARCHAR(20) NOT NULL,
    transaction_type NVARCHAR(15) NOT NULL,
    client_id NVARCHAR(20) not null,
    amount DOUBLE not null,
    new_balance DOUBLE not null,
    CONSTRAINT PK_Transaction PRIMARY KEY  (id_transaction)
);
-- insert into Account values('CHECKING' , 1, '00100001', 100.00,true);