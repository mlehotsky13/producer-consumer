package aero.avitech.model;

import aero.avitech.model.cst.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Operation {

    protected OperationType operationType;
}
