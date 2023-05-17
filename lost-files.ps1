FUNCTION Lost-Files([String] $pattern){
    $input=$((ls).Name|Select-String -SimpleMatch -Pattern $pattern);
  if($null -eq $input){
    $pattern|Out-File -Encoding UTF8 -Append $pattern;
  }
}

FUNCTION Find-NotExist($[String] path){
  cat -Encoding UTF8 path|ForEach-Object -proccess{Lost-Files $_}
}
