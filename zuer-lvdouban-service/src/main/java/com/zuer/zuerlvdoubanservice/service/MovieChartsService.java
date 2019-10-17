package com.zuer.zuerlvdoubanservice.service;

import com.zuer.zuerlvdoubancommon.charts.CalendarMovieShowCount;

import java.util.List;
import java.util.Map;

public interface MovieChartsService {
    List<CalendarMovieShowCount> queryCalendarMovieShowCount(Map<String, Object> map);
}
