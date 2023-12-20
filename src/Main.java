import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        // example to call program in cmd
        // java -jar BiggestFolderFinder.jar -d C:\SkillBox_GitHub\BiggestFolderFinder\ -l 1B

        ParametersBag bag = new ParametersBag(args);

        String folderPath = bag.getPath();
        long sizeLimit = bag.getLimit();

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
}
