<html>
<head>
  <title>Search Results</title>
  <meta content="main" name="layout">
</head>

<body>
    <div class="nav">
            <span class="menuButton">  <g:link action='list'>List</g:link></span>
            <span class="menuButton">  <g:link action='search'>Search Again</g:link></span>
        </div>
        
  <h1>Search results</h1>
  <p>Search  records for items matching <em>${term}</em>.
  Found <strong>${users.size()}</strong> hits
  </p>
  <ul>
    <g:each var="user" in="${users}">
      <li>${user.userId}</li>
    </g:each>
  </ul>
  
  <g:link action='search'>Search Again</g:link>
</body>
</html>