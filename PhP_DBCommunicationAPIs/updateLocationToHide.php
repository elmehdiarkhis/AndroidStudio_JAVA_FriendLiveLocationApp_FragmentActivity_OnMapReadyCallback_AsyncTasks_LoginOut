<?php

    // echo "hello";

    $out = "";

    $servername = 'localhost';
    $username = 'newUser';
    $password = 'pass';
    $databasename = 'locationDb';
       
    $conn = new mysqli($servername, $username, $password,$databasename);

    if($conn->connect_error){
        $out="erreur de conn";
    }else{
  


        $req = "SELECT id FROM Locations WHERE nom='$_POST[nom]' AND prenom='$_POST[prenom]';";
        $res=$conn->query($req);

        if($res->num_rows != 0)
        {
            $req2 = "UPDATE Locations SET statut='hide' WHERE nom='$_POST[nom]' AND prenom='$_POST[prenom]'";          
        }

        $res2=$conn->query($req2);
        if($res2)
        {
            $out = "votre localisation a ete HIDE avec succes";
        }else{
            $out = "error requette php";
        }


    }

    echo $out;
?>




