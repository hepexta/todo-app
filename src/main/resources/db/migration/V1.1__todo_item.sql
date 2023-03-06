create type todo_status as enum ('NEW', 'ACTIVE', 'DONE', 'REMOVED');

create table todo_item ( 
	todo_item_id uuid not null default uuid_generate_v4(),
	todo_item_label text not null,
	todo_item_description text,
	todo_category_id uuid not null,
	status todo_status not null,
	created_by text not null,
	created_at timestamptz not null default now(),
	updated_by text not null,
	updated_at timestamptz not null default now(),
	constraint pk_todo_item_id primary key (todo_item_id),
	constraint fk_todo_item_category foreign key (todo_category_id) references todo_category
 );

alter table todo_item add constraint check_todo_item_label check ( ( length(todo_item_label) <= 100) );

COMMENT ON TABLE todo_item IS 'Todo Item';
COMMENT ON COLUMN todo_item.todo_item_id IS 'Todo Item Identifier';
COMMENT ON COLUMN todo_item.todo_item_label IS 'Todo Item Name';
COMMENT ON COLUMN todo_item.todo_item_description IS 'Todo Item Description';
COMMENT ON COLUMN todo_item.todo_category_id IS 'Category Identifier';
COMMENT ON COLUMN todo_item.status IS 'Status';
COMMENT ON COLUMN todo_item.created_by IS 'Who created the record (if available)';
COMMENT ON COLUMN todo_item.created_at IS 'When the record was created';
COMMENT ON COLUMN todo_item.updated_by IS 'Who updated the record (if available)';
COMMENT ON COLUMN todo_item.updated_at IS 'When the record was updated';

create unique index idx_todo_item_todo_item_label on todo_item (lower(todo_item_label));
create index idx_todo_item_created_at on todo_item (created_at desc);