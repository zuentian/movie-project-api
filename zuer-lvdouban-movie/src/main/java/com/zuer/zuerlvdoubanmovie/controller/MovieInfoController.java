package com.zuer.zuerlvdoubanmovie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubancommon.entity.*;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubancommon.utils.UploadFile;
import com.zuer.zuerlvdoubancommon.vo.MovieInfoExp;
import com.zuer.zuerlvdoubanmovie.feginservice.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@EnableAutoConfiguration
@RequestMapping(value = "/MovieInfoController")
@PropertySource("classpath:uploadMovieImage.properties")
@RestController
@Transactional(rollbackFor = {Exception.class})
public class MovieInfoController {

    private static final Logger logger= LoggerFactory.getLogger(MovieInfoController.class);

    @Autowired
    private MovieInfoFeignService movieInfoFeignService;

    @Autowired
    private MovieRelNameFeignService movieRelNameFeignService;

    @Autowired
    private MovieTypeFeignService movieTypeFeignService;

    @Autowired
    private MovieCountryFeignService movieCountryFeignService;

    @RequestMapping(value = "/insertMovieInfo",method = RequestMethod.POST)
    public String insertMovieInfo(@RequestParam Map<String,Object> param) throws Exception {

        String info=param.get("movieInfo")==null?null:(String) param.get("movieInfo");
        ObjectMapper mapper = new ObjectMapper();
        MovieInfo movieInfo= mapper.readValue(info, MovieInfo.class);
        String id=UUID.randomUUID().toString();
        movieInfo.setId(id);
        EntityUtils.setCreatAndUpdatInfo(movieInfo);
        movieInfoFeignService.insertMovieInfo(movieInfo);

        String movieRelName=param.get("movieRelName")==null?null:(String)param.get("movieRelName");
        if(StringUtils.isNotBlank(movieRelName)){
            String[] movieRelNames = movieRelName.trim().split(",");
            for(String str:movieRelNames){
                MovieRelName movieRelNameNew=new MovieRelName();
                movieRelNameNew.setId(UUID.randomUUID().toString());
                movieRelNameNew.setMovieId(id);
                movieRelNameNew.setName(str);
                EntityUtils.setCreateInfo(movieRelNameNew);
                insertMovieRelName(movieRelNameNew);
            }
        }

        String movieType=param.get("movieType")==null?null:(String)param.get("movieType");

        if(StringUtils.isNotBlank(movieType)){
            String[] movieTypes = movieType.trim().split(",");
            for(String str:movieTypes){
                MovieType movieTypeNew=new MovieType();
                movieTypeNew.setId(UUID.randomUUID().toString());
                movieTypeNew.setMovieId(id);
                movieTypeNew.setType(str);
                EntityUtils.setCreateInfo(movieTypeNew);
                insertMovieType(movieTypeNew);

            }
        }
        String movieCountry=param.get("movieCountry")==null?null:(String)param.get("movieCountry");
        if(StringUtils.isNotBlank(movieCountry)){
            String[] movieCountrys = movieCountry.trim().split(",");
            for(String str:movieCountrys){
                MovieCountry movieCountryNew =new MovieCountry();
                movieCountryNew.setId(UUID.randomUUID().toString());
                movieCountryNew.setMovieId(id);
                movieCountryNew.setCountryCode(str);
                EntityUtils.setCreateInfo(movieCountryNew);
                insertMovieCountry(movieCountryNew);

            }
        }

        return id;
    }
    /*电影出品方国家地区数据插入*/
    private void insertMovieCountry(MovieCountry movieCountry) {
        movieCountryFeignService.insertMovieCountry(movieCountry);
    }

    /*电影类型数据插入*/
    private void insertMovieType(MovieType movieType) {
        movieTypeFeignService.insertMovieType(movieType);
    }

    /*电影相关人物数据插入*/
    private void insertMovieRelName(MovieRelName movieRelName) {
        movieRelNameFeignService.insertMovieRelName(movieRelName);
    }


