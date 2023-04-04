grammar SVM;

@header {
package it.ghellimanca.gen.svm;
import java.util.HashMap;
}

@lexer::members {

}

assembly: instruction* ;
instruction : 'push' src=REG    #push
            | 'pop'             #pop
            | 'add' dest=REG reg1=REG reg2=REG      #add
            | 'addi' dest=REG reg1=REG val=NUMBER   #addInt
            | 'sub' dest=REG reg1=REG reg2=REG      #sub
            | 'subi' dest=REG reg1=REG val=NUMBER   #subInt
            | 'mult' dest=REG reg1=REG reg2=REG     #mult
            | 'multi' dest=REG reg1=REG val=NUMBER  #multInt
            | 'div' dest=REG reg1=REG reg2=REG      #div
            | 'divi' dest=REG reg1=REG val=NUMBER   #divInt
            | 'and' dest=REG reg1=REG reg2=REG           #and
            | 'or' dest=REG reg1=REG reg2=REG            #or
            | 'not' dest=REG reg1=REG                    #not
            | 'lw' dest=REG offset=NUMBER '(' src=REG ')'      #loadWord
            | 'li' dest=REG val=NUMBER                         #loadInteger
            | 'sw' src=REG offset=NUMBER '(' dest=REG ')'      #storeWord
            | 'mv' dest=REG src=REG                            #move
            | 'b' dest=LABEL                           #branchToLabel
            | 'beq' reg1=REG reg2=REG dest=LABEL       #branchIfEqual
            | 'bleq' reg1=REG reg2=REG dest=LABEL      #branchIfMoreEqual
            | 'jal' dest=LABEL                      #jumpAndSaveRA
            | 'jr'  dest=REG                        #jumpToRegister
            | LABEL ':'                         #label
            | 'halt'                            #halt
            | 'print' src=REG                   #print
            ;


fragment DIGIT  : '0'..'9';
NUMBER : '0' | ('-')? DIGIT+;
REG : '$'('t'DIGIT|'a0'|'ra'|'sp'|'fp'|'al');

fragment CHAR : ('a'..'z'|'A'..'Z');
LABEL : CHAR (CHAR | DIGIT)*('_'CHAR+)*;

// ESCAPE SEQUENCES
WS  : ( '\t' | ' ' | '\r' | '\n' )+   -> skip;
LINECOMMENTS 	: ';' (~('\n'|'\r'))* -> skip;

