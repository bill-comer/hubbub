<html>
<head>
  <title>Search HubBub</title>
  <meta content="main" name="layout">
</head>

<body>
    <div class="nav">
            <span class="menuButton">  <g:link action='list'>List</g:link></span>
            <span class="menuButton">  <g:link action='search'>Search Again</g:link></span>
        </div>
        
  <formset>
    <legend>Search for Friends</legend>
     
    <g:form action="results">
      <label for="userId">User Id</label>
      <g:textField name="userId"/>
      
      <g:submitButton name="search" value="Search" />
    </g:form>
  </formset>
</body>
</html>