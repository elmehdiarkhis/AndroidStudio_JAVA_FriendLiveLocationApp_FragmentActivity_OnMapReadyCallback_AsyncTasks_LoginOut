<?php

$out = "";

//Connect to DataBase=======
$myDb = new mysqli('localhost','newUser','pass','crud'); 

if ($myDb -> connect_errno) {
    $out = "Failed to connect to MySQL: " . $myDb -> connect_error;
    exit();
}

// $_POST['userN'] = 'elmehdi';
// $_POST['passW'] = 'rhhr';

// echo $_POST['userN'];
// echo $_POST['passW'];

//Requette========
$req = "SELECT email FROM User WHERE userName='$_POST[userN]'= AND pass='$_POST[passW]';";        
$resultat = $myDb->query($req);

if($resultat->num_rows != 0)
{
    $membre = $resultat->fetch_assoc();
    $out = "Votre email est : ".$membre['email'];
}
//=========


echo $out;
     
    
?>