    @RequestMapping(value = "/queryMovieInfoByParam",method = RequestMethod.POST)
    public Map<String,Object> queryMovieInfoByParam(@RequestParam Map<String,Object> param) throws Exception {
        Map<String,Object> map=new HashMap<String,Object>();
        String name=param.get("param")==null?null:(String)param.get("param");
        map.put("name",name);

        String time1=param.get("startTime")==null?null:(String) param.get("startTime");
        if(StringUtils.isNotBlank(time1)){
            long startTime=Long.valueOf(time1);
            map.put("startTime",new Date(startTime));
        }
        String time2=param.get("endTime")==null?null:(String)param.get("endTime");
        if(StringUtils.isNotBlank(time2)){
            long endTime=Long.valueOf(time2);
            map.put("endTime",new Date(endTime));
        }
        String startScore=param.get("startScore")==null?null:(String)param.get("startScore");
        if(StringUtils.isNotBlank(startScore)){
            map.put("startScore",Double.valueOf(startScore));
        }

        String endScore=param.get("endScore")==null?null:(String)param.get("endScore");
        if(StringUtils.isNotBlank(endScore)){
            map.put("endScore",Double.valueOf(endScore));
        }

        int page=Integer.valueOf((String)param.get("page"));
        int limit=Integer.valueOf((String)param.get("limit"));
        int start=(page-1)*limit+1;
        int end=page*limit;
        map.put("start",start);
        map.put("end",end);

        String movieType=param.get("movieType")==null?null:(String)param.get("movieType");
        map.put("movieType",movieType);
        String movieCountry=param.get("movieCountry")==null?null:(String)param.get("movieCountry");
        map.put("movieCountry",movieCountry);

        Map<String,Object> resultMap=new HashMap<String, Object>();
        int count=movieInfoFeignService.queryMovieInfoByParamCount(map);
        resultMap.put("count",count);
        List<MovieInfoExp> movieInfoListExpList=new ArrayList<MovieInfoExp>();
        if(count>0){
            List<MovieInfo> movieInfoList =movieInfoFeignService.queryMovieInfoByParam(map);
            movieInfoListExpList=movieInfoList.parallelStream().map(movieInfo -> {
                MovieInfoExp movieInfoExp=new MovieInfoExp();
                BeanUtils.copyProperties(movieInfo,movieInfoExp);

                List<MovieCountry> movieCountryList=movieCountryFeignService.queryMovieCountryByMovieId(movieInfo.getId());

                List<MovieType> movieTypeList=movieTypeFeignService.queryMovieTypeByMovieId(movieInfo.getId());

                List<MovieRelName> movieRelNameList=movieRelNameFeignService.queryMovieRelNameByMovieId(movieInfo.getId());

                movieInfoExp.setMovieCountryList(movieCountryList);
                movieInfoExp.setMovieTypeList(movieTypeList);
                movieInfoExp.setMovieRelNameList(movieRelNameList);
                return movieInfoExp;
            }).collect(Collectors.toList());

        }
        resultMap.put("list",movieInfoListExpList);

        return resultMap;
    }


    @RequestMapping(value = "/queryMoviePictureInfoByMovieIdFromSix/{id}",method = RequestMethod.GET)
    public Map<String,Object> queryMoviePictureInfoByMovieIdFromSix(@PathVariable String id){
        Map<String,Object> resultMap=new HashMap<String, Object>();
        //此处查看电影详细信息先展示前六张图片
        List<MoviePictureInfo> moviePictureInfoList=moviePictureInfoFeignService.queryMoviePictureInfoByMovieIdFromSix(id);
        int moviePictureCount=moviePictureInfoFeignService.queryMoviePictureInfoByMovieIdCount(id);

        resultMap.put("moviePictureInfoList",moviePictureInfoList);
        resultMap.put("moviePictureCount",moviePictureCount);
        return resultMap;
    }
    @RequestMapping(value = "/queryMovieBaseInfoById/{id}",method = RequestMethod.GET)
    public MovieInfo queryMovieBaseInfoById(@PathVariable String id) {
        return movieInfoFeignService.queryMovieInfoById(id);
    }

    @RequestMapping(value = "/queryMovieInfoById/{id}",method = RequestMethod.GET)
    public Map<String,Object> queryMovieInfoById(@PathVariable String id){
        Map<String,Object> resultMap=new HashMap<String, Object>();

        MovieInfo movieInfo=movieInfoFeignService.queryMovieInfoById(id);
        resultMap.put("movieInfo",movieInfo);

        Date date=movieInfo.getMovieShowTime();
        resultMap.put("movieShowTime",date.getTime());

        List<MovieRelName> movieRelNameList=movieRelNameFeignService.queryMovieRelNameByMovieId(id);
        resultMap.put("movieRelNameList",movieRelNameList);

        List<MovieCountry> movieCountryList=movieCountryFeignService.queryMovieCountryByMovieId(id);
        resultMap.put("movieCountryList",movieCountryList);

        List<MovieType> movieTypeList=movieTypeFeignService.queryMovieTypeByMovieId(id);
        resultMap.put("movieTypeList",movieTypeList);


        return resultMap;
    }


