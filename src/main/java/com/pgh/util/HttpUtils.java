package com.pgh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.net.URL;  
import java.net.URLConnection;  
import java.net.URLEncoder;  


/**
 * http请求
 * 
 * @author penghz
 * 
 */
public class HttpUtils
{
	protected  static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
    /**
     * post请求 ，超时默认30秒
     * 
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params)
            throws IOException
    {
    	if(params==null)params=new HashMap<String, String>();
//    	setAuthorityParams(params);
        return post(url, params, 120,false);
    }
    
    
    /**
     * 带签名的post请求
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String postWithAuthority(String url, Map<String, String> params)
            throws IOException
    {
    	if(params==null)params=new HashMap<String, String>();
//    	setAuthorityParams(params);
        return post(url, params, 120,false);
    }
    
    /**
     * 带签名的post请求
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String postWithAuthority(String url, Map<String, String> params,int timeout)
            throws IOException
    {
    	if(params==null)params=new HashMap<String, String>();
//    	setAuthorityParams(params);
        return post(url, params, timeout,false);
    }
    

    /**
     * post请求
     * 
     * @param url
     * @param params
     * @param timeout
     *                超时时间，秒
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params,
            int timeout) throws IOException
    {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout",
                timeout * 1000);
        httpclient.getParams().setBooleanParameter(
                "http.protocol.expect-continue", false);
        String retVal = "";
        try
        {
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            if (params != null)
            {
                for (Map.Entry<String, String> param : params.entrySet())
                {
                    formparams.add(new BasicNameValuePair(param.getKey(), param
                            .getValue()));
                }
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
                    HTTP.UTF_8);
            HttpPost httppost = new HttpPost(url);
            setHeaderInfo(httppost);
            httppost.setEntity(entity);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            retVal = new String(httpclient.execute(httppost, responseHandler)
                    .getBytes(HTTP.ISO_8859_1), HTTP.UTF_8);
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    
   
    /**
     * post请求
     * 
     * @param url
     * @param params
     * @param timeout
     *                超时时间，秒
     * @param isParseReturn
     *                是否转码返回值
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params,
            int timeout,boolean isParseReturn) throws IOException
    {
    	StopWatch clock = new StopWatch();
    	clock.start();
    	
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout",
                timeout * 1000);
        httpclient.getParams().setBooleanParameter(
                "http.protocol.expect-continue", false); 
        String retVal = "";
        try
        {
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            if (params != null)
            {
                for (Map.Entry<String, String> param : params.entrySet())
                {
                    formparams.add(new BasicNameValuePair(param.getKey(), param
                            .getValue()));
                }
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
                    HTTP.UTF_8);
            HttpPost httppost = new HttpPost(url);
            setHeaderInfo(httppost);
            httppost.setEntity(entity);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            if(isParseReturn){
                retVal = new String(httpclient.execute(httppost, responseHandler)
                    .getBytes(HTTP.ISO_8859_1), HTTP.UTF_8);
            }else{
                retVal = httpclient.execute(httppost, responseHandler).toString();
            }
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
        	 throw e;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			 throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 throw e;
		}
        finally{
            httpclient.getConnectionManager().shutdown();
        }
        if(logger.isDebugEnabled()){
        logger.debug(String.format("===request info===[waiting:%s ms;url:%s;params:%s;reponse:%s]", 
        		clock.getTime(), url, params, retVal));
        }
        return retVal;
    }
    
    /**
     * 设置头信息
     * @param httppost
     */
    private static void setHeaderInfo(HttpPost httppost){
//    	if(ThreadLocals.getHeaderInfo()!=null && ThreadLocals.getHeaderInfo().containsKey("Referer")){
//    		httppost.addHeader("Referer", ThreadLocals.getHeaderInfo().get("Referer"));
//    	}
//    	if(ThreadLocals.getHeaderInfo()!=null && ThreadLocals.getHeaderInfo().containsKey("Cookie")){
//    		httppost.addHeader("Cookie", ThreadLocals.getHeaderInfo().get("Cookie"));
//    	}
    }
    
