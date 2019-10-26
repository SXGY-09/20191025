package com.sxgy.sp26.action;

import java.io.File;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sxgy.sp26.domain.User;

@Controller
public class UploadController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping("/success")
	public String success() {
		return "success";
	}
	@RequestMapping("/failure")
	public String error() {
		return "failure";
	}
	@RequestMapping("/upload1")
	public String upload1() {
		return "uploadForm";
	}
	@RequestMapping("register1")
	public String register1() {
		return "registerForm";
	}
	//上传文件会自动绑定到MultipartFile中
	@PostMapping("/upload")
	public String upload(HttpServletRequest request,
			@RequestParam("description") String description,
			@RequestParam("file") MultipartFile file) throws Exception {
		System.out.println("description="+description);
		//如果文件不为空，写入上传路径
		if(!file.isEmpty()) {
			String path=request.getServletContext().getRealPath("/upload/");
			System.out.println("path="+path);
			String filename=file.getOriginalFilename();
			File filePath=new File(path, filename);
			//判断路径是否存在，不存在就创建一个
			if(!filePath.getParentFile().exists()) {
				filePath.getParentFile().mkdirs();
			}
			//将上传文件保存到一个目标文件当中
			file.transferTo(new File(path+File.separator+filename));
			return "success";
		}
		return "failure";
	}
	@PostMapping("/register")
	public String register(HttpServletRequest request,
			@ModelAttribute User user,
			Model model) throws Exception {
		System.out.println("username="+user.getUsername());
		//如果文件不为空，写入上传路径
		if(!user.getHeadPortrait().isEmpty()) {
			String path=request.getServletContext().getRealPath("/upload/");
			System.out.println("path="+path);
			String filename=user.getHeadPortrait().getOriginalFilename();
			File filePath=new File(path, filename);
			//判断路径是否存在，不存在就创建一个
			if(!filePath.getParentFile().exists()) {
				filePath.getParentFile().mkdirs();
			}
			//将上传文件保存到一个目标文件当中
			user.getHeadPortrait().transferTo(new File(path+File.separator+filename));
			model.addAttribute("user",user);
			return "userInfo";
		}
		return "failure";
	}
	@RequestMapping(value="/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,
			@RequestParam("filename") String filename,
			@RequestHeader("User-Agent") String userAgent,
			Model model) throws Exception{
		//下载文件路径
		String path=request.getServletContext().getRealPath("/upload/");
		//构建file
		File file=new File(path+File.separator+filename);
		//ok:200
		BodyBuilder builder=ResponseEntity.ok();
		//内容长度
		builder.contentLength(file.length());
		//application/octet-stream 二进制流数据
		builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
		//使用URLDecoder.decode对文件名进行解码
		filename=URLEncoder.encode(filename, "UTF-8");
		//设置实际的相应文件名，告诉浏览器文件要用于下载和保存
		if(userAgent.indexOf("MSIE")>0) {
			//IE
			builder.header("Content-Disposition", "attachment; filename="+filename);
		}else {
			builder.header("Content-Disposition", "attachment; filename*=UTF-8''"+filename);
		}
		return builder.body(FileUtils.readFileToByteArray(file));
	}
}
