package com.fh.api.member.biz;

import com.fh.api.member.po.Member;
import com.fh.api.member.vo.MemberVo;

public interface IMemberService {
    void addMember(MemberVo memberVo);

    Member findMemberByName(String userName);

    String findMenberByPhone(String phone);
}
