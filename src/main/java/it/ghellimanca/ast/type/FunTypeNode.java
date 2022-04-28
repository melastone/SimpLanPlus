package it.ghellimanca.ast.type;

import java.util.List;

/**
 * Represents ((args type) -> ret type) node in the AST.
 */
public class FunTypeNode extends TypeNode {
    final List<TypeNode> argTypes;
    final TypeNode returnType;

    public FunTypeNode(List<TypeNode> argTypes, TypeNode returnType) {
        this.argTypes = argTypes;
        this.returnType = returnType;
    }

    //TODO capire se inserire checksemantics, typecheck, ecc
}
