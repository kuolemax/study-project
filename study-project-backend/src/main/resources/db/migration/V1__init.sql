create schema if not exists user_center;

create or replace function cs_timestamp() returns trigger as
$$
begin
    new.update_at= current_timestamp;
    return new;
end
$$
    language plpgsql;
-- create trigger cs_name before update on xxx for each row execute procedure cs_timestamp();