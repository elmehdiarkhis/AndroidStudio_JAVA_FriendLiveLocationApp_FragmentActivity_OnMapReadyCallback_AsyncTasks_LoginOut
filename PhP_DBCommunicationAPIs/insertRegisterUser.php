<?php

    $out = "";

    $servername = 'localhost';
    $username = 'newUser';
    $password = 'pass';
    $databasename = 'locationDb';
       
    $conn = new mysqli($servername, $username, $password,$databasename);

    if($conn->connect_error){
        $out="erreur de conn";
    }else{

 

        $photo = intval($_POST['photo']);

        
        $req = "SELECT id FROM Locations WHERE userName='$_POST[userName]';";
        $res=$conn->query($req);

        if($res->num_rows == 0)
        {
            
            $req2 = "INSERT INTO Locations (nom,prenom,photo,userName,pass) VALUES ('$_POST[nom]','$_POST[prenom]',$photo,'$_POST[userName]','$_POST[pass]')"; 

            $res2=$conn->query($req2);
            if($res2)
            {
                $out = "Register: registerSucces";
            }       
        }else{
            $out = "Register: Ce UserName est deja pris!!";
        }


    }

    echo $out;

?>




