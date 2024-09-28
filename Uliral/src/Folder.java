import java.io.File;

//Гол хэрэгжүүлсэн санаа нь хавтасан дотор хавтас гэх мэтээр холилдсон бүтэц байсан ч алдаагүй ажиллах

public class Folder {
    //Дотоод класс хавтас болон файл тоог хадгалах.
    private static class Result {
        int folderCount;
        int fileCount;

        Result(int folderCount, int fileCount) {
            this.folderCount = folderCount;
            this.fileCount = fileCount;
        }
    }

    public static void main(String[] args) {
        //Шалгах жишээ хавтасны хаяг.
        String path = "src/Test";

        File folder = new File(path);

        if (folder.exists() && folder.isDirectory()) {
            //Үр дүнг хадгалах.
            Result result = count(folder);

            System.out.println("Нийт хавтасны тоо : " + result.folderCount);
            System.out.println("Нийт файлын тоо : " + result.fileCount);
        } else {
            System.out.println("Байхгүй хавтас.");
        }
    }

    //Хавтасны бүх directory-оор recursive байдлаар гүйж хавтас, файл тоог шалгах функц.
    private static Result count(File folder) {
        int folderCount = 0;
        int fileCount = 0;

        File[] items = folder.listFiles();

        if (items != null) {
            for (File item : items) {
                if (item.isDirectory()) {
                    folderCount++;
                    //subfolder болгон дахин recursive байдлаар гүйж шалгана.
                    Result subfolder = count(item);
                    folderCount += subfolder.folderCount;
                    fileCount += subfolder.fileCount;
                } else if (item.isFile()) {
                    fileCount++;
                }
            }
        }
        return new Result(folderCount, fileCount);
    }
}