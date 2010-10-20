<html>
  <head>
    <title>Profile Photo</title>
    <meta name="layout" content="main" />
  </head>
  
  <body>
    
    <div class="profilePic">
      <g:if test="${profile.photo}">
        <img src="<g:createLink controller='user' action='profile' id='${userId}'"/>
      </g:if>
      <p>Profile for <strong>${profile.fullName}</strong></p>
      <p>Bio: ${profile.bio}</p>
    </div>
    
    
  </body>
</html>