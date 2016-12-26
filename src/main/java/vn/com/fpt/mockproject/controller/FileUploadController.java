package vn.com.fpt.mockproject.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import vn.com.fpt.mockproject.dao.UserDAO;
import vn.com.fpt.mockproject.form.UploadForm;
import vn.com.fpt.mockproject.model.User;

@Controller
public class FileUploadController {
	@Autowired
	UserDAO userDAO;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == UploadForm.class) {

			// Ä�Äƒng kĂ½ Ä‘á»ƒ chuyá»ƒn Ä‘á»•i giá»¯a
			// cĂ¡c Ä‘á»‘i tÆ°á»£ng multipart thĂ nh byte[]
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
	}

	   // GET: Hiá»ƒn thá»‹ trang form upload
	   @RequestMapping(value = "/data", method = RequestMethod.GET)
	   public String uploadMultiFileHandler(Model model) {
		   List<User>list= userDAO.getAllUser(0, 9);
	       UploadForm myUploadForm = new UploadForm();
	       model.addAttribute("myUploadForm", myUploadForm);
	       model.addAttribute("list",list);
	       // Forward to "/WEB-INF/pages/uploadMultiFile.jsp".
	       return "userData";
	   }
	  
	   // POST: Sá»­ lĂ½ Upload
	   @RequestMapping(value = "/data", method = RequestMethod.POST)
	   public String uploadMultiFileHandlerPOST(HttpServletRequest request, //
	           Model model, //
	           @ModelAttribute("myUploadForm") UploadForm myUploadForm) {
	 
	       return this.doUpload(request, model, myUploadForm);
	 
	   }
	 
	   private String doUpload(HttpServletRequest request, Model model, //
	           UploadForm myUploadForm) {
	 
	       // ThÆ° má»¥c gá»‘c upload file.
	       String uploadRootPath = request.getServletContext().getRealPath("upload");
	       System.out.println("uploadRootPath=" + uploadRootPath);
	 
	       File uploadRootDir = new File(uploadRootPath);
	       //
	       // Táº¡o thÆ° má»¥c gá»‘c upload náº¿u nĂ³ khĂ´ng tá»“n táº¡i.
	       if (!uploadRootDir.exists()) {
	           uploadRootDir.mkdirs();
	       }
	       CommonsMultipartFile[] fileDatas = myUploadForm.getFileDatas();
	       //
	       List<File> uploadedFiles = new ArrayList<File>();
	       for (CommonsMultipartFile fileData : fileDatas) {
	 
	           // TĂªn file gá»‘c táº¡i Client.
	           String name = fileData.getOriginalFilename();
	           System.out.println("Client File Name = " + name);
	 
	           if (name != null && name.length() > 0) {
	               try {
	                   // Táº¡o file táº¡i Server.
	                   File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
	 
	                   // Luá»“ng ghi dá»¯ liá»‡u vĂ o file trĂªn Server.
	                   BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	                   stream.write(fileData.getBytes());
	                   stream.close();
	                   //
	                   uploadedFiles.add(serverFile);
	                   System.out.println("Write file: " + serverFile);
	               } catch (Exception e) {
	                   System.out.println("Error Write file: " + name);
	               }
	           }
	       }
	       model.addAttribute("uploadedFiles", uploadedFiles);
	       return uploadMultiFileHandler(model);
	   }
	 
	}