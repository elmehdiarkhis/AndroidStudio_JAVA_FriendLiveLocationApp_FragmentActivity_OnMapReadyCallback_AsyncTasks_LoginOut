<?php


    $out = "";

    $servername = 'localhost';
    $username = 'newUser';
    $password = 'pass';
    $databasename = 'locationDb';
       
    $conn = new mysqli($servername, $username, $password,$databasename);

    if($conn->connect_error){
        $out="Ouchh!! erreur serveur :(";
    }else{
  
        $req = "SELECT * FROM Locations WHERE statut='show';";
        $res=$conn->query($req);

        if($res->num_rows != 0)
        {
            
            while ($ligne = $res->fetch_assoc()){
                $counter=0;
                foreach ($ligne as $colomnName => $value)
                {
                    if($colomnName=="id"){
                        $out .= "//".$value.","; 
                    }else{
                        if($counter%10==0){
                            $out .= $value; 
                        }else{
                            $out .= $value.","; 
                        }
                    } 
                     $counter++;             
                }
            }
        }
    }

    echo $out;
?>




