package org.example.jdbc.bank.model;

import lombok.Data;
import org.example.jdbc.bank.enums.OperationType;

@Data
public class Operation {

    private int id;
private int numero;
    private double montant;
    private OperationType operationType;

    public Operation(int id, int numero, double montant, OperationType operationType) {
        this.id = id;
        this.numero = numero;
        this.montant = montant;
        this.operationType = operationType;
    }
}