    @RequestMapping(value = "/updateMovieInfo",method = RequestMethod.POST)
    public void updateMovieInfo(@RequestParam Map<String,Object> param) throws Exception {
        String info=param.get("movieInfo")==null?null:(String) param.get("movieInfo");
        ObjectMapper mapper = new ObjectMapper();
        MovieInfo movieInfo= mapper.readValue(info, MovieInfo.class);
        EntityUtils.setUpdatedInfo(movieInfo);
        movieInfoFeignService.updateMovieInfoById(movieInfo);
        int count=0;
        count=movieRelNameFeignService.deleteMovieRelNameByMovieId(movieInfo.getId());
        if(count>=0){
            String movieRelName=param.get("movieRelName")==null?null:(String)param.get("movieRelName");
            if(StringUtils.isNotBlank(movieRelName)){
                String[] movieRelNames = movieRelName.trim().split(",");
                for(String str:movieRelNames){
                    MovieRelName movieRelNameNew=new MovieRelName();
                    movieRelNameNew.setId(UUID.randomUUID().toString());
                    movieRelNameNew.setMovieId(movieInfo.getId());
                    movieRelNameNew.setName(str);
                    EntityUtils.setCreateInfo(movieRelNameNew);
                    insertMovieRelName(movieRelNameNew);
                }
            }
        }

        count=movieTypeFeignService.deleteMovieTypeByMovieId(movieInfo.getId());
        if(count>=0){
            String movieType=param.get("movieType")==null?null:(String)param.get("movieType");
            if(movieType!=null){
                String[] movieTypes = movieType.trim().split(",");
                for(String str:movieTypes){
                    MovieType movieTypeNew=new MovieType();
                    movieTypeNew.setId(UUID.randomUUID().toString());
                    movieTypeNew.setMovieId(movieInfo.getId());
                    movieTypeNew.setType(str);
                    EntityUtils.setCreateInfo(movieTypeNew);
                    insertMovieType(movieTypeNew);

                }
            }
        }


        count=movieCountryFeignService.deleteMovieCountryByMovieId(movieInfo.getId());
        if(count>=0){
            String movieCountry=param.get("movieCountry")==null?null:(String)param.get("movieCountry");
            if(movieCountry!=null){
                String[] movieCountrys = movieCountry.trim().split(",");
                for(String str:movieCountrys){
                    if(StringUtils.isNotBlank(str)){
                        MovieCountry movieCountryNew =new MovieCountry();
                        movieCountryNew.setId(UUID.randomUUID().toString());
                        movieCountryNew.setMovieId(movieInfo.getId());
                        movieCountryNew.setCountryCode(str);
                        EntityUtils.setCreateInfo(movieCountryNew);
                        insertMovieCountry(movieCountryNew);
                    }
                }
            }
        }

    }



    @RequestMapping(value = "/deleteMovieInfoById/{id}",method = RequestMethod.GET)
    public void deleteMovieInfoById(@PathVariable String id) {
        movieInfoFeignService.deleteMovieInfoById(id);
        movieRelNameFeignService.deleteMovieRelNameByMovieId(id);//删除电影相关人物
        movieTypeFeignService.deleteMovieTypeByMovieId(id);//删除电影的类型
        movieCountryFeignService.deleteMovieCountryByMovieId(id);//删除电影的出品方国家地区

        List<MoviePictureInfo>  moviePictureInfoList=moviePictureInfoFeignService.queryMoviePictureInfoByMovieId(id);
        for(MoviePictureInfo moviePictureInfo:moviePictureInfoList){
            if(UploadFile.deleteFile(rootPath+moviePictureInfo.getFileUrl())){//删除硬盘中储存的电影海报和剧照
                moviePictureInfoFeignService.deleteMoviePictureInfoById(moviePictureInfo.getId());//删除电影海报和剧照的所有信息
            }
        }
    }

    @RequestMapping(value = "/deletePictureById/{id}",method = RequestMethod.GET)
    public void deletePictureById(@PathVariable String id) {
        MoviePictureInfo moviePictureInfo=moviePictureInfoFeignService.queryMoviePictureInfoById(id);
        if(UploadFile.deleteFile(rootPath+moviePictureInfo.getFileUrl())){
            moviePictureInfoFeignService.deleteMoviePictureInfoById(id);
        }
    }

    @Value("${local.vueIp}")
    private String vueIp;
    @Value("${local.upload.images.path}")
    private String uploadImagesPath;
    @Value("${static.image.path}")
    private String rootPath;
    @Autowired
    private MoviePictureInfoFeignService moviePictureInfoFeignService;

