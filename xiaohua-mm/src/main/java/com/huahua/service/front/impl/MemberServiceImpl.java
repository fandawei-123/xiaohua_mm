package com.huahua.service.front.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huahua.dao.front.MemberDao;
import com.huahua.domain.front.Member;
import com.huahua.domain.system.User;
import com.huahua.service.front.MemberService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("memberService")
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberDao memberDao;

    @Override
    public void delete(Member member) {
        memberDao.delete(member);
    }

    @Override
    public Member findById(String id) {
        return memberDao.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return memberDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<Member> all = memberDao.findAll();
        return new PageInfo(all);
    }

    @Override
    public void updateState(String memberId, String state) {
        memberDao.updateState(memberId, state);
    }
}
