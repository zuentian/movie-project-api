package com.zuer.zuerlvdoubanmovie.controller;

import com.alibaba.fastjson.JSONObject;
import com.zuer.zuerlvdoubancommon.entity.MovieUser;
import com.zuer.zuerlvdoubancommon.entity.MovieUserRecord;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubancommon.utils.HttpClientUtils;
import com.zuer.zuerlvdoubancommon.vo.MovieScoreSection;
import com.zuer.zuerlvdoubancommon.vo.MovieUserCommand;
import com.zuer.zuerlvdoubanmovie.executor.AnalysisMovieData;
import com.zuer.zuerlvdoubanmovie.executor.AnalysisUserMovieTypeCount;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieUserFeignService;
import com.zuer.zuerlvdoubanmovie.service.MovieUserRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@EnableAutoConfiguration
@RequestMapping(value = "/MovieUserController")
@RestController
@Transactional(rollbackFor = {Exception.class})
public class MovieUserController {

    @Autowired
    private MovieUserFeignService movieUserFeignService;

    @Autowired
    private MovieCalculateScoreController movieCalculateScoreController;

    @RequestMapping(value = "/insertMovieUser", method = RequestMethod.POST)
    public void insertMovieUser(@RequestParam Map<String, Object> param) throws Exception {

        MovieUser movieUser = EntityUtils.mapToEntity(param, MovieUser.class);
        EntityUtils.setCreatAndUpdatInfo(movieUser);
        if ("1".equals(movieUser.getState())) {
            movieUser.setWatchBeforeTime(new Date());
        }
        if ("2".equals(movieUser.getState())) {
            movieUser.setWatchAfterTime(new Date());
        }
        int i = movieUserFeignService.insertMovieUser(movieUser);
        if (i > 0) {
            //重新为该电影计算想看人数和看过人数
            movieCalculateScoreController.calculateWatchCount(movieUser.getMovieId());
            //重新为该电影计算评分
            movieCalculateScoreController.calculateScore(movieUser.getMovieId());
        }

    }


    @RequestMapping(value = "/queryMovieUserByMovieIdAndUserId", method = RequestMethod.POST)
    public MovieUser queryMovieUserByMovieIdAndUserId(@RequestParam Map<String, Object> param) throws Exception {
        String movieId = param.get("movieId") == null ? null : (String) param.get("movieId");
        String userId = param.get("userId") == null ? null : (String) param.get("userId");
        List<MovieUser> movieUserList = movieUserFeignService.queryMovieUserByMovieIdAndUserId(movieId, userId);
        if (movieUserList != null && movieUserList.size() > 0) {
            return movieUserList.get(0);

        }
        return new MovieUser();
    }


    @RequestMapping(value = "/queryMovieScoreInfo/{id}", method = RequestMethod.GET)
    public List<MovieScoreSection> queryMovieScoreInfo(@PathVariable String id) {
        return movieCalculateScoreController.getScoreSectionCount(id);
    }

    @RequestMapping(value = "/deleteMovieUserByMovieIdAndUserId", method = RequestMethod.POST)
    public void deleteMovieUserByMovieIdAndUserId(@RequestParam Map<String, Object> param) throws Exception {
        String movieId = param.get("movieId") == null ? null : (String) param.get("movieId");
        String userId = param.get("userId") == null ? null : (String) param.get("userId");
        List<MovieUser> movieUserList = movieUserFeignService.queryMovieUserByMovieIdAndUserId(movieId, userId);
        if (movieUserList != null && movieUserList.size() > 0) {
            int i = movieUserFeignService.deleteMovieUserByMovieIdAndUserId(movieId, userId);
            if (i > 0) {
                //重新为该电影计算想看人数和看过人数
                movieCalculateScoreController.calculateWatchCount(movieId);
                //重新为该电影计算评分
                movieCalculateScoreController.calculateScore(movieId);
            }
        }
    }

