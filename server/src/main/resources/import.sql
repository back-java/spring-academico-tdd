insert into autores (nome, descricao) values ('Sophia Angelides',null);
insert into autores (nome, descricao) values ('Mário Pedrosa',null);
insert into autores (nome, descricao) values ('Emílio F. Moran',null);
insert into autores (nome, descricao) values ('Jack R. Greene',null);
insert into autores (nome, descricao) values ('Antônio Chaves',null);

insert into livros (titulo, ano_lancamento, importado, prazo_entrega, preco) values ('BTCHEKHOV: Cartas para uma Poética','1995-12-29',true,296,43);
insert into livros (titulo, ano_lancamento, importado, prazo_entrega, preco) values ('MODERNOS - Textos Escolhidos 3', '2002-09-22',false,31,47);
insert into livros (titulo, ano_lancamento, importado, prazo_entrega, preco) values ('HUMANA: Uma Introdução à Antropologia', '1998-01-17',false,400,42);
insert into livros (titulo, ano_lancamento, importado, prazo_entrega, preco) values ('TRABALHO POLICIAL', '2000-08-25',false,265,26);
insert into livros (titulo, ano_lancamento, importado, prazo_entrega, preco) values ('ADOÇÃO INTERNACIONAL e o Tráfico de Crianças', '1998-11-16', false,49,51);

insert into autores_livros (cod_autor, cod_livro) values (1,1);
insert into autores_livros (cod_autor, cod_livro) values (2,2);
insert into autores_livros (cod_autor, cod_livro) values (3,3);
insert into autores_livros (cod_autor, cod_livro) values (4,4);
insert into autores_livros (cod_autor, cod_livro) values (5,5);
insert into autores_livros (cod_autor, cod_livro) values (5,4);
insert into autores_livros (cod_autor, cod_livro) values (5,3);
insert into autores_livros (cod_autor, cod_livro) values (3,5);
insert into autores_livros (cod_autor, cod_livro) values (1,2);
insert into autores_livros (cod_autor, cod_livro) values (2,5);
