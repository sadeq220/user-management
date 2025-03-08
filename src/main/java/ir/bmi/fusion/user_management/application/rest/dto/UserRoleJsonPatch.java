package ir.bmi.fusion.user_management.application.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * JSON PATCH format
 *  it contains operation to perform(e.g. remove, add, replace)
 */
@Getter
@Setter
public class UserRoleJsonPatch {
    private OP op;
    private List<Long> roleIds;

    public static enum OP {
        REPLACE,ADD,REMOVE
    }
}
