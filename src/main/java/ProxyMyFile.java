import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.*;

import static java.util.logging.Level.*;
import static java.util.logging.Level.CONFIG;
import static java.util.logging.Level.INFO;

public class ProxyMyFile extends MyFileAbs implements AutoCloseable {

    private MyFile myFile;

    public static final Logger LOGGER = Logger.getLogger(MyFile.class.getName());
    private static Handler fileHandlerException;
    private static Handler fileHandlerInfo;
    private static Handler consoleHandlerInfo;

    public ProxyMyFile(Path path, Path root, Path trash) {
        try {
            this.myFile = new MyFile(path, root, trash);
        } catch (Exception e) {
            LOGGER.log(WARNING, "Exception in MyFile constructor", e);
        }

    }

    public MyFile getMyFile() {
        return myFile;
    }

    @Override
    public void sync(MyFile dst) {
        LOGGER.log(INFO, String.format("Method %s, value: %s, this.myFile Value: %s", "void sync(MyFile dst)", dst, myFile));
        try {
            myFile.sync(dst);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(WARNING, "Exception in method sync(MyFile dst)", e);
        }
    }

    @Override
    public void moveOnce(MyFile dst) {
        LOGGER.log(INFO, String.format("Method %s, value: %s, this.myFile Value: %s", "moveOnce(MyFile dst)", dst, myFile));
        try {
            myFile.moveOnce(dst);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(WARNING, "Exception in method moveOnce(MyFile dst)", e);
        }
    }

    @Override
    public int containByName(List<MyFile> another) {
        LOGGER.log(INFO, String.format("Method %s, value: %s, this.myFile Value: %s", "int containByName(List<MyFile> another)", another, myFile));
        return myFile.containByName(another);
    }

    @Override
    public boolean isNewer(MyFile another) {
        LOGGER.log(INFO, String.format("Method %s, value: %s, this.myFile Value: %s", "boolean isNewer(MyFile another)", another, myFile));

        return myFile.isNewer(another);
    }

    @Override
    public Path getPath() {
        LOGGER.log(INFO, String.format("Method %s, this.myFile Value: %s", "Path getPath()", myFile));
        return myFile.getPath();
    }


    @Override
    public boolean equals(Object o) {
        return myFile.equals(o);
    }

    @Override
    public int hashCode() {
        return myFile.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        return myFile.compareTo(o);
    }


    @Override
    public String toString() {
        return myFile.toString();
    }

    private static void initLogger() {
        LOGGER.setLevel(ALL);
        LOGGER.setUseParentHandlers(false);

        try {
            fileHandlerException = new FileHandler(
                    "C:\\Users\\usr-mbk00066\\Desktop\\dailyCopy\\exception_log_" +
                            new SimpleDateFormat("YYYY.MM.dd_H.mm.s").format(Calendar.getInstance().getTime()) +
                            ".log"
            );
            fileHandlerException.setFormatter(new SimpleFormatter());
            fileHandlerException.setLevel(WARNING);
            fileHandlerException.setEncoding("UTF-8");
            LOGGER.addHandler(fileHandlerException);


            fileHandlerInfo = new FileHandler(
                    "C:\\Users\\usr-mbk00066\\Desktop\\dailyCopy\\info_log_" +
                            new SimpleDateFormat("YYYY.MM.dd_H.mm.s").format(Calendar.getInstance().getTime()) +
                            ".log"

            );
            fileHandlerInfo.setFormatter(new SimpleFormatter());
            fileHandlerInfo.setLevel(CONFIG);
            fileHandlerInfo.setEncoding("UTF-8");
            fileHandlerInfo.setFilter(record -> record.getLevel().equals(INFO));
            LOGGER.addHandler(fileHandlerInfo);

            consoleHandlerInfo = new ConsoleHandler();
            consoleHandlerInfo.setLevel(CONFIG);
            consoleHandlerInfo.setFilter(record -> record.getLevel().equals(INFO));
            LOGGER.addHandler(consoleHandlerInfo);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        fileHandlerException.close();
        fileHandlerInfo.close();
        consoleHandlerInfo.close();
    }
}
