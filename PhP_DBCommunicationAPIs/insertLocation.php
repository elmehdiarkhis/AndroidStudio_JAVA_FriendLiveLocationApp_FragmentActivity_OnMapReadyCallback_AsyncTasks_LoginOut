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
  
        $latitude = doubleval($_POST['latitude']);
        $longitude = doubleval($_POST['longitude']);
        $altitude = doubleval($_POST['altitude']);

        $req = "SELECT id FROM Locations WHERE nom='$_POST[nom]' AND prenom='$_POST[prenom]';";
        $res=$conn->query($req);

        if($res->num_rows != 0)
        {
            $req2 = "UPDATE Locations SET latitude=$latitude,longitude=$longitude,altitude=$altitude,pays='$_POST[pays]',ville='$_POST[ville]',province='$_POST[province]',postalCode='$_POST[postalCode]',statut='show' WHERE nom='$_POST[nom]' AND prenom='$_POST[prenom]'";   

            $res2=$conn->query($req2);
            if($res2)
            {
                $out = "votre localisation a ete partager avec succes";
            }    
        }

    }

    echo $out;
?>




