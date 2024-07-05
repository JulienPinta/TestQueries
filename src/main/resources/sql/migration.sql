create table `group`
(
    id   int
        primary key,
    name VARCHAR(1000) null
);
create table `user`
(
    id       int
        primary key,
    name     VARCHAR(1000) null,
    group_id int           null,
    constraint user_group_key
        foreign key (group_id) references `group` (id)
);
create table `country`
(
    id   int
        primary key,
    name VARCHAR(1000) null
);
create table `city`
(
    id         int
        primary key,
    name       VARCHAR(1000) null,
    country_id int           null,
    constraint city_countryy_key
        foreign key (country_id) references `country` (id)
);
create table `room`
(
    id   int
        primary key,
    name VARCHAR(1000) null
);
create table `chair`
(
    id         int
        primary key,
    name       VARCHAR(1000) null,
    room_id int           null,
    constraint chair_room_key
        foreign key (room_id) references `room` (id)
);