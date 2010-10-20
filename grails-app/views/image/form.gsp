<html>
  <head>
    <title>Upload Photo</title>
    <meta name="layout" content="main" />
  </head>
  
  <body>
    <h1>Upload Photo</h1>
    <g:uploadForm action="upload">
      <g:select name="userId" from="${userList}"
         optionKey="userId" optionValue="userId" />
    <p/>
       Photo: <input name="photo" type="file" />
       <g:submitButton name="upload" value="Upload" />
      
    </g:uploadForm>
    
  </body>
</html>