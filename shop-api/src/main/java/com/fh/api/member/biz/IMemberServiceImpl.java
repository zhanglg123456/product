package com.fh.api.member.biz;

import com.fh.api.member.mapper.IMemberMapper;
import com.fh.api.member.po.Member;
import com.fh.api.member.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("memberService")
public class IMemberServiceImpl implements IMemberService {

    @Autowired
    private IMemberMapper memberMapper;

    @Override
    public void addMember(MemberVo memberVo) {

        Member member=new Member();
        member.setBirthday(memberVo.getBirthday());
        member.setPhone(memberVo.getPhone());
        member.setEmail(memberVo.getEmail());
        member.setUserName(memberVo.getUserName());
        member.setUserPwd(memberVo.getUserPwd());
        member.setRegTime(new Date());
        memberMapper.addMember(member);
    }

    @Override
    public Member findMemberByName(String userName) {
        return memberMapper.findMemberByName(userName);
    }

    @Override
    public String findMenberByPhone(String phone) {
        return memberMapper.findMenberByPhone(phone);
    }
}
