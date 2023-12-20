import java.io.File;

public class SizeCalculator {

    private static long getFolderSize(File folder){
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

    /*TODO методы getHumanReadableSize и getSizeFromHumanReadable работают. но реализованы не самым лучшим образом
    т.к. при старте реализации не было четкого понимания кк они будут использоваться*/

    public static String getHumanReadableSize(long size){
        double currentSize = size;
        int siPrefixDigits;
        for (siPrefixDigits = 0; currentSize/1024 > 1; siPrefixDigits++){
            currentSize = currentSize / 1024;
        }

        String siPrefix = "B";
        switch (siPrefixDigits){
            case 1 -> siPrefix = "Kb";
            case 2 -> siPrefix = "Mb";
            case 3 -> siPrefix = "Gb";
            case 4 -> siPrefix = "Tb";
            case 5 -> siPrefix = "Pb";
            case 6 -> siPrefix = "Eb";
        }
        return String.format("%.2f", currentSize) + siPrefix;
    }

    public static long getSizeFromHumanReadable(String size) {
        if (size.contains("B")) {return Long.parseLong(size.substring(0, size.indexOf('B')).strip()) * (long) Math.pow(1024, 0);}
        if (size.contains("K")) {return Long.parseLong(size.substring(0, size.indexOf('K')).strip()) * (long) Math.pow(1024, 1);}
        if (size.contains("M")) {return Long.parseLong(size.substring(0, size.indexOf('M')).strip()) * (long) Math.pow(1024, 2);}
        if (size.contains("G")) {return Long.parseLong(size.substring(0, size.indexOf('G')).strip()) * (long) Math.pow(1024, 3);}
        if (size.contains("P")) {return Long.parseLong(size.substring(0, size.indexOf('P')).strip()) * (long) Math.pow(1024, 4);}
        if (size.contains("E")) {return Long.parseLong(size.substring(0, size.indexOf('E')).strip()) * (long) Math.pow(1024, 5);}
        return -1L;
    }
}
