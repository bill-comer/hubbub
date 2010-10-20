<html>
<head>
  <title>Timeline for ${user?.profile?.fullName }</title>
  <meta content="main" name="layout">
</head>

<body>  
  <g:if test="${flash.message}">
    <div class="flash">
      ${flash.message }
    </div>
  </g:if>
  
  <g:if test="${user?.profile == null}">
    <div class="flash">
          User Unknown
    </div>
  </g:if>


  <div class="nav">
            <span class="menuButton">  <g:link controller="user" action='list'>User List</g:link></span>
  </div>
        
  <g:if test="${user?.profile}">      
  <div id="newPost">
    <h3>
      What's up at the mo ${user?.profile?.fullName}
    </h3>
    
    <p>
      <g:form action="addPost" id="${params.id}" >
        <g:textArea id='postContent' name='content' rows="3" cols="50" />
        <br/>
        <g:submitButton name="post" value="new Post"/>
      </g:form>
    </p>
  </div>      
        
  <h1>
    <g:if test="${user?.profile}">
       Timeline for ${user?.profile?.fullName}
    </g:if>
  </h1>
  
  <div class="allPosts">
   <g:each in="${user?.posts}" var="post">
     <div class="postEntry">
       <div class="postText">
         ${post?.content}
       </div>
       <div class="postDate">
         ${post?.dateCreated}
       </div>
     </div>
   
   </g:each>
  </div>
  </g:if>
 
  
</body>
</html>