package com.huahua.service.front.impl;


import com.huahua.dao.front.MemberDao;
import com.huahua.domain.front.Member;
import com.huahua.service.front.MemberService;
import com.huahua.utils.JedisUtils;
import com.huahua.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author Huahua
 */
@Component("memberService")
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberDao memberDao;
    private Jedis jedis;


    @Override
    public boolean register(Member member) {
        String uuid = UUID.randomUUID().toString();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < uuid.length() && id.length() < 10; i++) {
            char ch = uuid.charAt(i);
            if (ch > 47 && ch < 58) {
                id.append(ch);
            }
        }
        member.setId("M" + id);
        Date date = new Date();
        member.setRegisterDate(date);
        member.setState("1");
        member.setPassword(MD5Util.code(member.getPassword()));
        int save = memberDao.save(member);
        return save > 0;
    }

    @Override
    public Member login(String email, String password) {
        password = MD5Util.code(password);
        Member member = memberDao.findByEmailAndPwd(email, password);
        //将登陆人信息缓存到redis中
        jedis = JedisUtils.getResource();
        jedis.setex(member.getId(),3600,member.getNickName());
        jedis.close();


        return member;
    }

    @Override
    public String getLoginInfo(String id) {
        jedis = JedisUtils.getResource();
        String nickName = jedis.get(id);
        jedis.close();
        return nickName;
    }

    @Override
    public boolean logout(String id) {
        Long row = jedis.del(id);
        jedis.close();
        return row > 0;
    }
}
