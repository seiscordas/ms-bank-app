INSERT INTO tb_customer (name, email, mobile_number, create_dt) VALUES ('KL Sites', 'teste@klsites.com.br', '999999999', CURDATE());
 
INSERT INTO tb_account (customer_id, account_number, account_type, branch_address, create_dt) VALUES (1, 186576453, 'Savings', '123 Benedito J. dos Reis, Apucarana', CURDATE());
 
