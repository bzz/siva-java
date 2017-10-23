package tech.sourced.siva;

import java.nio.file.attribute.PosixFilePermission;
import java.util.EnumSet;
import java.util.Set;

import static java.nio.file.attribute.PosixFilePermission.*;

/**
 * FileModeUtils maps siva files modes to UNIX files mode since in Siva Format v1 they are represented as Go os.FileMod.
 * @see <a href="https://github.com/src-d/go-siva/issues/11"</a>
 */
public class FileModeUtils {
    private static final PosixFilePermission[] decodeMap = {
            OTHERS_EXECUTE,
            OTHERS_WRITE,
            OTHERS_READ,
            GROUP_EXECUTE,
            GROUP_WRITE,
            GROUP_READ,
            OWNER_EXECUTE,
            OWNER_WRITE,
            OWNER_READ
    };

    static Set<PosixFilePermission> posixFilePermissions(int mode) {
        int mask = 1;
        Set<PosixFilePermission> perms = EnumSet.noneOf(PosixFilePermission.class);
        for (PosixFilePermission flag : decodeMap) {
            if (flag != null && (mask & mode) != 0) {
                perms.add(flag);
            }
            mask = mask << 1;
        }
        return perms;
    }
}
