-- board 테이블 만들기

create table board(
	board_no number(5) primary key,		--게시판 글번호
	board_writer varchar2(30) not null,	--게시판 글 작성자
	board_title varchar2(200) not null,	--게시판 글제목
	board_cont varchar2(1000),			--게시판 글내용
	board_pwd varchar2(30) not null,	--게시판 글 비밀번호
	board_hit number(5) default 0,		--게시판 글 조회수
	board_date date,					--게시판 글 작성일
	board_update date					--게시판 글 수정일
);


-- board 테이블에 게시글을 추가해 보자
insert into board values (1, '김둥둥', '제목1', '둥둥이 글입니다.', '1234', default, sysdate, '');
insert into board values (2, '최길동', '제목2', '길동이 글입니다.', '1111', default, sysdate, '');
insert into board values (3, '김네모', '제목3', '네모의 글입니다.', '2222', default, sysdate, '');