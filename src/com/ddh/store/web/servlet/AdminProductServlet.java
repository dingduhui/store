package com.ddh.store.web.servlet;

import java.io.File;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.ddh.store.domain.Category;
import com.ddh.store.domain.PageModel;
import com.ddh.store.domain.Product;
import com.ddh.store.service.CategoryService;
import com.ddh.store.service.ProductService;
import com.ddh.store.service.serviceImp.CategoryServiceImp;
import com.ddh.store.service.serviceImp.ProductServiceImp;
import com.ddh.store.utils.UUIDUtils;
import com.ddh.store.utils.UploadUtils;
import com.ddh.store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminProductServlet
 */
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findAllProductCatsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int curNum = Integer.parseInt(req.getParameter("num"));
		ProductService productService = new ProductServiceImp();
		PageModel pm = productService.findAllProductCatsWithPage(curNum);
		req.setAttribute("page", pm);
		return "/admin/product/list.jsp";
	}

	public String addProductUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		CategoryService categoryService = new CategoryServiceImp();
		List<Category> list = categoryService.findAllCats();
		req.setAttribute("allCats", list);
		return "/admin/product/add.jsp";
	}

	public String addProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 重组表单数据
		Map<String, String> map = new HashMap<String, String>();
		// 携带表单数据传入数据库
		Product product = new Product();
		try {
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
			List<FileItem> list = servletFileUpload.parseRequest(req);
			for (FileItem item : list) {
				if (item.isFormField()) {
					// 如果是普通对象存入map中
					map.put(item.getFieldName(), item.getString("utf-8"));
				} else {
					// 如果是上传流
					// 获取文件名称
					String oldfilename = item.getName();
					// 截取文件类型名字
					String newfilename = UploadUtils.getUUIDName(oldfilename);
					InputStream is = item.getInputStream();
					String realpath = getServletContext().getRealPath("/products/3/");
					String dir = UploadUtils.getDir(newfilename);
					String path = realpath + dir;
					File newfile = new File(path);
					if (!newfile.exists()) {
						newfile.mkdirs();
					}
					File finalfile = new File(newfile, newfilename);
					if (!finalfile.exists()) {
						finalfile.createNewFile();
					}
					// 创建对应的输入输出流
					OutputStream op = new FileOutputStream(finalfile);
					IOUtils.copy(is, op);
					IOUtils.closeQuietly(is);
					IOUtils.closeQuietly(op);
					map.put("pimage", "/products/3/" + dir + "/" + newfilename);
				}
			}
			BeanUtils.populate(product, map);
			product.setPid(UUIDUtils.getCode());
			product.setPdate(new Date());
			product.setPflag(0);
			//调用业务层存入上传的数据信息
			ProductService productService=new ProductServiceImp();
			productService.saveProduct(product);
			resp.sendRedirect("/store/AdminProductServlet?method=findAllProductCatsWithPage&num=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/product/add.jsp";
	}

}
