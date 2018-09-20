
//NER
document.getElementById("ner").innerHTML = "curl -d \"Das ist ein Test von Dennis Demmer\" -H\n" +
    "            \"Content-Type: text/plain\" -X POST " + window.location.href + "ner";


//Autocorrect
document.getElementById("correct").innerHTML = "curl -d \"Das ist ein Test von Dennis Demmer\" -H\n" +
    "            \"Content-Type: text/plain\" -X POST " + window.location.href + "correct";


//Upload TXT
document.getElementById("uploadtxt").innerHTML = " curl -i -F 'file=@test1.txt' "+ window.location.href +"txt/correct";


//Upload ZIP
document.getElementById("uploadzip").innerHTML = " curl -i -F 'file=@test.zip' " + window.location.href + "zip/true";
