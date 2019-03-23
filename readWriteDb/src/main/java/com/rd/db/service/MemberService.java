package com.rd.db.service;

import java.util.List;

import com.rd.db.bean.Member;

public interface MemberService {

	int insert(Member member);

	int save(Member member);

	List<Member> selectAll();

}
