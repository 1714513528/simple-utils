FUNCTION Find-File([String] $pattern){
  $input=$((ls).Name|Select-String -AllMatches -SimpleMatch -Pattern $pattern);
  if($null -ne $input){
    explorer.exe /select,$input;
  }else{
    echo "FILE NOT FOUND !!!";
  }
}
