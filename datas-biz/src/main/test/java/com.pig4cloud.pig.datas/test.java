package com.pig4cloud.pig.datas;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pig4cloud.pig.datas.Utils.DatasUtils;
import com.pig4cloud.pig.datas.entity.DataChinaCity;
import com.pig4cloud.pig.datas.mapper.DataChinaCityMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fanglong
 * @date 2022/7/3
 * @apiNote
 */
@SpringBootTest
public class test {

    @Autowired
    private DataChinaCityMapper dataChinaCityMapper;
    @Test
    void test(){
        String date = "2022-07-01";
        //根据时间查询疫情数据，如果日期参数为空，则是当前时间
        QueryWrapper<DataChinaCity> wrapper = new QueryWrapper<>();
        wrapper.select("IFNULL(SUM(confirmed_relative),0) as nativeAdd"//新增确诊
                ,"IFNULL(SUM(native_relative),0) as nativeAdd"//新增本土
                ,"IFNULL(SUM(asymptomatic_relative),0) as asymptomaticAdd"//新增无症状
                ,"IFNULL(SUM(cur_confirm),0) as curConfirm"//现有确诊
                ,"IFNULL(SUM(confirmed),0) as confirmed"//累计确诊
                ,"IFNULL(SUM(crued),0) as crued"//累计治愈
                ,"IFNULL(SUM(died),0) as died")//累计死亡
                .eq("pro","福建");
        if (CharSequenceUtil.hasEmpty(date)){
            wrapper.likeRight("create_time", DatasUtils.getDate(new Date()));
        } else {
            wrapper.likeRight("create_time",date);
        }

        List<Map<String, Object>> cityList = dataChinaCityMapper.selectMaps(wrapper);
        System.out.println(cityList);
    }
}