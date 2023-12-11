import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        String folderPath = "D:\\Temp\\";
        File file = new File(folderPath);

        //однопоточно
        long start1 = System.currentTimeMillis();
        System.out.println(getFolderSize(file));
        long duration1 = (System.currentTimeMillis() - start1);
        System.out.println(duration1 + "ms\n");

        //многопоточно
        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
        long duration = (System.currentTimeMillis() - start);
        System.out.println(duration + "ms");
    }

    public static long getFolderSize(File folder){
        if(folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for(File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}
