package com.zuer.zuerlvdoubanmovie.feginservice;

import com.zuer.zuerlvdoubancommon.entity.MovieUser;
import com.zuer.zuerlvdoubancommon.vo.DictValue;
import com.zuer.zuerlvdoubancommon.vo.MovieUserCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "zuer-lvdouban-service")
public interface MovieUserFeignService {


    @RequestMapping(value = "/MovieUser/insertMovieUser",method = RequestMethod.POST)
    int insertMovieUser(@RequestBody MovieUser movieUser);

    @RequestMapping(value = "/MovieUser/queryMovieUserByMovieIdFromState2",method = RequestMethod.GET)
    List<MovieUser> queryMovieUserByMovieIdFromState2(@RequestParam("movieId")String movieId);


    @RequestMapping(value = "/MovieUser/queryMovieUserByMovieIdAndUserId",method = RequestMethod.GET)
    List<MovieUser> queryMovieUserByMovieIdAndUserId(@RequestParam("movieId")String movieId, @RequestParam("userId")String userId);

    @RequestMapping(value = "/MovieUser/queryMovieUserScoreCountByMovieIdAndScore",method = RequestMethod.GET)
    int queryMovieUserScoreCountByMovieIdAndScore(@RequestParam("movieId") String movieId, @RequestParam("score")String score);
    @RequestMapping(value = "/MovieUser/deleteMovieUserByMovieIdAndUserId",method = RequestMethod.GET)
    int deleteMovieUserByMovieIdAndUserId(@RequestParam("movieId")String movieId, @RequestParam("userId")String userId);

    @RequestMapping(value = "/MovieUser/queryMovieUserById",method = RequestMethod.GET)
    MovieUser queryMovieUserById(@RequestParam("id")String id);

    @RequestMapping(value = "/MovieUser/updateMovieUserById",method = RequestMethod.POST)
    int updateMovieUserById(@RequestBody MovieUser movieUser);

    @RequestMapping(value = "/MovieUser/queryMovieUserCountByMovieIdFromState",method = RequestMethod.GET)
    int queryMovieUserCountByMovieIdFromState(@RequestParam("movieId")String movieId, @RequestParam("state")String state);


    @RequestMapping(value = "/MovieUser/queryShortCommandByMovieId",method = RequestMethod.GET)
    List<MovieUserCommand> queryShortCommandByMovieId(@RequestParam("movieId")String movieId);

    @RequestMapping(value = "/MovieUser/queryMovieUserCountByUserIdFromState",method = RequestMethod.GET)
    int queryMovieUserCountByUserIdFromState(@RequestParam("userId")String userId, @RequestParam("state")String state);
}
