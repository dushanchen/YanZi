package com.yanzi.taurus.controller;

import java.io.IOException;
import java.util.UUID;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;

public class Test {

    private static final String ACCESS_KEY = "bMsxBlEhchY7vyVNBn8aZRMbQWNIITXd2oewsiQA";
    private static final String SECRET_KEY = "B2hDo4x-wTlXcKfOUI9wnA3wrn9iGkqHtTV3eoQY";
    private static final String URL_PREFIX = "http://qiniu.image.yetter.cn/";
    private static final int TOKEN_EXPRIES = 5 * 60;
    private static final String BUCKET = "yetter";

    private Auth auth;
    
    public static void main(String args[]) throws IOException {
        new Test().upload();
      //简单上传，使用默认策略，只需要设置上传的空间名就可以了
//       new Test().listFiles();
    }
    public String getUpToken() {
        //密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        return auth.uploadToken(BUCKET);
    }
        public void upload() throws IOException {
            //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
            Zone z = Zone.autoZone();
            Configuration c = new Configuration(z);
            //创建上传对象
            UploadManager uploadManager = new UploadManager(c);

            //上传到七牛后保存的文件名
            String key = "curriculum/map/" + UUID.randomUUID() + ".jpg";
            //上传文件的路径
            String FilePath = "C://Users//dsczijizuo//Desktop//杜膳辰//照片//接收文件//11.jpg";
            try {
                //调用put方法上传
                Response res = uploadManager.put(FilePath, key, getUpToken());
                //打印返回的信息
                System.out.println(res.bodyString());
            } catch (QiniuException e) {
                Response r = e.response;
                //响应的文本信息
                System.out.println(r.bodyString());
            }
    }
        public void listFiles(){
        	auth = Auth.create(ACCESS_KEY, SECRET_KEY);

            Zone z = Zone.zone0();
            Configuration c = new Configuration(z);

            //实例化一个BucketManager对象
            BucketManager bucketManager = new BucketManager(auth, c);
 

            try {
                //调用listFiles方法列举指定空间的指定文件
                //参数一：bucket    空间名
                //参数二：prefix    文件名前缀
                //参数三：marker    上一次获取文件列表时返回的 marker
                //参数四：limit     每次迭代的长度限制，最大1000，推荐值 100
                //参数五：delimiter 指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
                FileListing fileListing = bucketManager.listFiles(BUCKET, null, null, 1000, null);
                FileInfo[] items = fileListing.items;
                for (FileInfo fileInfo : items) {
                    System.out.println(fileInfo.key);
                }
            } catch (QiniuException e) {
                //捕获异常信息
                Response r = e.response;
                System.out.println(r.toString());
            }
        }
}