    /**
     * 设置认证参数
     * @param params
     */
//	public static void setAuthorityParams(Map<String, String> params) {
//		try {
//			String req_dateTime_str = String.valueOf(new Date().getTime());
//			String sign_key = SignUtils.getSign_key();
//			String sign = SignUtils.getSign(req_dateTime_str, sign_key);
//			params.put(Constants.REQ_DATETIME, req_dateTime_str);
//			params.put(Constants.SIGN_VAR, sign);
//			SystemUser user = SessionUtils.getCurrentLoginUser();
//			if (user != null) {
//				user.setUserMenuMap(null);
//				user.setPassword(null);
//				user.setSalt(null);
//				params.put("_userInfo", JSON.toJSONString(user));
//			}
//			String moduleId = ThreadLocals.getModuleId();
//			if(StringUtils.isNotBlank(moduleId)){
//				params.put("sys_menu_moduleId", moduleId);
//			}
//		} catch (Exception e) {
//			logger.error("setAuthorityParams error:", e);
//		}
//	}
    
	/**
	 * 
	 * @param url 地址
	 * @param json 数据
	 * @param timeout 超时
	 * @param objects 参数
	 * @return
	 * @throws IOException
	 */
	public static String postJson(String url, String json, int timeout,
			Object... objects) throws IOException {
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setIntParameter("http.socket.timeout",
				timeout * 1000);
		httpclient.getParams().setBooleanParameter(
				"http.protocol.expect-continue", false);
		String retVal = "";
		try {
			String encoding = HTTP.UTF_8;
			if (objects != null && objects.length > 0) {
				encoding = objects[0].toString();
			}
			HttpPost httppost = new HttpPost(url);
			StringEntity params = new StringEntity(json, encoding);
			httppost.addHeader("content-type", "application/json");
			httppost.setEntity(params);
			setHeaderInfo(httppost);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			if (objects == null || objects.length == 0) {
				retVal = new String(httpclient.execute(httppost,
						responseHandler).getBytes(HTTP.ISO_8859_1), HTTP.UTF_8);
			} else if (objects != null && "utf-8".equals(objects[0])) {
				retVal = httpclient.execute(httppost, responseHandler);
			} else if (objects != null && "gb2312".equals(objects[0])) {
				retVal = new String(httpclient.execute(httppost,
						responseHandler).getBytes("iso-8859-1"), "gb2312");
			} else {
				retVal = new String(httpclient.execute(httppost,
						responseHandler).getBytes(), HTTP.UTF_8);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return retVal;
	}

    /**
     * get请求
     * 
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String get(String url, Map<String, String> params)
            throws IOException
    {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", 100000);
        String retVal = "";
        try
        {
            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            if (params != null)
            {
                for (Map.Entry<String, String> param : params.entrySet())
                {
                    qparams.add(new BasicNameValuePair(param.getKey(), param
                            .getValue()));
                }
            }
            String paramstr = URLEncodedUtils.format(qparams, HTTP.UTF_8);
            if (StringUtils.isNotEmpty(paramstr))
            {
                url = url + "?" + paramstr;
            }
            HttpGet httpget = new HttpGet(url);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            retVal = httpclient.execute(httpget, responseHandler);
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }

    /**
     * get请求
     * 
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String get(String url, Map<String, String> params,
            String charset) throws IOException
    {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", 100000);
        String retVal = "";
        try
        {
            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            if (params != null)
            {
                for (Map.Entry<String, String> param : params.entrySet())
                {
                    qparams.add(new BasicNameValuePair(param.getKey(), param
                            .getValue()));
                }
            }
            String paramstr = URLEncodedUtils.format(qparams, charset);
            if (StringUtils.isNotEmpty(paramstr))
            {
                url = url + "?" + paramstr;
            }
            HttpGet httpget = new HttpGet(url);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            retVal = httpclient.execute(httpget, responseHandler);
            retVal = new String(retVal.getBytes(HTTP.ISO_8859_1), charset);
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }

    /**
     * 得到访问url的返回状态(200正常)
     * 
     * @param url
     *                :访问地址
     * @param timeOut
     *                :设置超时时间秒
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static int getPostRetStatu(String url, Integer timeOut)
    {
        HttpResponse response = null;
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setIntParameter("http.socket.timeout",
                    timeOut * 1000);
            httpclient.getParams().setBooleanParameter(
                    "http.protocol.expect-continue", false);
            /**
             * 此处使用get请求,因为一些网站屏蔽了post请求eg:baidu
             */
            HttpGet httpget = new HttpGet(url);
            response = httpclient.execute(httpget);
        }
        catch (Exception e)
        {
            // e.printStackTrace();
        	logger.error("",e);
            return 404;
        }
        return response.getStatusLine().getStatusCode();
    }

  
    /**
     * @Description:获取参数路径
     * @param params
     * @return
     * @author Alvin.zengqi
     * @date 2011-6-27 下午08:55:16
     */
    public static String getParamsPath(Map<String, String> params)
    {
        String url = "";
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        // String paramstr = "";
        if (params != null)
        {
            for (Map.Entry<String, String> param : params.entrySet())
            {
                qparams.add(new BasicNameValuePair(param.getKey(), param
                        .getValue()));
                // paramstr = paramstr+param.getKey()+"="+param.getValue()+"&";
            }
            // paramstr = paramstr + "randomCode="+new Date().toString();
        }
        String paramstr = URLEncodedUtils.format(qparams, HTTP.UTF_8);
        if (StringUtils.isNotEmpty(paramstr))
        {
            url = url + "?" + paramstr;
        }
        return url;
    }
    
