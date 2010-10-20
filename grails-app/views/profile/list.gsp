
<%@ page import="com.grailsinaction.Profile" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'profile.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="fullName" title="${message(code: 'profile.fullName.label', default: 'Full Name')}" />
                        
                            <g:sortableColumn property="bio" title="${message(code: 'profile.bio.label', default: 'Bio')}" />
                        
                            <g:sortableColumn property="homepage" title="${message(code: 'profile.homepage.label', default: 'Homepage')}" />
                        
                            <g:sortableColumn property="email" title="${message(code: 'profile.email.label', default: 'Email')}" />
                        
                            <g:sortableColumn property="photo" title="${message(code: 'profile.photo.label', default: 'Photo')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${profileInstanceList}" status="i" var="profileInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${profileInstance.id}">${fieldValue(bean: profileInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: profileInstance, field: "fullName")}</td>
                        
                            <td>${fieldValue(bean: profileInstance, field: "bio")}</td>
                        
                            <td>${fieldValue(bean: profileInstance, field: "homepage")}</td>
                        
                            <td>${fieldValue(bean: profileInstance, field: "email")}</td>
                        
                        <!-- 
                            <td>${fieldValue(bean: profileInstance, field: "photo")}</td>
                         -->
                            <td>
                              <img width="100" height="100" 
                                src="<g:createLink action='showPhoto' id='${profileInstance.id}'/>"/>
                            </td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${profileInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
