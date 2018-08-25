
//NER
document.getElementById("ner").innerHTML = "curl -d \"Das ist ein Test von Dennis Demmer\" -H\n" +
    "            \"Content-Type: text/plain\" -X POST " + window.location.href + "ner";


//Autocorrect
document.getElementById("correct").innerHTML = "curl -d \"Das ist ein Test von Dennis Demmer\" -H\n" +
    "            \"Content-Type: text/plain\" -X POST " + window.location.href + "correct";


//Upload TXT
document.getElementById("uploadtxt").innerHTML = " curl -d \"@test.txt\" -H \"Content-Type:\n" +
    "            application/txt\" -X POST " + window.location.href + "uploadtxt";


//Upload ZIP
document.getElementById("uploadzip").innerHTML = " curl -d \"@test.zip\" -H \"Content-Type:\n" +
    "            application/txt\" -X POST " + window.location.href + "uploadzip";
