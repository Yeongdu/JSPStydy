-- tbl_reply 테이블 생성
create table tbl_reply(
	rno number(5) primary key,		--답변 글 번호
	bno number(5) not null,			--원글 글 번호
	rewriter varchar2(30) not null,	--답변 글 작성자
	recont varchar2(500) not null, --답변 글 내용
	redate date,					--답변 글 작성일
	reupdate date					--답변 글 수정일
);

alter table tbl_reply add constraint fk_reply_board foreign key (bno) references tbl_board(bno);