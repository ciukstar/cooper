package edu.ciukstar.cooper.application;

import java.nio.ByteBuffer;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author sergiu
 */
@Named(value = "codeGen")
@Dependent
public class CodeGenerator {
    public String getValue() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }
}
