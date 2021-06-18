-- create a function that returns the index of a random row
create or replace function random_index(account_id varchar)
   returns int
   language plpgsql
  as
$$
declare
rand int;
rows int;
begin
select count(*) into rows from accounts;
select round(random()*rows) into rand;
return rand;
end;
$$