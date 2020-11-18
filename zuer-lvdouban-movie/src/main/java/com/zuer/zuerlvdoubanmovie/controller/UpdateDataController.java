package com.zuer.zuerlvdoubanmovie.controller;

import com.zuer.zuerlvdoubanmovie.common.util.DataConnUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@EnableAutoConfiguration
@RequestMapping(value = "/UpdateDataController")
@RestController
@Transactional(rollbackFor = {Exception.class})

/**
 * 数据文件：371M大小的txt文件,一千亿条数据
 * 测试方案一 ：
 *          使用commons-io.jar读取文件，jdbc的executeUpdate插入数据，
 *          花费时间：6h
 * 测试方案二 ：
 *          使用commons-io.jar读取文件，jdbc批处理addBatch()插入数据，
 *          花费时间：3h
 *
 * 测试方案三 ：
 *           使用.ctl文件，执行sqlldr命令导入，设置提交128行，改成并行，效果并没有提升（不清楚为什么）
 *           花费时间：3h
 */

public class UpdateDataController {
    int idx;
    Connection conn = null;
    PreparedStatement pstmt = null;

    /**
     * 使用commons-io.jar包的FileUtils的类进行读取
     * txt中内容文件的分割必须为|，java中需要加转译符号
     *
     * @return void
     */
    private void readTxtFileByFileUtils(String fileName) {
        File file = new File(fileName);
        conn = DataConnUtils.dbConnectionZuer03();
        try {
            int i = 0;
            LineIterator lineIterator = FileUtils.lineIterator(file, "GBK");
            while (lineIterator.hasNext()) {
                i++;
                String line = lineIterator.nextLine();
                // 行数据转换成数组
                String[] custArray = line.split("\\|");
                if (custArray.length == 6) {
                    insertDemo(custArray);
                }
                if (i % 100000 == 0) {
                    System.out.println(new Date() + ">>>>成功数：" + i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            DataConnUtils.dbDisConnection();
        }
    }

    private void readTxtFileByFileUtilsAddBatch(String fileName) {
        File file = new File(fileName);
        conn = DataConnUtils.dbConnectionZuer03();
        String sqlStr = "insert into DEMO_1 (ID, NAME, AGE, SEX, ADDRESS, NATION, PROVENANCE,CRT_DATE) VALUES (?,?,?,?,?,?,?,?)";

        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sqlStr);
            int i = 0;
            LineIterator lineIterator = null;
            int n = 0;
            lineIterator = FileUtils.lineIterator(file, "GBK");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            while (lineIterator.hasNext()) {
                i++;
                idx = 1;
                String line = lineIterator.nextLine();
                // 行数据转换成数组
                String[] custArray = line.split("\\|");
                if (custArray.length == 6) {
                    pstmt.setString(idx++, UUID.randomUUID().toString().replace("-", ""));
                    pstmt.setString(idx++, custArray[0]);
                    pstmt.setString(idx++, custArray[1]);
                    pstmt.setString(idx++, custArray[2]);
                    pstmt.setString(idx++, custArray[3]);
                    pstmt.setString(idx++, custArray[4]);
                    pstmt.setString(idx++, custArray[5]);
                    if (i % 100000 == 0) {
                        n++;
                        cal.set(Calendar.DAY_OF_MONTH,n);
                    }

                    pstmt.setTimestamp(idx++,new Timestamp(cal.getTimeInMillis()));

                    //10w提交一次
                    pstmt.addBatch();
                    if (i % 100000 == 0) {
                        pstmt.executeBatch();
                        pstmt.clearBatch();
                        conn.commit();
                    }
                }
                if (i % 100000 == 0) {
                    System.out.println(n+":"+cal.getTimeInMillis());
                    System.out.println(new Date() + ">>>>成功数：" + i);
                }
            }
            pstmt.executeBatch(); //执行批处理
            pstmt.clearBatch();  //清空批处理
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            DataConnUtils.dbDisConnection();
        }
    }

    /**
     * @return void
     * @Title: insertCustInfo
     * @date 2017年11月13日
     */
    private void insertDemo(String[] strArray) {
        try {
            String sqlStr = "insert into DEMO_2 (ID, NAME, AGE, SEX, ADDRESS, NATION, PROVENANCE) VALUES (?,?,?,?,?,?,?)";

            pstmt = conn.prepareStatement(sqlStr);
            idx = 1;
            pstmt.clearParameters();
            pstmt.setString(idx++, UUID.randomUUID().toString().replace("-", ""));
            pstmt.setString(idx++, strArray[0]);
            pstmt.setString(idx++, strArray[1]);
            pstmt.setString(idx++, strArray[2]);
            pstmt.setString(idx++, strArray[3]);
            pstmt.setString(idx++, strArray[4]);
            pstmt.setString(idx++, strArray[5]);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * * 写控制文件.ctl
     */
    public static void ctlFileWriter() {
        String fileRoute = "C:\\Users\\Zuer\\Desktop\\";        // 文件地址路径
        String fileName = "users.txt";        // 数据文件名
        String tableName = "DEMO_1";            // 表名
        String fieldName = "( " +
                /* "ID FILLER ,"+*/
                "NAME," +
                "AGE," +
                "SEX," +
                "ADDRESS," +
                "NATION," +
                "PROVENANCE" +
                ")"; // 要写入表的字段
        String ctlfileName = "users.ctl";    // 控制文件名
        FileWriter fw = null;
        String strctl = "Options (skip=1,rows=100000)" +
                "\n Load Data Infile '" + fileRoute + "" + fileName + "'" +
                "\n Append into table " + tableName + "" +
                "\n Fields terminated by '|'" +
                "\n Optionally enclosed by '\"'" +
                "\n Trailing nullcols " + fieldName + "";
        System.out.println(strctl);
        try {
            fw = new FileWriter(fileRoute + "" + ctlfileName);
            fw.write(strctl);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // 要执行的DOS命令
        String user = "zuer03";
        String psw = "password";
        String database = "localhost:1521/ORCL"; // IP要指向数据库服务器的地址
        String logfileName = "users.log";

        executeDos(user, psw, database, fileRoute, ctlfileName, logfileName);
    }

    /**
     * * 调用系统DOS命令
     *
     * @param user
     * @param psw
     * @param fileRoute
     * @param ctlfileName
     * @param logfileName
     */
    public static void executeDos(String user, String psw, String database, String fileRoute, String ctlfileName,
                                  String logfileName) {
        InputStream ins = null;
        // 要执行的DOS命令
        String cmd = "sqlldr.exe userid=" + user +
                "/" + psw + "@" + database +
                " control=" + fileRoute + ctlfileName +
                " log=" + fileRoute + logfileName + "" +
                " parallel=true";

        try {
            Process process = Runtime.getRuntime().exec(cmd);
            ins = process.getInputStream(); // 获取执行cmd命令后的信息

            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String msg = new String(line.getBytes("ISO-8859-1"), "UTF-8");
                //System.out.println(msg); // 输出
            }
            int exitValue = process.waitFor();
            if (exitValue == 0) {
                System.out.println("返回值：" + exitValue + "\n数据导入成功");

            } else {
                System.out.println("返回值：" + exitValue + "\n数据导入失败");
            }

            process.getOutputStream().close(); // 关闭
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        System.out.println("导入数据开始:" + new Date());
        UpdateDataController udc = new UpdateDataController();
        Long startTime = new Date().getTime();
        //udc.readTxtFileByFileUtils("C:\\Users\\Zuer\\Desktop\\3333.txt");
        udc.readTxtFileByFileUtilsAddBatch("C:\\Users\\Zuer\\Desktop\\3333.txt");
        //udc.ctlFileWriter();
        System.out.println("导入数据总共耗时:" + (new Date().getTime() - startTime) / 1000 + "秒");

    }
}