    @RequestMapping(value = "/updateMovieUserByMovieIdAndUserId", method = RequestMethod.POST)
    public void updateMovieUserByMovieIdAndUserId(@RequestParam Map<String, Object> param) throws Exception {
        MovieUser movieUser = EntityUtils.mapToEntity(param, MovieUser.class);
        EntityUtils.setUpdatedInfo(movieUser);
        if ("1".equals(movieUser.getState())) {
            movieUser.setWatchBeforeTime(new Date());
        }
        if ("2".equals(movieUser.getState())) {
            movieUser.setWatchAfterTime(new Date());
        }
        int i=movieUserFeignService.updateMovieUserById(movieUser);
        if(i>0){
            //重新为该电影计算想看人数和看过人数
            movieCalculateScoreController.calculateWatchCount(movieUser.getMovieId());
            //重新为该电影计算评分
            movieCalculateScoreController.calculateScore(movieUser.getMovieId());
        }
    }


    @RequestMapping(value = "/queryShortCommandByMovieId", method = RequestMethod.POST)
    public List<MovieUserCommand> queryShortCommandByMovieId(@RequestParam Map<String, Object> param) throws Exception {
        String movieId = param.get("movieId") == null ? null : (String) param.get("movieId");
        return movieUserFeignService.queryShortCommandByMovieId(movieId);
    }

    @RequestMapping(value = "/queryWatchMovieCount/{id}", method = RequestMethod.GET)
    public Map<String,Object> queryWatchMovieCount(@PathVariable String id) {
        Map<String,Object> resultMap=new HashMap<String, Object>();
        int watchBeforeCount = movieUserFeignService.queryMovieUserCountByUserIdFromState(id,"1");
        int watchAfterCount = movieUserFeignService.queryMovieUserCountByUserIdFromState(id,"2");
        resultMap.put("watchBeforeCount",watchBeforeCount);
        resultMap.put("watchAfterCount",watchAfterCount);
        return resultMap;
    }


    @Resource
    private MovieUserRecordService movieUserRecordService;
    @Resource
    private AnalysisMovieData analysisMovieData;
    @Resource
    private AnalysisUserMovieTypeCount analysisUserMovieTypeCount;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 用户关联电影的操作
     * @param movieUserRecord
     * @throws Exception
     */
    @RequestMapping(value = "/insertMovieUserInfo", method = RequestMethod.POST)
    public void insertMovieUserInfo(@RequestBody MovieUserRecord movieUserRecord) throws Exception {
        logger.info("用户更新电影记录业务 start");
        movieUserRecordService.insertMovieUserRecord(movieUserRecord);
        //模拟处理时间有3秒
        TimeUnit.SECONDS.sleep(3);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                /*
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        analysisMovieData.changeUserId(movieUserRecord.getMovieId());
                    }
                });*/
                /*executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        analysisUserMovieTypeCount.addMovieTypeCount(movieUserRecord.getUserId(),
                                movieUserRecord.getMovieType());
                    }
                });*/
            }
        });
        logger.info("用户更新电影记录业务 end");
    }

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
            2,
            1000,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(10),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    System.out.println("线程" + r.hashCode() + "创建");
                    //线程命名
                    Thread th = new Thread(r, "threadPool" + r.hashCode());
                    return th;
                }
            },
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        String url = "http://localhost:9994/MovieUserController/insertMovieUserInfo";

        int i = 4;
        for ( ; i < 15; i++) {
            final int a = i;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                JSONObject object = new JSONObject();
                object.put("movieId","111");
                object.put("userId","3321N");
                object.put("state","1");
                object.put("score","7");
                object.put("movieType","0001");
                object.put("shortCommand","------"+a);
                String result =  HttpClientUtils.doPost(url,object.toJSONString());
                System.out.println(object.toJSONString());
                }
            };
            Thread thread  = new Thread(runnable);
            thread.start();
        }

    }
}





