package com.kepler.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.dao.FileUpload;
import com.kepler.service.DataService;
import com.kepler.vo.DataDocVo;
import com.kepler.vo.empVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("data")
public class DataController {
    @Resource
    private DataService ds;

    @RequestMapping("/datadoc")
    public String datadoc(){
        return "dataDoc";
    }
    @RequestMapping("/Alldata")
    public void Alldata(HttpServletResponse response)throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        List sum = ds.selDocData();
        json.put("code",0);
        json.put("count",sum.size());
        json.put("msg","");
        json.put("data",sum);
        pwt.print(json.toString());
    }

    @RequestMapping("/addFile")
    @ResponseBody
    public String addFile(DataDocVo docVo, MultipartFile file, HttpServletRequest request){
        Map map = new HashMap();
        String realPath =  request.getRealPath("");
        String dirPath = "F:\\T3\\HTOA\\web\\WEB-INF\\static\\file\\";
        //上传文件
        String fileName = FileUpload.upload(file,dirPath,request);

        //保存数据库
        docVo.setDataName(file.getOriginalFilename());
        docVo.setUrl(dirPath+fileName);
        int empId = (int) request.getSession().getAttribute("empId");
        docVo.setEmpid(empId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        docVo.setOptime((new Date()));
        //保存到数据库
        ds.addFile(docVo);
        return "success";
    }

    @RequestMapping("/download.do")
    public void download(Integer docId,HttpServletRequest request,HttpServletResponse response) throws Exception{
        DataDocVo docVo = ds.findById(docId);

        // 指定要下载的文件所在路径
        String path = docVo.getUrl();
        // 创建该文件对象
        File file = new File(path);

        String fileName = this.getFilename(request,docVo.getDataName());

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

        //打开本地文件流
        InputStream inputStream = new FileInputStream(file);
        //激活下载操作
        OutputStream os = response.getOutputStream();

        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b,0,length);
        }
        // 这里主要关闭。
        os.close();
        inputStream.close();
    }
    /**
     * 根据浏览器的不同进行编码设置，返回编码后的文件名
     */
    public String getFilename(HttpServletRequest request,
                              String filename) throws Exception {
        // IE不同版本User-Agent中出现的关键词
        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
        // 获取请求头代理信息
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                //IE内核浏览器，统一为UTF-8编码显示
                return URLEncoder.encode(filename, "UTF-8");
            }
        }
        //火狐等其它浏览器统一为ISO-8859-1编码显示
        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
    }

    @RequestMapping("/delFile")
    @ResponseBody
    public String delFile(DataDocVo docVo){
        DataDocVo db = ds.findFile(docVo.getDocId());
        ds.deleteFile(docVo);
        return "success";
    }
}
