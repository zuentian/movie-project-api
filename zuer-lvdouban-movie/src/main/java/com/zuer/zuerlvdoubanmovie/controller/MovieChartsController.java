package com.zuer.zuerlvdoubanmovie.controller;

import com.zuer.zuerlvdoubancommon.charts.BarMovieShowCount;
import com.zuer.zuerlvdoubancommon.charts.CalendarMovieShowCount;
import com.zuer.zuerlvdoubancommon.charts.PieMovieShowCount;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieChartsFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@EnableAutoConfiguration
@RequestMapping(value = "/MovieChartsController")
@RestController
@Transactional(rollbackFor = {Exception.class})
public class MovieChartsController {

    @Autowired
    private MovieChartsFeignService movieChartsFeignService;

    @RequestMapping(value = "/queryCalendarMovieShowCount",method = RequestMethod.POST)
    public List<Object[]>  queryCalendarMovieShowCount(@RequestParam Map<String,Object> param) {
        Calendar car = Calendar.getInstance();
        car.setTime(new Date());
        int nowYear=car.get(Calendar.YEAR);//当前年份

        String id =(String) param.get("id");
        String year=param.get("year")==null?nowYear+"":(String)param.get("year");

        Map<String ,Object> map=new HashMap<String,Object>();
        map.put("userId",id);
        map.put("year",year);
        List<CalendarMovieShowCount> calendarMovieShowCounts=movieChartsFeignService.queryCalendarMovieShowCount(map);
        List<Object[]> list=new ArrayList<Object[]>();
        for(CalendarMovieShowCount c:calendarMovieShowCounts){
            Object [] obj=new Object[2];
            obj[0]=c.getDay();
            obj[1]=c.getCount();
            list.add(obj);
        }
        return list;
    }

    @RequestMapping(value = "/queryBarMovieShowCount/{id}",method = RequestMethod.GET)
    public Map<String,Object> queryBarMovieShowCount(@PathVariable String id) {
        List<BarMovieShowCount> barMovieShowCountList=movieChartsFeignService.queryBarMovieShowCount(id);
        Map<String,Object> resultMap=new HashMap<String,Object>();
        List<Object[]> list=new ArrayList<Object[]>();
        if(barMovieShowCountList!=null){
            resultMap.put("max",barMovieShowCountList.get(barMovieShowCountList.size()-1).getCount());

            for(int i=0;i<barMovieShowCountList.size();i++){
                if(i>=barMovieShowCountList.size()-8){//页面只展示8个类型即可，可以配置数据字典
                    Object[] obj=new Object[2];
                    obj[0]=barMovieShowCountList.get(i).getType();
                    obj[1]=barMovieShowCountList.get(i).getCount();
                    list.add(obj);
                }
            }
            resultMap.put("data",list);
        }
        return resultMap;
    }


    @RequestMapping(value = "/queryPieShowMovieYearCount/{id}",method = RequestMethod.GET)
    public List<PieMovieShowCount> queryPieShowMovieYearCount(@PathVariable String id) {
        Map<String,Integer> map=new LinkedHashMap<String, Integer>();
        List<PieMovieShowCount> pieMovieShowCountList=movieChartsFeignService.queryPieShowMovieYearCount(id);
        System.out.println(pieMovieShowCountList);
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());

        int now=c.get(Calendar.YEAR);//
        int lastYear=now-1;//去年
        int last10Year=lastYear-lastYear%10-10;
        int last20Year=last10Year-10;
        int last30Year=last20Year-10;
        if(pieMovieShowCountList!=null){
            map.put(now+"新片",0);
            map.put(lastYear+"",0);
            map.put(last10Year+"-"+(lastYear-1),0);
            map.put(last20Year+"s",0);
            map.put(last30Year+"s",0);
            map.put(last30Year+"s之前",0);

            for(PieMovieShowCount p:pieMovieShowCountList){
                int year=Integer.valueOf(p.getYear());//该电影年份
                if(year==now){
                    map.put(year+"新片",map.get(year+"新片")+1);
                }if(year==lastYear){
                    map.put(lastYear+"",map.get(lastYear+"")+1);
                }
                if(year>=last10Year&&year<=(lastYear-1)){
                    map.put(last10Year+"-"+(lastYear-1),map.get(last10Year+"-"+(lastYear-1))+1);
                }
                if(year>=last20Year&&year<=(last10Year-1)){
                    map.put(last20Year+"s",map.get(last20Year+"s")+1);
                }
                if(year>=last30Year&&year<=(last20Year-1)){
                    map.put(last30Year+"s",map.get(last30Year+"s")+1);
                }
                if(year<=(last30Year-1)){
                    map.put(last30Year+"s之前",map.get(last30Year+"s之前")+1);
                }
            }
        }
        List<PieMovieShowCount> list=new ArrayList<PieMovieShowCount>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            PieMovieShowCount p=new PieMovieShowCount();
            p.setName(entry.getKey());
            p.setValue(entry.getValue());
            list.add(p);
        }
        return list;
    }

    public static void main(String[] args) {



    }
}
