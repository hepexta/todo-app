CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table todo_category ( 
	todo_category_id uuid not null default uuid_generate_v4(),
	todo_category_name text not null,
	todo_category_description text,
	created_by text not null,
	created_at timestamptz not null default now(),
	updated_by text not null,
	updated_at timestamptz not null default now(),
	constraint pk_todo_category_id primary key (todo_category_id)
 );

alter table todo_category add constraint check_todo_category_name check ( ( length(todo_category_name) <= 100) );

COMMENT ON TABLE todo_category IS 'Category';
COMMENT ON COLUMN todo_category.todo_category_id IS 'Category Identifier';
COMMENT ON COLUMN todo_category.todo_category_name IS 'Category Name';
COMMENT ON COLUMN todo_category.todo_category_description IS 'Category Description';
COMMENT ON COLUMN todo_category.created_by IS 'Who created the record (if available)';
COMMENT ON COLUMN todo_category.created_at IS 'When the record was created';
COMMENT ON COLUMN todo_category.updated_by IS 'Who updated the record (if available)';
COMMENT ON COLUMN todo_category.updated_at IS 'When the record was updated';

create unique index idx_todo_category_todo_category_name on todo_category (lower(todo_category_name));
create index idx_todo_category_created_at on todo_category (created_at desc);