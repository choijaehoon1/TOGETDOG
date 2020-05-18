package com.togetdog.review;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.togetdog.community.FileCont;
import com.togetdog.filemng.FileVO;

/**
 * Servlet implementation class RevFileCont
 */
@WebServlet("/view/rfile.do")
public class RevFileCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private final Logger LOG = Logger.getLogger(FileCont.class);

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RevFileCont() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String savePath = "D:\\HR_20191130\\03_JSP\\daoWorkspace\\ZSOMAC\\WebContent\\img\\image_";
		//사이즈
		int size = 1024*1024*50;
		//Encoding
		String encType = "UTF-8";
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		File dirUpload = new File(savePath);
		if(dirUpload.exists() ==false) dirUpload.mkdir();
		
		try {
			MultipartRequest multi = new MultipartRequest(request,savePath,size,encType,new DefaultFileRenamePolicy());
			Enumeration<String> files = multi.getFileNames();
			
			
			while(files.hasMoreElements()){
				FileVO fileVO = new FileVO();
				String tmpFile = files.nextElement();
				if(multi.getOriginalFileName(tmpFile) == null || tmpFile.length()==0) continue;
				
				
				//원본파일
				String orgFileName = multi.getOriginalFileName(tmpFile);
				//저장파일
				String saveFileName = multi.getFilesystemName(tmpFile);
				//saveFilePath(저장경로) : savePath + saveFileName
				String saveFilePath = savePath + File.separator + saveFileName;
				
				LOG.debug("============");
				LOG.debug("orgFileName" + orgFileName);
				LOG.debug("saveFileName" + saveFileName);
				LOG.debug("savePath + saveFileName" + saveFilePath);
				LOG.debug("============");
				
				//파일사이즈
				File fileSize = new File(saveFilePath);
				int fileLongSize = (int) fileSize.length();	//byte임
				
				String ext= "";
				if(saveFileName.lastIndexOf(".")>0) {
					int dotIndex = saveFileName.lastIndexOf(".");
					ext = saveFileName.substring(dotIndex);
				}
				
				fileVO.setOrgNm(orgFileName);
				fileVO.setSaveNm(saveFileName);
				fileVO.setFileSize(fileLongSize);
				fileVO.setImgPath(savePath);
				fileVO.setExt(ext);
				
				//TODO:Session -> 등록자ID,수정자ID
				fileList.add(fileVO);
			}
			
			for(FileVO fvo:fileList) {
				LOG.debug("vo:"+fvo);
			}
			
		} catch(Exception e) {
			LOG.debug("============");
			LOG.debug("Exception:" + e.getMessage());
			LOG.debug("============");
		} 
		
			for(FileVO fvo:fileList) {
				LOG.debug("띠발것 니가 안나오고"+fvo);
			}
		
		//화면으로 전달
//		out.print(fileList);
		
		
		
		out.print(fileList.get(0).getOrgNm()+",");
		out.print(fileList.get(0).getSaveNm()+",");
		out.print(fileList.get(0).getImgPath()+",");
		out.print(fileList.get(0).getFileSize()+",");
		out.print(fileList.get(0).getExt()+",");
		
		/*
		request.setAttribute("fileList", fileList);
		String retPath = "/view/mypage_review_insert.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(retPath);
		dispatcher.forward(request, response);
		*/
	}

}
