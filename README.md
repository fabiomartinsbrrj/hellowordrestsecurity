helloworld: Helloworld Example
===============================
Author: Fabio Martins  
Level: Avançado  
Technologies: Servlet, JAXB  
  
Target Product: JBoss EAP  
Source: <https://github.com/fabiomartinsbrrj/hellowordrestsecurity/>  

O que é?
-----------

Exemplo tirado do github do usuario jboss-developer que mescla a funcionalidade do resteasy com segurança RODANDO no EAP 6


System requirements
-------------------
Red Hat JBoss Enterprise Application Platform 6.1 ou superior. 

Java 6.0 (Java SDK 1.6) or superior, Maven 3.0 ou superior.
 
Configurar
-------------------
Configurar no JBOSS no standalone.xml 

        <subsystem xmlns="urn:jboss:domain:security:1.2">
            <security-domains>
                <security-domain name="scorpios-web-auth" cache-type="default">
                    <authentication>
                        <login-module code="com.scipa.scorpios.security.MeuSimplesUsernamePasswordLoginModule" flag="required"/>
                    </authentication>
                </security-domain>
			....
        </subsystem>
        
Access the application 
---------------------
URL: <http://localhost:8080/jboss-helloworld/services/companies/>. 

ou pela linha de comando

 curl -H "Accept: application/json" -u 'colocar usuario:qualquer senha' -X GET http://localhost:8080/jboss-helloworld/services/companies/tasks/1

 curl -i -u 'colocar usuario:qualquer senha' -H "Content-Length: 0" -X POST http://localhost:8080/jboss-helloworld/services/companies/tasks/task1
  
Se usar o usuario msilva vai conseguir acessar todos os serviços da role user_role

Se usar o usuario rmacedo vai conseguir acessar todos os serviços da role admin_role

Qualquer outro usuario vai ser negado o acesso


