<?php

$link = mysql_connect('localhost', 'root', '');
if (!$link) {
    die('MySQL error: ' . mysql_error());
}
echo 'Connected with MySQL';
mysql_close($link);
?>
