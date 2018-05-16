package board;
import com.opensymphony.xwork2.ActionSupport;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.net.URLEncoder;

public class viewAction extends ActionSupport{
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private boardVO paramClass = new boardVO();
	private boardVO resultClass = new boardVO();
	
	private int currentPage;
	
	private int no;
	private String password;
	
	private String fileUploadPath = "C:\\uploads";
	


	private InputStream inputStream;
	private String contentDisposition;
	private long contentLength;

	// �깮�꽦�옄
	public viewAction() throws IOException {

		reader = Resources.getResourceAsReader("sqlMapConfig.xml"); // sqlMapConfig.xml �뙆�씪�쓽 �꽕�젙�궡�슜�쓣 媛��졇�삩�떎.
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader); // sqlMapConfig.xml�쓽 �궡�슜�쓣 �쟻�슜�븳 sqlMapper 媛앹껜 �깮�꽦.
		reader.close();
	}
	public String execute() throws Exception {

		// �빐�떦 湲��쓽 議고쉶�닔 +1.
		paramClass.setNo(getNo());
		sqlMapper.update("updateReadHit", paramClass);

		// �빐�떦 踰덊샇�쓽 湲��쓣 媛��졇�삩�떎.
		resultClass = (boardVO) sqlMapper.queryForObject("selectOne", getNo());

		return SUCCESS;
	}

	// 泥⑤� �뙆�씪 �떎�슫濡쒕뱶
	public String download() throws Exception {

		// �빐�떦 踰덊샇�쓽 �뙆�씪 �젙蹂대�� 媛��졇�삩�떎// �븳以꾩쭨由� �뜲�씠�꽣
		resultClass = (boardVO) sqlMapper.queryForObject("selectOne", getNo());

		// �뙆�씪 寃쎈줈�� �뙆�씪紐낆쓣 file 媛앹껜�뿉 �꽔�뒗�떎.
		File fileInfo = new File(fileUploadPath + resultClass.getFile_savname());

		// �떎�슫濡쒕뱶 �뙆�씪 �젙蹂� �꽕�젙.
		setContentLength(fileInfo.length());
		setContentDisposition("attachment;filename="
				+ URLEncoder.encode(resultClass.getFile_orgname(), "UTF-8"));
		setInputStream(new FileInputStream(fileUploadPath
				+ resultClass.getFile_savname()));

		return SUCCESS;
	}

	// 鍮꾨�踰덊샇 泥댄겕 �뤌
	public String checkForm() throws Exception {

		return SUCCESS;
	}

	// 鍮꾨�踰덊샇 泥댄겕 �븸�뀡
	public String checkAction() throws Exception {

		// 鍮꾨�踰덊샇 �엯�젰媛� �뙆�씪誘명꽣 �꽕�젙.
		paramClass.setNo(getNo());
		paramClass.setPassword(getPassword());

		// �쁽�옱 湲��쓽 鍮꾨�踰덊샇 媛��졇�삤湲�.
		resultClass = (boardVO) sqlMapper.queryForObject("selectPassword",
				paramClass);

		// �엯�젰�븳 鍮꾨�踰덊샇媛� ��由щ㈃ ERROR 由ы꽩.
		if (resultClass == null)
			return ERROR;

		return SUCCESS;
	}
			
			
			

			
			
			
			

			public boardVO getParamClass() {
		return paramClass;
	}

	public void setParamClass(boardVO paramClass) {
		this.paramClass = paramClass;
	}

	public boardVO getResultClass() {
		return resultClass;
	}

	public void setResultClass(boardVO resultClass) {
		this.resultClass = resultClass;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
			
			
			
}
