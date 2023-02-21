grammar SimpLanVM;

program: instructions*;

instructions: 'push' REGISTER |
              'pop'	|
              'lw' REGISTER NUMBER '(' REGISTER ')' |
              'mv' REGISTER REGISTER |
              'multi' REGISTER REGISTER NUMBER |
              'li' REGISTER NUMBER |
              'mult' REGISTER REGISTER REGISTER |
              'div' REGISTER REGISTER REGISTER |
              'add' REGISTER REGISTER REGISTER |
              'sub' REGISTER REGISTER REGISTER |
              'and' REGISTER REGISTER |
              'or' REGISTER REGISTER |
              'not' REGISTER REGISTER |
              'beq' REGISTER REGISTER LABEL |
              'bleq' REGISTER REGISTER LABEL |
              'b' LABEL |
              LABEL ':';

REGISTER:
          '$sp' | // stack pointer
          '$fp' | // frame pointer
          '$al' | // access link
          '$a0' | // accumulator
          '$t1'   // temporary value register
          ;

LABEL: ('A' ..'Z') ('A' ..'Z' | '_')*;
NUMBER: '0' | ('-')? (('1' ..'9') ('0' ..'9')*);
BOOL: '0' | '1';

WS              : (' '|'\t'|'\n'|'\r')-> skip;