    @RequestMapping(value="/insertMovieInfoPictureStage", method= RequestMethod.POST)
    public void insertMovieInfoPictureStage(@RequestParam("files") MultipartFile[] files,HttpServletRequest request) throws Exception {
        String id=request.getParameter("id");

        String datePath=getDatePath();
        logger.info("insertMovieInfoPictureStage 电影剧照上传的根路径:rootPath=["+rootPath+"] 文件的放置路径：uploadImagesPath=["+uploadImagesPath+"] 日期格式的文件路径：datePath=["+datePath+"]");

        String path=rootPath+uploadImagesPath+File.separator+datePath;
        logger.info("insertMovieInfoPictureStage 电影剧照上传全路径path=["+path+"]");

        //电影剧照
        if(files!=null&&files.length>0){
            for(MultipartFile file:files){
                MoviePictureInfo moviePictureInfo=new MoviePictureInfo();
                String moviePictureId=UUID.randomUUID().toString();
                moviePictureInfo.setId(moviePictureId);
                moviePictureInfo.setMovieId(id);
                moviePictureInfo.setFileName(file.getOriginalFilename());
                moviePictureInfo.setType("S");
                String fileName=UploadFile.uploadMultipartFile(file,moviePictureId,path);
                moviePictureInfo.setFileUri(File.separator+vueIp+ uploadImagesPath+File.separator+datePath+File.separator+fileName);
                moviePictureInfo.setFileUrl(uploadImagesPath+File.separator+datePath+File.separator+fileName);

                logger.info("insertMovieInfoPictureStage moviePictureInfo.getFileUri()=["+moviePictureInfo.getFileUri()+"]");
                logger.info("insertMovieInfoPictureStage moviePictureInfo.getFileUrl()=["+moviePictureInfo.getFileUrl()+"]");

                EntityUtils.setCreateInfo(moviePictureInfo);
                moviePictureInfoFeignService.insertMoviePictureInfo(moviePictureInfo);
            }
        }

    }


    /*
    获取带日期的路径
     */
    public String getDatePath(){

        Calendar cal = Calendar.getInstance();
        String year=cal.get(Calendar.YEAR)+"";
        String month=cal.get(Calendar.MONTH)+1<10?"0"+(cal.get(Calendar.MONTH)+1):(cal.get(Calendar.MONTH)+1)+"";
        String day=cal.get(Calendar.DAY_OF_MONTH)<10?"0"+cal.get(Calendar.DAY_OF_MONTH):cal.get(Calendar.DAY_OF_MONTH)+"";
        return year+ File.separator+month+ File.separator+day;


    }


    @RequestMapping(value = "/queryMoviePictureByParam",method = RequestMethod.POST)
    public Map<String,Object> queryMoviePictureByParam(@RequestParam Map<String,Object> param){

        Map<String,Object> resultMap=moviePictureInfoFeignService.queryMoviePictureByParam(param);
        return resultMap;
    }



    @RequestMapping(value="/moviePictureBillUpload", method= RequestMethod.POST)
    public String moviePictureBillUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        String movieId=request.getParameter("id");
        String name=request.getParameter("name");

        String datePath=getDatePath();
        logger.info("moviePictureBillUpload 电影海报上传的根路径:rootPath=["+rootPath+"] 文件的放置路径：uploadImagesPath=["+uploadImagesPath+"] 日期格式的文件路径：datePath=["+datePath+"]");

        String path=rootPath+uploadImagesPath+File.separator+datePath;
        logger.info("moviePictureBillUpload 电影海报上传全路径path=["+path+"]");

        //上传新的海报不用删除原来的海报

        MoviePictureInfo moviePictureInfo=new MoviePictureInfo();
        String id=UUID.randomUUID().toString();

        moviePictureInfo.setId(id);
        moviePictureInfo.setMovieId(movieId);
        moviePictureInfo.setType("B");
        moviePictureInfo.setFileName(name);
        String fileName=UploadFile.uploadMultipartFile(file,id,path);
        moviePictureInfo.setFileUri(File.separator+vueIp+ uploadImagesPath+File.separator+datePath+File.separator+fileName);
        moviePictureInfo.setFileUrl(uploadImagesPath+File.separator+datePath+File.separator+fileName);

        logger.info("moviePictureBillUpload moviePictureInfo.getFileUri()=["+moviePictureInfo.getFileUri()+"]");
        logger.info("moviePictureBillUpload moviePictureInfo.getFileUrl()=["+moviePictureInfo.getFileUrl()+"]");

        EntityUtils.setCreateInfo(moviePictureInfo);
        moviePictureInfoFeignService.insertMoviePictureInfo(moviePictureInfo);
        return moviePictureInfo.getFileUri();

    }
    @RequestMapping(value = "/queryMoviePictureBillOne/{id}",method = RequestMethod.GET)
    public MoviePictureInfo queryMoviePictureBillOne(@PathVariable String id)  {
         List<MoviePictureInfo> moviePictureInfoList=moviePictureInfoFeignService.queryMoviePictureInfoByMovieIdFromType(id,"B");
         if(null!=moviePictureInfoList&&moviePictureInfoList.size()>0){
             return moviePictureInfoList.get(0);
         }
         return new MoviePictureInfo();
    }

}
