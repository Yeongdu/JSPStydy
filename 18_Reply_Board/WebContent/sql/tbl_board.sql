-- tbl_board 게시판 테이블 생성
create table tbl_board(
	bno number(5) primary key,		--게시판 글 번호
	writer varchar2(30) not null,	--게시판 글 작성자
	title varchar2(100) not null,	--게시판 글 제목
	content varchar2(1000) not null,--게시판 글 내용
	pwd varchar2(30) not null,		--게시판 글 비밀번호
	regdate date,					--게시판 글 작성일
	regupdate date					--게시판 글 수정일
);

-- 게시판에 글을 추가해보자
insert into tbl_board values(1, '홍길동','제목1','내용1','1111', sysdate,'');
insert into tbl_board values(2, '이순신','장군 글','이순신 장군 글입니다','2222', sysdate,'');
insert into tbl_board values(3, '유관순','열사님 글','유관순 열사님 글','3333', sysdate,'');
insert into tbl_board values(4, '세종대왕','한글','한글창제한 왕입니다','4444', sysdate,'');
insert into tbl_board values(5, '김연아','연아님 글','내용5','5555', sysdate,'');