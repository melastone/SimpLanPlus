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
            | move;

push    : 'push' src=REG;                       #push
pop     : 'pop';                                #pop

add     : 'add' dest=REG reg1=REG reg2=REG;         #add
addi    : 'addi' dest=REG reg1=REG val=NUMBER;      #addInt
sub     : 'sub' dest=REG reg1=REG reg2=REG;         #sub
subi    : 'subi' dest=REG reg1=REG val=NUMBER;      #subInt

lw      : 'lw' dest=REG offset=NUMBER '(' src=REG ')'; #loadWord
li      : 'li' dest=REG val=NUMBER;                    #loadInteger
sw      : 'sw' src=REG offset=NUMBER '(' dest=REG ')'; #storeWord
move    : 'move' dest=REG src=REG


fragment DIGIT  : '0'..'9';
REG : '$'('t'DIGIT|'a0'|'ra'|'sp'|'fp');

fragment CHAR : ('a'..'z'|'A'..'Z');
STRING : CHAR+;

// ESCAPE SEQUENCES
WS  : ( '\t' | ' ' | '\r' | '\n' )+   -> skip;
LINECOMMENTS 	: ';' (~('\n'|'\r'))* -> skip;

ERR: . { errors.add("Invalid character: "+ getText()); } -> channel(HIDDEN);