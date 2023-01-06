package com.hlw.edu.util;

import com.hlw.edu.dto.FileHolder;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @description: 文件处理工具类
 */
public class FileUtil {
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    /**
     * @description: 将CommonsMultipartFile转换成File类
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * @description: 处理文件，并将原上传文件保存到新位置
     */
    public static String generateNewFile(FileHolder fileHolder, String targetAddr){
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileHolder.getFileName());
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;

        try {
            //获取fileHolder中的上传文件的输入流
            InputStream is = fileHolder.getFile();
            //创建一个文件输入流
            FileOutputStream fos = new FileOutputStream(PathUtil.getFileBasePath() + relativeAddr);
            //创建一个缓冲区
            byte[] buffer = new byte[1024];
            //判断输入流中的数据是否已经读完的标识
            int length = 0;
            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
            while((length = is.read(buffer))>0){
                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                fos.write(buffer, 0, length);
            }
            //关闭输入流
            is.close();
            //关闭输出流
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回图片相对路径地址
        return relativeAddr;
    }

    /**
     * @description: 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     */
    public static String getRandomFileName() {
        //获取随机的五位数
        int ranNum = r.nextInt(89999)+10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr+ranNum;
    }

    /**
     * @description: 获取输入文件流的扩展名
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


    /**
     * @description: 创建目标路径所涉及到的目录
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getFileBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    /**
     * @description: storePath是文件的路径还是目录的路径，
     * 如果storePath是文件路径则删除该文件，
     * 如果storePath是目录路径则删除该目录下的所有文件
     */
    public static void deleteFileOrPath(String storePath){
        //获取全路径，路径有可能是目录路径，有可能是文件路径
        File fileOrPath = new File(PathUtil.getFileBasePath() + storePath);
        if (fileOrPath.exists()){
            if (fileOrPath.isDirectory()){
                File[] files = fileOrPath.listFiles();
                for (File file : files) {
                    file.delete();
                }
            }
            fileOrPath.delete();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // 创建文件流
        File file = new File("C:/Users/hp/Desktop/杨烁平-实习简历.pdf");
        InputStream is = new FileInputStream(file);
        FileHolder fileHolder = new FileHolder(file.getName(),is);
        String dest = PathUtil.getHomeworkFilePath(1L);
        String newAddr = FileUtil.generateNewFile(fileHolder,dest);
        System.out.println(newAddr);
    }
}
