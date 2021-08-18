package com.zuer.zuerlvdoubanmovie.controller;

import com.zuer.zuerlvdoubancommon.entity.MovieUser;
import com.zuer.zuerlvdoubancommon.entity.MovieUserRecord;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubancommon.vo.MovieScoreSection;
import com.zuer.zuerlvdoubancommon.vo.MovieUserCommand;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieUserFeignService;
import com.zuer.zuerlvdoubanmovie.service.MovieUserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


   //@Autowired
    //@Qualifier("MovieUserRecordService")
     @Resource(name = "MovieUserRecordService")
    private MovieUserRecordService movieUserRecordService;
    /**
     * 用户关联电影的操作
     * @param movieUserRecord
     * @throws Exception
     */
    @RequestMapping(value = "/insertMovieUserInfo", method = RequestMethod.POST)
    public void insertMovieUserInfo(@RequestBody MovieUserRecord movieUserRecord) throws Exception {
        //MovieUserRecord movieUserRecord = EntityUtils.mapToEntity(param, MovieUserRecord.class);
        movieUserRecordService.insertMovieUserRecord(movieUserRecord);
    }
}
