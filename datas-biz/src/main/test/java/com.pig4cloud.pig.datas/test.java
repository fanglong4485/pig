package com.pig4cloud.pig.datas;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.pig4cloud.pig.datas.service.DataChinaCityService;
import com.pig4cloud.pig.datas.utils.DatesUtil;
import com.pig4cloud.pig.datas.entity.DataChinaCity;
import com.pig4cloud.pig.datas.mapper.DataChinaCityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
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
	private DataChinaCityService dataChinaCityService;

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
            wrapper.likeRight("create_time", DatesUtil.getDate(new Date()));
        } else {
            wrapper.likeRight("create_time",date);
        }

        List<Map<String, Object>> cityList = dataChinaCityMapper.selectMaps(wrapper);
        System.out.println(cityList);
    }

    @Test
    void test2(){
        dataChinaCityMapper.insert(new DataChinaCity((long) 2,"test2"));
    }

    @Test
	void test3(){
		QueryWrapper<DataChinaCity> wrapper = new QueryWrapper<>();
		wrapper.select("IFNULL(SUM(confirmed_relative),0) as confirmedRelativeSum")
				.between("create_time","2022-03-01","2022-03-27")
				.orderByDesc("create_time");
		List<Map<String, Object>> maps = dataChinaCityMapper.selectMaps(wrapper);
		System.out.println(maps);
	}

	@Test
	void test4(){
		Map citiesTrend = dataChinaCityService.getCitiesTrend();
		System.out.println(citiesTrend);
	}

	@Test
	void test5(){
		Map<SFunction<DataChinaCity,?>, Object> map = new HashMap<>();
		map.put(DataChinaCity::getCity,"三沙");
		List<DataChinaCity> list = dataChinaCityService.list(Wrappers.<DataChinaCity>lambdaQuery().allEq(map));
		list.forEach(item->{
			System.out.println(item.getCity());
		});
	}
}
