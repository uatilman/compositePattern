import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.List;

public abstract class MyFileAbs implements Serializable, Comparable {

    abstract void sync(MyFile dst) throws IOException;

    abstract void moveOnce(MyFile dst) throws IOException;

    abstract int containByName(List<MyFile> another);

    abstract boolean isNewer(MyFile another);

    abstract Path getPath();

    abstract MyFile getMyFile();

}
