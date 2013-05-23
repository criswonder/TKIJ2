package test.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 简易的 HTTP 文件上传类，基于 socket api 实现
 * 
 * @author 王小波 2013-4-1
 */
public class HttpSimpleUpload {

	public static void main(String[] args) {
		Map<String, String> param = new HashMap<String, String>();
//		param.put("lon", "120.341331234");
//		param.put("lat", "30.1234567");
//		param.put("photoTime", "2013-09-20 12:33:44");
//		param.put("photoAddress", "中国");
//		param.put("description", "描述信息");

		String result = upload("http://clw.17car.com.cn/car/reportcase.action?userid=44&carid=6&jingdu=56&weidu=46", new File("F:/desert.jpg"), param, "");
//		  String result = upload("http://192.168.16.30:8080/upload_img.htm", new File("F:/upload_test_main2/test222.jpg"), param, "57A918A621F84F3EC8512F1585869112");
//	      String result = upload("http://192.168.16.239:8080/upload_img.htm", new File("F:/upload_test_main2/test222.jpg"), param, "57A918A621F84F3EC8512F1585869112");
//	      String result = upload("http://localhost:8080/travel/upload_img.htm", new File("F:/upload_test_main2/test222.jpg"), param, "57A918A621F84F3EC8512F1585869112");
		System.out.println(result);
		System.out.println("11");
	}

	/**
	 * 文件上传
	 * 
	 * @param uploadUrl
	 *            上传路径完整URL
	 * @param file
	 *            待上传的文件
	 * @param param
	 *            上传时附加的参数集合
	 * @param sessionId
	 *            cookie中的sessionId
	 * 
	 * @return 返回相应格式的数据
	 */
	public static String upload(String uploadUrl, File file, Map<String, String> param, String sessionId) {
		if (uploadUrl == null || file == null) {
			throw new IllegalArgumentException("参数有误");
		}
		try {
			HttpSimpleUpload uploader = new HttpSimpleUpload(uploadUrl, file, param, sessionId);
			String out = uploader.doUpload();
			int index = out.indexOf("\r\n\r\n");
			if (index != -1) {
				out = out.substring(index).trim();
			}
			return out;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private HttpSimpleUpload(String uploadUrl, File file, Map<String, String> param, String sessionId) {
		this.uploadUrl = uploadUrl;
		this.param = param;
		this.file = file;
		this.sessionId = sessionId;
	}

	// 上传地址完整URL路径
	private String uploadUrl;

	// 上传参数
	private Map<String, String> param;

	// 上传文件
	private File file;

	// 发送请求时带的 sessionId
	private String sessionId;

	private FileInputStream fis;

	// 包的总大小
	private Integer length = 0;

	// 字符串缓冲区
	private StringBuilder sb;

	// 输出流
	private OutputStream os;

	// 上传完整 url
	private String uploadURL;

	// 主机名
	private String host;

	// 端口号
	private String port;

	// 分隔符
	private static final String boundary = "---------------------------7dd10d361100da";

	private String doUpload() throws Exception {
		initAddressParam(uploadUrl);
		Socket socket = new Socket(host, Integer.valueOf(port));
		socket.setSoTimeout(30000);
		socket.setKeepAlive(true);
		socket.setSendBufferSize(262144);
		socket.setReceiveBufferSize(262144);
		os = socket.getOutputStream();
		sb = new StringBuilder();

		if (param != null && param.size() > 0) {
			for (Map.Entry<String, String> entry : param.entrySet()) {
				fillParam(entry.getKey(), entry.getValue());
			}
		}
		fillFileInfo();
		String head = initHead(uploadUrl) + sb.toString();
		os.write(head.getBytes("utf-8"));
		uploadFile(file);
		os.flush();
		socket.shutdownOutput();

		BufferedReader bis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		sb = new StringBuilder();
		String tmp = null;
		while ((tmp = bis.readLine()) != null) {
			sb.append(tmp + "\r\n");
		}
		socket.close();
		return sb.toString();

	}

	// 地址处理
	private void initAddressParam(String url) {
		if (!url.startsWith("http://")) {
			throw new IllegalArgumentException("url格式不正确");
		}
		url = url.substring("http://".length());
		int index = url.indexOf("/");
		uploadURL = url.substring(index);
		host = url.substring(0, index);
		port = "80";
		int portIndex = host.indexOf(":");
		if (portIndex != -1) {
			port = host.substring(portIndex + 1);
			host = host.substring(0, portIndex);
		}
	}

	// 填充参数信息
	private void fillParam(String key, String value) throws Exception {
		StringBuilder tmp = new StringBuilder();
		tmp.append("--" + boundary + "\r\n");
		tmp.append("Content-Disposition: form-data; name=\"" + key + "\"" + "\r\n");
		tmp.append("\r\n");
		tmp.append(value);
		tmp.append("\r\n");

		length += (tmp.toString().getBytes("utf-8").length);
		sb.append(tmp.toString());
	}

	// 填充文件信息
	private void fillFileInfo() throws Exception {
		StringBuilder tmp = new StringBuilder();
		tmp.append("--" + boundary + "\r\n");
		tmp.append("Content-Disposition: form-data; name=\"_file_uploader\"; filename=\"" + file.getName() + "\"" + "\r\n");
		tmp.append("Content-Type: image/pjpeg" + "\r\n");
		tmp.append("\r\n");

		length += tmp.toString().getBytes("UTF-8").length;
		sb.append(tmp.toString());

		this.fis = new FileInputStream(file);
		length += fis.available();
		length += ("\r\n--" + boundary + "--\r\n").getBytes().length;
	}

	// 写入文件流
	private void uploadFile(File file) throws Exception {
		int len = 0;
		byte[] buf = new byte[5000];
		while ((len = fis.read(buf)) != -1) {
			os.write(buf, 0, len);
		}
		os.write(("\r\n--" + boundary + "--\r\n").getBytes());
		fis.close();
	}

	// 初始化HTTP 头信息
	private String initHead(String url) {
		StringBuffer header = new StringBuffer();
		header.append("POST " + uploadURL + " HTTP/1.1\r\n");
		header.append("Host: " + host + ":" + port + "\r\n");
		header.append("Accept: */*\r\n");
		header.append("Accept-Language: zh-cn\r\n");
		header.append("Accept-Charset: utf-8\r\n");
//		header.append("Cookie: JSESSIONID=" + sessionId + "\r\n");
		header.append("Content-Type: multipart/form-data; boundary=" + boundary + "\r\n");
		header.append("Content-Length: " + String.valueOf(length) + "\r\n");
		header.append("\r\n");
		return header.toString();
	}

}