    /**
     * get请求
     * @param url
     * @param params
     * @param timeout 超时时间，秒
     * @param charset 编码方式
     * @return
     * @throws Exception
     */
    public static String get(String url, Map<String, String> params, int timeout, String charset) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        String retVal = "";
        try {
            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    qparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
            }
            String paramstr = URLEncodedUtils.format(qparams, charset);
            if (StringUtils.isNotEmpty(paramstr)) {
                url = url + "?" + paramstr;
            }
            HttpGet httpget = new HttpGet(url);
            
            HttpResponse resp = httpclient.execute(httpget);
            retVal = EntityUtils.toString(resp.getEntity(), charset);
        } catch (IOException e) {
            throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    /**
     * post请求
     * @param url
     * @param params
     * @param timeout 超时时间，秒
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params, int timeout, String charset) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        String retVal = "";
        try {
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    formparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, charset);
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(entity);
            HttpResponse resp = httpclient.execute(httppost);
            retVal = EntityUtils.toString(resp.getEntity(), charset);
        } catch (IOException e) {
            throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    
    
    
    
    
    public static String sendGet(String url, String charset) {  
        String result = "";  
        BufferedReader in = null;  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection connection = realUrl.openConnection();  
            // 设置通用的请求属性  
            connection.setRequestProperty("accept", "*/*");  
            connection.setRequestProperty("connection", "Keep-Alive");  
            connection.setRequestProperty("user-agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 建立实际的连接  
            connection.connect();  
            // 定义 BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream(), charset));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送GET请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        // 使用finally块来关闭输入流  
        finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (Exception e2) {  
                e2.printStackTrace();  
            }  
        }  
        return result;  
    }  
  
    /**  
     * POST请求，字符串形式数据  
     * @param url 请求地址  
     * @param param 请求数据  
     * @param charset 编码方式  
     */  
    public static String sendPostUrl(String url, String param, String charset) {  
  
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            // 发送请求参数  
            out.print(param);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(  
                    conn.getInputStream(), charset));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送 POST 请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        // 使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  
    /**  
     * POST请求，Map形式数据  
     * @param url 请求地址  
     * @param param 请求数据  
     * @param charset 编码方式  
     */  
    public static String sendPost(String url, Map<String, String> param,  
            String charset) {  
  
        StringBuffer buffer = new StringBuffer();  
        if (param != null && !param.isEmpty()) {  
            for (Map.Entry<String, String> entry : param.entrySet()) {  
                buffer.append(entry.getKey()).append("=")  
                        .append(URLEncoder.encode(entry.getValue()))  
                        .append("&");  
  
            }  
        }  
        buffer.deleteCharAt(buffer.length() - 1);  
  
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            // 发送请求参数  
            out.print(buffer);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(  
                    conn.getInputStream(), charset));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送 POST 请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        // 使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  
  
}