FUNCTION Find-File([String] $pattern){
  $input=$((ls).Name|Select-String -AllMatches -SimpleMatch -Pattern $pattern);
  if($null -eq $input){
    echo "$pattern NOT FOUND !!!";
  }elseif($input.Length -eq 1){
    explorer.exe /select,$input[0];
  }elseif($input.Length -gt 1){
    for($i=0;$i -lt $input.Length;$i++){$_=$input[$i];echo "$i`t$_";}
    $index=Read-Host "please input index";
    explorer.exe /select,$input[$index];
  }
}
