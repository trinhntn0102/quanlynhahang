package com.qlnh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest request;
    /**
     * Đọc chuỗi giá trị của tham số
     * @param name tên tham số
     * @param defaultValue giá trị mặc định
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public String getString(String name, String defaultValue){
        String requestValue = request.getParameter(name);
        return requestValue != null? requestValue : defaultValue;
    }
    /**
     * Đọc số nguyên giá trị của tham số
     * @param name tên tham số
     * @param defaultValue giá trị mặc định
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
    LAB4: SPRING BEANS
    SOF3021 – LẬP TRÌNH JAVA 5 TRANG 2
     */
    public int getInt(String name, int defaultValue){
        String requestValue = getString(name, String.valueOf(defaultValue));
        return Integer.parseInt(requestValue);
    }
    /**
     * Đọc số thực giá trị của tham số
     * @param name tên tham số
     * @param defaultValue giá trị mặc định
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public double getDouble(String name, double defaultValue){
        String requestValue = getString(name, String.valueOf(defaultValue));
        return Double.parseDouble(requestValue);
    }

    /**
     * Đọc giá trị boolean của tham số
     * @param name tên tham số
     * @param defaultValue giá trị mặc định
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public boolean getBoolean(String name, boolean defaultValue){
        String requestValue = getString(name, String.valueOf(defaultValue));
        return Boolean.parseBoolean(requestValue);
    }


    public Date getDate(String name, String pattern){
        String value = getString(name,"");
        try{
            return new SimpleDateFormat(pattern).parse(value);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Lưu file upload vào thư mục
     * @param file chứa file upload từ client
     * @param path đường dẫn tính từ webroot
     * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
     * @throws RuntimeException lỗi lưu file
     */
    public File save(MultipartFile file, String path){
        if(!file.isEmpty()){
            File dir = new File(request.getServletContext().getRealPath(path)); //getRealPath trả về đường dẫn
            if(!dir.exists()){
                dir.mkdir(); //Multiple directories
            }
            try{
                File savedFile = new File(dir, file.getOriginalFilename());
                file.transferTo(savedFile);
                return savedFile;
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }else{
            return null;
        }
    }
}
