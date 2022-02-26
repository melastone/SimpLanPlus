- come mai usano maven? ci sono vantaggi?
  trovato nella documentazione scritta dagli altri. nel progetto Java aiuta nelle dipendenze e nella build!
- come scelgo la struttura dati con la quale implementare la sumbol table?

Per ora non abbiamo fatto altro che generare il lexer ed il parser utilizzando ANTLR. Dobbiamo ancora interpretare i risultati.

Il passo successivo è capire da che parte farci per la fase di Analisi semantica. La prima domanda da porsi è: come vogliamo rappresentare il nostro Environment?

Optiamo per realizzare una lista di hash tables. Sia nelle slides che nei progetti degli altri vediamo che è questa la struttura dati scelta. Non vediamo perché fare una cosa

Per prima cosa configuriamo il progetto come Maven project. Aggiungiamo il framework al progetto esistente seguendo le istruzioni https://www.jetbrains.com/help/idea/convert-a-regular-project-into-a-maven-project.html
Inizializziamo il Group ID del file POM ed aggiungiamo le dependencies dei pacchetti altr. 
Facciamo un refactoring sensato dei file che abbiamo fino ad ora, esclusi tutti quelli generati da Antlr che stanno ancora nella directory gen.