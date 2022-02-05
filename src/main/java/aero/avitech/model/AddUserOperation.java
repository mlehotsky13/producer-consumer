package aero.avitech.model;

import aero.avitech.entity.User;
import aero.avitech.model.cst.OperationType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode
@Getter
public class AddUserOperation extends Operation {

    private final User user;

    public AddUserOperation(User user) {
        super(OperationType.ADD_USER);
        this.user = user;
    }
}
