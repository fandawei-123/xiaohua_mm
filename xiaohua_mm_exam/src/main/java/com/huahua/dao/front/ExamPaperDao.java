package com.huahua.dao.front;

import com.huahua.domain.front.ExamPaper;
import org.apache.ibatis.annotations.Insert;

import javax.annotation.Resource;

/**
 * @author Huahua
 */
@Resource
public interface ExamPaperDao {

    @Insert("insert into tr_examination_paper (id, member_id, state, apply_time)\n" +
            "values (#{id}, #{memberId}, #{state}, #{applyTime})")
    int save(ExamPaper examPaper);
}
