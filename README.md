# SimpLanPlus
Progetto per il corso di Compilatori e Interpreti dell'Università di Bologna, A.A. 2020/21

## Come utilizzare SimpLanPlus da Eclipse

* Importare la cartella SimpLanPlus all’interno del workspace di Eclipse:
  * *File > Import*; 
  * Selezionare *General > Projects from Folder or Archive*; 
  * Cliccare su *Directory* e selezionare la cartella scaricata contenente il progetto; 
  * Cliccare su *Finish*. 
* Aggiungere le librerie di ANTLR e generare l’eseguibile:
  * Tasto destro sulla cartella del progetto (quello contentente il file **pom.xml**); 
  * *Show in > Terminal*; 
  * Digitare il comando: 
    ```bash 
       mvn compile assembly:single
    ```
* Crea un file **.simplan** o scegli un file dalla cartella **examples**; 
* Torna al terminale e digita il seguente comando per compilare ed eseguire il file:
  ```bash 
    java -jar target/SimpLanPlus.jar <path_file>
  ```
  con il path al file scelto al posto di **<path_file>**.