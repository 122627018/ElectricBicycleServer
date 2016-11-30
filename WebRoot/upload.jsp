<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
  
      
    <title>Struts2文件上传</title>  
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
    <!--  
    <link rel="stylesheet" type="text/css" href="styles.css">  
    -->  
  
  </head>  
    
  <body>  
   <center>  
    <h1>Struts 2完成上传</h1>  
      <form action="android/up_head" method="post" enctype="multipart/form-data">  
        <table>  
            <tr>  
                <td>文件名称:</td>  
                <td><input type="text" name="fileName" ></td>  
            </tr>  
            
             <tr>  
                <td>用户id:</td>  
                <td><input type="text" name="userid" ></td>  
            </tr>  
            <tr>  
                <td>上传文件:</td>  
                <td><input type="file" name="file"></td>  
            </tr>  
            <tr>  
                <td><input type="submit" value="上传"></td>  
                <td><input type="reset"></td>  
            </tr>  
        </table>  
      </form>  
  </center>  
  </body>  
</html>