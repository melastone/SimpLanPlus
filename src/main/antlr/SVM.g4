grammar SVM;

@header {
package it.ghellimanca.gen.svm;
import java.util.HashMap;
}

@lexer::members {

}

program: instruction* ;
instruction : push      #pushIntoStack
            | pop       #popFromStack
            | add   #sum
            | addi  #addInteger
            | sub   #subtraction
            | subi  #subInt
            | mult  #multiplication
            | multi #multInt
            | div   #division
            | and   #logicAnd
            | or    #logicOr
            | not   #logicNot
            | lw      #loadWord
            | li      #loadInteger
            | sw      #storeWord
            | mv      #move
            | b         #branchToLabel
            | beq       #branchIfEq
            | bleq      #branchIfMoreOrEqual
            | jal       #jumpAndSaveRA
            | jr        #jumpToRegister
            | LABEL ':'     #label
            | 'halt'        #halt
            | 'print' src=REG   #print
            ;


push    : 'push' src=REG;
pop     : 'pop';

add     : 'add' dest=REG reg1=REG reg2=REG;
addi    : 'addi' dest=REG reg1=REG val=NUMBER;
sub     : 'sub' dest=REG reg1=REG reg2=REG;
subi    : 'subi' dest=REG reg1=REG val=NUMBER;
mult     : 'mult' dest=REG reg1=REG reg2=REG;
multi    : 'multi' dest=REG reg1=REG val=NUMBER;
div     : 'div' dest=REG reg1=REG reg2=REG;

and      : 'and' reg1=REG reg2=REG;
or      : 'or' reg1=REG reg2=REG;
not      : 'not' reg1=REG reg2=REG;

lw      : 'lw' dest=REG offset=NUMBER '(' src=REG ')';
li      : 'li' dest=REG val=NUMBER;
sw      : 'sw' src=REG offset=NUMBER '(' dest=REG ')';
mv      : 'mv' dest=REG src=REG;

b       : 'b' dest=LABEL;
beq     : 'beq' reg1=REG reg2=REG dest=LABEL;
bleq     : 'bleq' reg1=REG reg2=REG dest=LABEL;

jal     : 'jal' dest=LABEL;
jr      : 'jr'  dest=REG;


fragment DIGIT  : '0'..'9';
NUMBER : '0' | ('-')? DIGIT;
REG : '$'('t'DIGIT|'a0'|'ra'|'sp'|'fp'|'al');

fragment CHAR : ('a'..'z'|'A'..'Z');
LABEL : CHAR+('_'CHAR+)*;

// ESCAPE SEQUENCES
WS  : ( '\t' | ' ' | '\r' | '\n' )+   -> skip;
LINECOMMENTS 	: ';' (~('\n'|'\r'))* -> skip;

