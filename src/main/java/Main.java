import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static final Path SRC = Paths.get("C:\\Users\\usr-mbk00066\\Desktop\\Получено").toAbsolutePath();
    private static final Path DST = Paths.get("C:\\Users\\usr-mbk00066\\Desktop\\Sync").toAbsolutePath();
    private static final Path TRASH_PATH = Paths.get("C:\\Users\\usr-mbk00066\\Desktop\\trash").toAbsolutePath();

    public static void main(String[] args) {

//        MyFile src;
//        MyFile dst;

        MyFileAbs proxySrc;
        MyFileAbs proxyDst;


        try {
            proxySrc = new ProxyMyFile(SRC, SRC, TRASH_PATH);
            proxyDst = new ProxyMyFile(DST, DST, TRASH_PATH);
            proxySrc.sync(proxyDst.getMyFile());
//            src = new MyFile(SRC, SRC, TRASH_PATH);
//            dst = new MyFile(DST, DST, TRASH_PATH);
//            src.sync(dst);

        } catch (Exception | Error e) {
            e.printStackTrace();
        }

    }

}
