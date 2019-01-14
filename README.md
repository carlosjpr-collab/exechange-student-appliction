*** Accès au serveur MySQL distant ***
mysql -h us-cdbr-iron-east-01.cleardb.net -u be304fcc9fbc70 -p
password : xxxxxxxx

*** Accès au serveur MySQL local ***

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
    <li> /service/university
        <ul>
            <li>
                GET : Return all universities
            </li>
            <li>
                PUT : Save a new university into the DB
            </li>
        </ul>
    </li>
    <li> /service/course?id=id_univ
       <ul>
           <li>
               GET : Return the courses for the university with id=id_univ
           </li>
        </ul>
    </li>
    <li> /service/application 
        <ul>
            <li> GET : Return all applications for the connected user/university or INSA</li>
            <li> PUT : Save a new application for the connected user <br>
                JSON RequestBody : <br>
                {  <br> 
                "idUniv" : "xxx (int) ", <br>
                "agreement" : "xxx (0 or 1)", <br>
                "status" : "xxxxx (string)" <br>
                } <br>
                Response : <br> "success" : true --> the application is stored, no problem occured <br>
                 "success": "false" --> the user has already applied to this university : application not stored
            </li>
            <li> POST : Update the status of an application <br>
                JSON RequestBody : <br>
                { <br>
                "type" : "response", <br>
                "idApplication" : "xxx (int)", <br>
                "idUser" : "xxx(int)", <br>
                "response" : "OK" or "NOK" <br>
                } <br>
                Response : TODO
            </li>
        </ul>
      </li>
  </ul>
</div>
