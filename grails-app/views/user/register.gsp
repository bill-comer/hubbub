<html>
  <head>
    <title>Register new user</title>
    <meta name="layout" content="main" />
  </head>
  
  <body>
    <h1>Register New User</h1>
    
    <g:hasErrors>
      <div class="errors">
        <g:renderErrors beans="${user}" as list />
      </div>
    </g:hasErrors>
    
    <div class="nav">
            <span class="menuButton">  <g:link action='list'>List</g:link></span>
        </div>
        
    <g:form action="register">
      <d1>
        <dt>User Id</dt>
        <dd><g:textField name="userId" value="${user?.userId}"/></dd>
        <br/>
        <dt>Password</dt>
        <dd><g:textField name="password" value="${user?.password}"/></dd>
      
        <br/>
        <dt>Full Name</dt>
        <dd><g:textField name="profile.fullName" value="${user?.profile?.fullName}"/></dd>
      
        <br/>
        <dt>email</dt>
        <dd><g:textField name="profile.email" value="${user?.profile?.email}"/></dd>
        <br/>
        <dt>Bio</dt>
        <dd><g:textField name="profile.bio" value="${user?.profile?.bio}"/></dd>
        
        <dt><dd><g:submitButton name="register" value="Register"/></dd></dt>
      <!-- 
       -->
        
      </d1>
      
      
    </g:form>
    
  </body>
</html>