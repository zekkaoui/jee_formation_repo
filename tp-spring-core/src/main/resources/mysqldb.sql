create table FORMATION (
        id bigint not null auto_increment,
        DATE_DEBUT datetime not null,
        PRIX integer not null,
        THEMATIQUE varchar(50) not null,
        TYPE_FORMATION varchar(255) not null,
        primary key (id)
    );

insert into FORMATION (id, DATE_DEBUT, PRIX, THEMATIQUE, TYPE_FORMATION) 
	   	values (1, CURRENT_TIMESTAMP, 4000, 'Spring CORE', 'en ligne');
insert into FORMATION (id, DATE_DEBUT, PRIX, THEMATIQUE, TYPE_FORMATION) 
		values (2, CURRENT_TIMESTAMP, 10000, 'Spring MVC', 'en classe');
insert into FORMATION (id, DATE_DEBUT, PRIX, THEMATIQUE, TYPE_FORMATION) 
	   	values (3, CURRENT_TIMESTAMP, 4000, 'Spring Security', 'en ligne');
insert into FORMATION (id, DATE_DEBUT, PRIX, THEMATIQUE, TYPE_FORMATION) 
		values (4, CURRENT_TIMESTAMP, 10000, 'Spring REST', 'en classe');

