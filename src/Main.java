import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        //String folderPath = "D:\\Temp\\stations-data\\";
        String folderPath = "D:\\Temp\\old\\";
        long sizeLimit = 40 * 1024;
        File file = new File(folderPath);
        Node root = new Node(file);

        //однопоточно
/*        long start1 = System.currentTimeMillis();
        System.out.println(getHumanReadableSize(getFolderSize(file)));
        long duration1 = (System.currentTimeMillis() - start1);
        System.out.println(duration1 + "ms\n");*/

        //многопоточно
        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root.toStringWithFilter(sizeLimit));

        long duration = (System.currentTimeMillis() - start);
        System.out.println(duration + "ms");

    }

    //TODO 3:11:20

}
