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
    
    <d1>
      <g:uploadForm>
        <dt>User Id:</dt>
        <dd><g:textField name="userId" /></dd>
        
        <dt>Password:</dt>
        <dd><g:passwordField name="password" /></dd>
        <dt>(repeat Password)</dt>
        <dd><g:passwordField name="passwordRepeat" /></dd>
        
        <dt>date of Birth</dt>
        <dd><g:datePicker name="dob" precision="day" value="${then}" years="${1900..nowYear}"/></dd>
        
        <dt>Country:</dt>
        <dd><g:countrySelect name="country" 
              noSelection="['':'Choose your country...']" /></dd>
        
        <dt>Photo:</dt>
        <dd><input type="file" name="photo"/></dd>
        
        <dt>Timezone</dt>
        <dd><g:timeZoneSelect name="timezone" /></dd>
        
        <dt>Who introduced you to Hubbub?</dt>
        <dd>
          <g:select name="referrer"
            from="${com.grailsinaction.Profile.list()}"
            optionKey="id"
            optionValue="fullName"
            noSelection="${['null':'Please choose...']}"    
          />
        </dd>
        
        <dt>Spam me foreever:</dt>
        <dd>
          <g:checkBox name="spamMe" checked="true"/>
        </dd>
        
        <dt>Email format:</dt>
        <dd>
          <g:radioGroup name='emailFormat'
            labels="['Plain', 'HTML']"
            values="['P','H']"
            value="H">
            ${it.label} ${it.radio}
          </g:radioGroup>
        </dd>
        
        <dt>
          <g:actionSubmit action="register62" value="Register"/>
        </dt>
        <dd>
          <g:actionSubmit value="Cancel" action="cancelRegister"/>
        </dd>
        
      </g:uploadForm>
    </d1>
    
    <p>
      <g:link controller="post">Back to Hubbub</g:link>
    </p>
    
  </body>
</html>