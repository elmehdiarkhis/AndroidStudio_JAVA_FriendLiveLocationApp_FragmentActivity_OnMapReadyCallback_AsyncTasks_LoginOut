<?php

    $out = "";

    $servername = 'localhost';
    $username = 'newUser';
    $password = 'pass';
    $databasename = 'locationDb';
       
    $conn = new mysqli($servername, $username, $password,$databasename);

    if($conn->connect_error){
        $out="";
    }else{
  
        // $out="connected:connected";

        $req = "SELECT nom,prenom FROM Locations WHERE userName='$_POST[userName]' AND pass='$_POST[pass]';";
        $res=$conn->query($req);

        if($res->num_rows != 0)
        {
            while ($ligne = $res->fetch_assoc()){
                $out = $ligne['nom'].":,:".$ligne['prenom'];
            }   
        }
        
    }

    echo $out;
?>




