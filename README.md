*** Accès au serveur MySQL distant ***
mysql -h us-cdbr-iron-east-01.cleardb.net -u be304fcc9fbc70 -p
password : xxxxxxxx

*** Accès à l'application en local ***
localhost:8080/...

*** Accès à l'application sur le cloud***
https://exchangesemester.cfapps.io/

*** Pour push l'application sur le cloud ***
    TODO


<h2> REST Api </h2>
<div>
  All RESTFul services accessible via /service/XXXX
  <ul>
    <li> /service/university (GET : Return all universities - PUT : Save a new university into the DB) </li>
    <li> /service/course?id=id_univ (GET : Return the courses for the university with id=id_univ) </li>
    <li> /service/application (GET : Return all applications for the connected user - PUT : Save a new application for the connected user </li>
  </ul>
</div>
