-- upload 테이블 생성

create table upload(
	upload_no number(5) primary key,		--자료실 글 번호
	upload_writer varchar2(30) not null,	--글 작성자
	upload_title varchar2(200) not null,	--제목
	upload_cont varchar2(1000) not null,	--내용
	upload_pwd varchar2(30) not null,		--비밀번호
	upload_file varchar2(1000),				--파일명
	upload_hit number(5) default 0,			--조회수
	upload_date date,						--작성일
	upload_update date						--수정일
);