grammar SVM;

@header {
import java.util.HashMap;
}

@lexer::members {

}

program: instruction* ;
instruction : push
            | pop
            | add
            | addi
            | sub
            | subi
            | lw
            | li
            | sw
            | mv
            | b
            | beq
            | label
            | jal
            | jr;

push    : 'push' src=REG;                       #push
pop     : 'pop';                                #pop

add     : 'add' dest=REG reg1=REG reg2=REG;         #add
addi    : 'addi' dest=REG reg1=REG val=NUMBER;      #addInt
sub     : 'sub' dest=REG reg1=REG reg2=REG;         #sub
subi    : 'subi' dest=REG reg1=REG val=NUMBER;      #subInt

lw      : 'lw' dest=REG offset=NUMBER '(' src=REG ')'; #loadWord
li      : 'li' dest=REG val=NUMBER;                    #loadInteger
sw      : 'sw' src=REG offset=NUMBER '(' dest=REG ')'; #storeWord
mv      : 'mv' dest=REG src=REG                        #move

b       : 'b' dest=LABEL                            #branch
beq     : 'beq' reg1=REG reg2=REG dest=LABEL        #branchIfEq
label   : LABEL ':'                                 #label

jal     : 'jal' dest=LABEL                          #jumpAndSaveRA
jr      : 'jr'  dest=REG                            #jumpToLabel


fragment DIGIT  : '0'..'9';
REG : '$'('t'DIGIT|'a0'|'ra'|'sp'|'fp'|'al');

fragment CHAR : ('a'..'z'|'A'..'Z');
LABEL : CHAR+;

// ESCAPE SEQUENCES
WS  : ( '\t' | ' ' | '\r' | '\n' )+   -> skip;
LINECOMMENTS 	: ';' (~('\n'|'\r'))* -> skip;

ERR: . { errors.add("Invalid character: "+ getText()); } -> channel(HIDDEN);