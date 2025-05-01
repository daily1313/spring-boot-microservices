create table article_like_count (
    article_id bigint not null primary key,
    like_count bigint not null,
    version bigint not null
);

